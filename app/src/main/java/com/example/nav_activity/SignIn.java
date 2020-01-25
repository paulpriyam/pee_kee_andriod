package com.example.nav_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nav_activity.AccessToken;
import com.example.nav_activity.App;
import com.example.nav_activity.LoginPost;
import com.example.nav_activity.MainActivity;
import com.example.nav_activity.R;
import com.example.nav_activity.ResponseLogIn;
import com.example.nav_activity.RetroInterface;
import com.example.nav_activity.SignUp;
import com.example.nav_activity.data.viewModel.CartDetailsViewModel;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    CallbackManager callbackManager = CallbackManager.Factory.create();
    private FirebaseAuth mAuth;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private LoginButton loginButton;
    private String firstName, lastName, email, birthday, gender;
    private URL profilePicture;
    private String userId;
    private String TAG = "SignIn";

    private EditText userEmail;
    private EditText userPassword;
    private Button login;
    private Button signup;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
//                    "(?=.*[0-9])" +         //at least 1 digit
//                    "(?=.*[a-z])" +         //at least 1 lower case letter
//                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
//                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        final String EMAIL = "email";
        userEmail =findViewById(R.id.enteremail);
        userPassword=findViewById(R.id.passwordenter);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);


        SignInButton google=findViewById(R.id.googlelogin);


        final GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.Client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(SignIn.this, gso);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 0);

            }
        });

        final LoginButton facebook=findViewById(R.id.facebooklogin);
        facebook.setPermissions(Arrays.asList(EMAIL));
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        String idTokenFacebook=loginResult.getAccessToken().getToken();
                        com.example.nav_activity.AccessToken accessToken=new com.example.nav_activity.AccessToken();
                        accessToken.setAccesstoken(idTokenFacebook);
                        accessToken.setType("android");
                        accessToken.setRole(0);
                        App.getRetrofit().create(RetroInterface.class).sendFacebookToken(accessToken).enqueue(new Callback<ResponseLogIn>() {
                            @Override
                            public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                                ResponseLogIn responseLogIn=response.body();
                                if(responseLogIn.getStatus()==1000)
                                {
                                    editor.putString("token",responseLogIn.getData());
                                    editor.putBoolean("loginStatus",true);
                                    editor.apply();
                                    Toast.makeText(SignIn.this,"facebook Login successful",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(SignIn.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onCancel() {
                        Log.d("hello", "hey");

                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("hello", "hey");

                        // App code
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPost loginPost=new LoginPost(userEmail.getText().toString(),userPassword.getText().toString(),0,null);
                App.getRetrofit().create(RetroInterface.class).login(loginPost).enqueue(new Callback<ResponseLogIn>() {
                    @Override
                    public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {

                        if(!response.isSuccessful())
                        {
                            Log.d("login","error geting response");
                            return;
                        }

                        ResponseLogIn responseLogIn=response.body();
                        String message=responseLogIn.getMessage();
                        String token=responseLogIn.getData();//store it in shared preferences.
                        if(message.equals("Login Successful"))
                        {
                            editor.putString("token",responseLogIn.getData());
                            editor.putBoolean("loginStatus",true);
                            editor.apply();
                            Toast.makeText(SignIn.this,"login Successful",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignIn.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(SignIn.this,"login unsuccessful",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<ResponseLogIn> call, Throwable t) {

                        Log.d("login","failed to login");
                    }
                });



            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        Button skip=findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this,MainActivity.class);
                intent.putExtra("loginStatus","false");
                startActivity(intent);
                finish();
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0)
        {
            Task<GoogleSignInAccount> googleSignInAccountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(googleSignInAccountTask);

        }
    }


    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> taskCompleted)
    {
        try {
            SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
            final SharedPreferences.Editor editor=sharedPreferences.edit();
            GoogleSignInAccount account=taskCompleted.getResult(ApiException.class);
            System.out.println("hello");
            String idToken = account.getIdToken();
            System.out.println("token:"+idToken);
            com.example.nav_activity.AccessToken accessToken=new AccessToken();
            accessToken.setAccesstoken(idToken);
            accessToken.setRole(0);
            accessToken.setType("Android");
            App.getRetrofit().create(RetroInterface.class).sendGoogleToken(accessToken).enqueue(new Callback<ResponseLogIn>() {
                @Override
                public void onResponse(Call<ResponseLogIn> call, Response<ResponseLogIn> response) {
                    ResponseLogIn responseLogIn=response.body();
                    int status=responseLogIn.getStatus();
                    System.out.println(status);
                    if(responseLogIn.getStatus()==1000) {
                        editor.putBoolean("loginStatus",true);
                        editor.putString("token",responseLogIn.getData());
                        editor.apply();
                        Toast.makeText(SignIn.this,"google Sign in successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogIn> call, Throwable t) {
                    Log.d("failed","fail");
                }
            });
            Log.e("token",idToken);

        } catch (ApiException e) {

            Log.w("googlesignin", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private boolean validateEmail() {

        String emailInput = userEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            userEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            userEmail.setError("Please enter a valid email address");
            return false;
        } else {
            userEmail.setError(null);
            return true;
        }
    }

}
