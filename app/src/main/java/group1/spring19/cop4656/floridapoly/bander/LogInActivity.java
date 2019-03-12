package group1.spring19.cop4656.floridapoly.bander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText userName;
    private EditText pWord;
    private Button logIn;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userName = findViewById(R.id.u_name);
        pWord = findViewById(R.id.p_word);
        //this sets up on click listener for the create Account button
        createAccount = (Button) findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUnamePword();

            }
        });

        logIn = (Button) findViewById(R.id.logInButton);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();

            }
        });



    }

    public void createUnamePword() {
        Intent intent = new Intent(this, UsernamePasswordActivity.class);

        startActivity(intent);
    }

    public void goToProfile() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
