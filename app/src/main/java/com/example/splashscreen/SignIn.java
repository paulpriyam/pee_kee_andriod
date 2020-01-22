package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        FirebaseApp.initializeApp(this);
        final String EMAIL = "email";

        mAuth=FirebaseAuth.getInstance();


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
        facebook.setReadPermissions(Arrays.asList("email", "user_birthday", "user_posts"));

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Log.e("token:", loginResult.getAccessToken().toString());

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e(TAG, object.toString());
                                Log.e(TAG, response.toString());
                                try {
                                    Log.e("hello", "hey");
                                    userId = object.getString("id");
                                    profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
                                    if (object.has("first_name"))
                                        firstName = object.getString("first_name");
                                    if (object.has("last_name"))
                                        lastName = object.getString("last_name");
                                    if (object.has("email"))
                                        email = object.getString("email");
                                    if (object.has("birthday"))
                                        birthday = object.getString("birthday");
                                    if (object.has("gender"))
                                        gender = object.getString("gender");
                                    Log.e("values:", "email: " + email);
                                    Intent intent = new Intent(SignIn.this, AfterSignIn.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email, birthday, gender");
                        request.setParameters(parameters);
                        request.executeAsync();
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


        Button skip=findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this,MainActivity.class);
                startActivity(intent);
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
//    public static final String AUTH_TOKEN_TYPE;
//
//    static {
//        // Initialize oauth scope
//        StringBuilder sb = new StringBuilder();
//        sb.append("oauth2:");
//        for (String scope : AUTH_SCOPES) {
//            sb.append(scope);
//            sb.append(" ");
//        }
//        AUTH_TOKEN_TYPE = sb.toString().trim();
//    }

    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> taskCompleted)
    {
        try {
            GoogleSignInAccount account = taskCompleted.getResult(ApiException.class);
            String idToken = account.getIdToken();
            System.out.println("token:"+idToken);
            Log.e("token",idToken);
            Intent intent=new Intent(SignIn.this,AfterSignIn.class);
            intent.putExtra("token",idToken);
            startActivity(intent);
        } catch (ApiException e) {

            Log.w("googlesignin", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
