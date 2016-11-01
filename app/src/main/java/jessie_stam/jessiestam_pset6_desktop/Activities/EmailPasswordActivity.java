package jessie_stam.jessiestam_pset6_desktop.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import jessie_stam.jessiestam_pset6_desktop.Helpers.BookManager;
import jessie_stam.jessiestam_pset6_desktop.Helpers.MenuHelper;
import jessie_stam.jessiestam_pset6_desktop.R;

/**
 * Created by Jessie on 13-10-2016.
 */

public class EmailPasswordActivity extends MainActivity implements View.OnClickListener {

    String title;
    String instr;
    String confirm_pass;

    TextView title_text;
    TextView instr_text;
    TextView status;
    TextView detail;

    EditText email_field;
    EditText password_field;
    EditText confirm_field;

    private Toolbar toolbar;
    MenuHelper menu_helper;

    BookManager manager;

    private static final String TAG = "EmailPassword";


    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailpassword);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();

        manager = BookManager.getOurInstance();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            title = extras.getString("log_sign");
            instr = extras.getString("instr");
            confirm_pass = extras.getString("pass_confirm");
        }

        title_text = (TextView) findViewById(R.id.signup_title);
        instr_text = (TextView) findViewById(R.id.signup_instr);
        status = (TextView) findViewById(R.id.status);
        detail = (TextView) findViewById(R.id.detail);

        Button signup_login_button = (Button) findViewById(R.id.sign_up_button);

        findViewById(R.id.sign_up_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        //findViewById(R.id.continue_button).setOnClickListener(this);

        title_text.setText(title);
        instr_text.setText(instr);

        email_field = (EditText) findViewById(R.id.email_input);
        password_field = (EditText) findViewById(R.id.password_input);
        confirm_field = (EditText) findViewById(R.id.password_confirm_input);

        if (confirm_pass != null) {
            if (confirm_pass.equals("visible")) {
                confirm_field.setVisibility(View.VISIBLE);
                signup_login_button.setText("Sign me up!");
            }
            else {
                signup_login_button.setText("Log me in!");
            }
        }


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu_item) {

        String clicked_item = menu_helper.getClickedMenuItem(menu_item, this);
        Toast.makeText(this, clicked_item, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(menu_item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
//                        if (!task.isSuccessful()) {
//                            mStatusTextView.setText(R.string.auth_failed);
//                        }
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = email_field.getText().toString();
        if (TextUtils.isEmpty(email)) {
            email_field.setError("Required.");
            valid = false;
        } else {
            email_field.setError(null);
        }

        String password = password_field.getText().toString();
        if (TextUtils.isEmpty(password)) {
            password_field.setError("Required.");
            valid = false;
        } else {
            password_field.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null) {
            status.setText(getString(R.string.emailpassword_status_fmt, user.getEmail()));
            detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            Button singup_login = (Button) findViewById(R.id.sign_up_button);

            if (singup_login != null) {
                findViewById(R.id.sign_up_button).setVisibility(View.GONE);
                findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
                //findViewById(R.id.continue_button).setVisibility(View.VISIBLE);
                email_field.setVisibility(View.GONE);
                password_field.setVisibility(View.GONE);

                if (confirm_field.getVisibility() != View.INVISIBLE) {
                    confirm_field.setVisibility(View.GONE);
                }
            }

        } else {
            status.setText(R.string.signed_out);
            detail.setText(null);

            findViewById(R.id.sign_up_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
            //findViewById(R.id.continue_button).setVisibility(View.GONE);
            email_field.setVisibility(View.VISIBLE);
            password_field.setVisibility(View.VISIBLE);

            if (confirm_pass != null) {
                if (confirm_pass.equals("invisible")) {
                    confirm_field.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        String new_instr = "Logged in succesfully! Click the search button to look for " +
                "books. Click the list button to access your lists.";

        if (i == R.id.sign_up_button) {

            String email = email_field.getText().toString();
            String password = password_field.getText().toString();
            String password_confirm = confirm_field.getText().toString();

            if (title.equals("Signing up")) {

                if (!password.equals(password_confirm)) {
                    Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
                else if (email.equals("")) {
                    Toast.makeText(this, "Fill in your e-mail!", Toast.LENGTH_SHORT).show();
                }
                else {
                    createAccount(email, password);

                    instr_text.setText(new_instr);

                    manager.create_user(email);
                }

            } else if (title.equals("Logging in")) {
                manager.create_user(email);
                signIn(email, password);
                instr_text.setText(new_instr);
            }

        } else if (i == R.id.sign_out_button) {
            manager.logout_user();

            signOut();

            instr_text.setText(instr);
        }
    }
}
