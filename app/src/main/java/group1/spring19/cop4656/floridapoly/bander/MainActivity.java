package group1.spring19.cop4656.floridapoly.bander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userName = findViewById(R.id.u_name);
        userName.setHint(getResources().getString(R.string.u_name));

        final EditText pWord = findViewById(R.id.p_word);
        pWord.setHint(getResources().getString(R.string.p_word));
    }
}
