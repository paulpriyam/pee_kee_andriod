package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.FacebookSdk;
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

public class SignIn extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_sign_in);

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

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
