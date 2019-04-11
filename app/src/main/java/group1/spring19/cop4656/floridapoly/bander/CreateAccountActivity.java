package group1.spring19.cop4656.floridapoly.bander;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    private EditText inputEmail;
    private EditText inputPassword;
    private Button signUp;
    private Button cancel;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userId;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        // Get the Firebase auth instance
        auth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DataSnapshot dataSnapshot;

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        //this sets up on click listener for the create Account button
        cancel = (Button) findViewById(R.id.userNamePassWordCancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();

            }
        });

        pd = new ProgressDialog(this);
        pd.setMessage("Creating Account....");
        signUp = (Button) findViewById(R.id.userNamePassWordSignUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.show();
                        if(!task.isSuccessful()) {
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed " + task.getException(),  Toast.LENGTH_SHORT).show();
                        } else{
                            // User information
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            userId = user.getUid();

                            mDatabase.child("users").child(userId).child("email").setValue(user.getEmail()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    mDatabase.child("users").child(userId).child("username").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            mDatabase.child("users").child(userId).child("type").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    mDatabase.child("users").child(userId).child("genre").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            mDatabase.child("users").child(userId).child("instrument").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    mDatabase.child("users").child(userId).child("link").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            mDatabase.child("users").child(userId).child("bio").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    mDatabase.child("users").child(userId).child("contact").setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                        @Override
                                                                                        public void onSuccess(Void aVoid) {
                                                                                            mDatabase.child("users").child(userId).child("image").setValue("https://firebasestorage.googleapis.com/v0/b/bander-98025.appspot.com/o/bander-logo.png?alt=media&token=8e38e2d9-1e0b-4177-b1a3-98c2a522bd42").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    pd.dismiss();
                                                                                                    startActivity(new Intent(CreateAccountActivity.this, EditProfileActivity.class));
                                                                                                    finish();
                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    });
                                                                                }
                                                                            });
                                                                        }
                                                                    });
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LogInActivity.class);

        startActivity(intent);
    }

    @Override
    public void onStart(){
        Log.d(TAG,"onStart() called");
        super.onStart();
    }
    @Override
    public void onResume() {
        Log.d(TAG,"onResume() called");
        super.onResume();
    }
    @Override
    public void onPause() {
        Log.d(TAG,"onPause() called");
        super.onPause();
    }
    @Override
    public void onStop() {
        Log.d(TAG,"onStop() called");
        super.onStop();
    }
    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy() called");
        super.onDestroy();
    }
}