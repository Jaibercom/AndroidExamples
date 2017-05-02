package co.edu.udea.compumovil.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivity";

    private EditText mUserNameField;
    private EditText mEmailField;
    private EditText mPasswordField;

    private FirebaseAuth mAuth;

    private String username;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserNameField = (EditText) findViewById(R.id.field_user_name);
        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //startMainActivity(currentUser);
    }



    private boolean validateForm() {
        boolean valid = true;

        username = mUserNameField.getText().toString();
        if (TextUtils.isEmpty(username)) {
            mUserNameField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }


        email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void createAccount() {

        if (!validateForm()) {
            return;
        }
        Log.d(TAG, "createAccount:" + email);
        //showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            startMainActivity(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void startMainActivity(FirebaseUser user){

        if(user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_sign_up_button) {
            Log.d(TAG, "email_sign_up_button:" );
            createAccount();

        } else if (i == R.id.login_bottom) {
            Log.d(TAG, "login_bottom:" );
            finish();
        }/* else if (i == R.id.disconnect_button) {
            revokeAccess();
        }*/
    }
}
