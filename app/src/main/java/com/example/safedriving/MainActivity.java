package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    static String currentaccId = "";
    static String name = "";
    static profileDataBase profiles;
    static User firsttime = null;
    GoogleSignInClient mGoogleSignInClient;
    static double usr_weight = 0.0, gen_const = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProfileDB();

        // GOOGLE SIGN IN
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_clientid))
                .requestProfile()
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
         GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
         if (account != null) {
             mGoogleSignInClient.signOut();
         }

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    }
                }
            private void signIn() {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 2);
            }
        });
    }

    public void updateUI(GoogleSignInAccount account) {
        if (account == null) {
            Toast.makeText(getApplicationContext(),
                    (CharSequence) ("Login Unsuccessful"),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            // USER INFO DISPLAYING FOR TESTING
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();

            currentaccId = personId;
            name = personGivenName;

            Log.i("DISPLAY NAME:  ", personName);
            Log.i("GIVEN NAME:   ", personGivenName);
            Log.i("FAMILY NAME:   ", personFamilyName);
            Log.i("EMAIL:    ", personEmail);
            Log.i("ID:    ", personId);

            ////////////////////////////////////////////////////

            User u = profiles.getUser(currentaccId);
            if (u == null) {
                Log.i("GOOGLE USER:  ", "NO WEIGHT IN RECORD");
                firsttime = new User(currentaccId, null, 0);
                Intent intent = new Intent(this, firstSign.class);
                startActivity(intent);
            }
            else {
                Log.i("GOOGLE USER:  ", String.valueOf(u.getWeight()));
                Log.i("GOOGLE USER:  ", u.getGender());
                usr_weight = u.getWeight();
                if (u.getGender().toLowerCase().equals("male")) {
                    gen_const = 0.73;
                } else if (u.getGender().toLowerCase().equals("female")) {
                    gen_const = 0.66;
                } else {
                    Toast.makeText(getApplicationContext(),
                            (CharSequence) ("Error"),
                            Toast.LENGTH_SHORT).show();
                    Log.i("Gender not available: ", u.getGender());
                }
                //mGoogleSignInClient.signOut();
                Intent intent = new Intent(this, DrinkSelecter.class);
                startActivity(intent);

            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 2) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("GOOGLE SIGN IN TAG", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }


    public void guestLogin(View view) {
        Intent intent = new Intent(this, User_Data.class);
        startActivity(intent);
    }

    public void initProfileDB() {
        profiles = new profileDataBase(this);
       // profiles.clearAll(profiles.getAllTasks());

//        profiles.addUser(new User("123", "female", 123.0));
//        profiles.addUser(new User("456", "male", 171.0));
        profiles.addUser(new User("102827879357047828164", "female", 117));
    }

}