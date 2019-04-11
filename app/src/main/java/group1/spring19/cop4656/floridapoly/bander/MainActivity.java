package group1.spring19.cop4656.floridapoly.bander;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> dbUserIds = new ArrayList<>();
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userId;

    private int notDoneYet = 0;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Firebase auth instance
        auth = FirebaseAuth.getInstance();


        pd = new ProgressDialog(this);
        pd.setMessage("Loading Profiles....");


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new YourProfileFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch(menuItem.getItemId()){
                        case R.id.nav_yourProfile:
                            selectedFragment = new YourProfileFragment();
                            break;
                        case R.id.nav_searchProfiles:
                          pd.show();

                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            StorageReference storageReference = FirebaseStorage.getInstance().getReference();

                                mDatabase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        getUsers(dataSnapshot);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                               // for (int i = 0; i < dbUserIds.size(); i++)
                                 //   Log.v("Users", dbUserIds.get(i));

                            //pass list
                            //pass position

                                    selectedFragment = new SearchingFragment();
                                    pd.dismiss();
                                    break;

//                        case R.id.nav_matches:
//                            selectedFragment = new MatchesFragment();
//                           break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public void goToEditFilters() {
        Intent intent = new Intent(this, FiltersActivity.class);
        startActivity(intent);
    }

    private void getUsers(DataSnapshot dataSnapshot) {

        for(DataSnapshot child : dataSnapshot.getChildren() ){
            // Do magic here

            for(DataSnapshot child2 : child.getChildren() ){
                //Log.v("Users", child2.getKey());
                dbUserIds.add(child2.getKey());
            }
        }
        for(int i = 0; i < dbUserIds.size(); i++)
        Log.v("Users",dbUserIds.get(i));

        notDoneYet = 1;

    }

}

