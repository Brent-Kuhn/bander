package group1.spring19.cop4656.floridapoly.bander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UsernamePasswordActivity extends AppCompatActivity {

    private EditText userNameCreate;
    private EditText pWordCreate;
    private Button next;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_password);

        userNameCreate = findViewById(R.id.useNameCreateEditText);
        pWordCreate = findViewById(R.id.passCreateEditText);
        //this sets up on click listener for the create Account button
        cancel = (Button) findViewById(R.id.userNamPassCancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();

            }
        });

        next = (Button) findViewById(R.id.useNamePassNextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();

            }
        });


    }

    public void goToLogin() {
        Intent intent = new Intent(this, LogInActivity.class);

        startActivity(intent);
    }

    public void goToEditProfile() {
        Intent intent = new Intent(this, EditProfileActivity.class);

        startActivity(intent);
    }
}
