package group1.spring19.cop4656.floridapoly.bander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class EditProfileActivity extends AppCompatActivity {

    private EditText yourName;

    private Spinner youAreSpin;
    private Spinner musicGenreSpin;
    private Spinner whatInstrumentSpin;

    private EditText link_1;
    private EditText link_2;
    private EditText link_3;
    private EditText link_4;

    private EditText yourBio;
    private EditText yourContactInfo;

    private ImageButton addImage_1;
    private ImageButton addImage_2;
    private ImageButton addImage_3;
    private ImageButton addImage_4;

    private Button cancel;
    private Button done;

    //this is here so that we can hide this layout if musician is not selected from the youAre spinner
    private LinearLayout whatInstrumentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final Boolean profileAlreadyCompleted = false;

        yourName = findViewById(R.id.yourNameEditText);

        youAreSpin = (Spinner) findViewById(R.id.youAreSpinner);
        musicGenreSpin = (Spinner) findViewById(R.id.musicGenreSpinner);
        whatInstrumentSpin = (Spinner) findViewById(R.id.whatInstrumentSpinner);

        //sets adapter for the youAre spinner
        ArrayAdapter<String> youAreAdapter = new ArrayAdapter<String>(EditProfileActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.profileTypeArray));
        youAreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        youAreSpin.setAdapter(youAreAdapter);


        //sets adapter for the musicGenre spinner
        ArrayAdapter<String> musicGenreAdapter = new ArrayAdapter<String>(EditProfileActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.musicGenreArray));
        musicGenreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musicGenreSpin.setAdapter(musicGenreAdapter);


        //sets adapter for whatInstrument spinner
        ArrayAdapter<String> whatInstrumentAdapter = new ArrayAdapter<String>(EditProfileActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.whatInstrumentArray));
        whatInstrumentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whatInstrumentSpin.setAdapter(whatInstrumentAdapter);

        addImage_1 = (ImageButton) findViewById(R.id.profileImageButton1);
        addImage_2 = (ImageButton) findViewById(R.id.profileImageButton2);
        addImage_3 = (ImageButton) findViewById(R.id.profileImageButton3);
        addImage_4 = (ImageButton) findViewById(R.id.profileImageButton4);

        link_1 = findViewById(R.id.linkEditText1);
        link_2 = findViewById(R.id.linkEditText2);
        link_3 = findViewById(R.id.linkEditText3);
        link_4 = findViewById(R.id.linkEditText4);

        yourBio = findViewById(R.id.yourBioEditText);
        yourContactInfo = findViewById(R.id.yourContactEditText);

        cancel = (Button) findViewById(R.id.editProfileCancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileAlreadyCompleted)
                    goToProfile();
                else
                    goToLogin();
            }
        });

        done = (Button) findViewById(R.id.editProfileDoneButton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();

            }
        });
    }
    public void goToLogin() {
        Intent intent = new Intent(this, LogInActivity.class);

        startActivity(intent);
    }

    public void goToProfile() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }



}