package group1.spring19.cop4656.floridapoly.bander;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

    ArrayList<String> dbUserIds = new ArrayList<>();
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userId;
    private int position;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        position = 0;

        // Get the Firebase auth instance
        auth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();





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
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            break;
                        case R.id.nav_searchProfiles:

                            GetUserList startSearching = new GetUserList();
                            startSearching.execute();

                            break;

//                        case R.id.nav_matches:
//                            selectedFragment = new MatchesFragment();
//                           break;
                    }

                    return true;
                }
            };

//    public void goToEditFilters() {
//        Intent intent = new Intent(this, FiltersActivity.class);
//        startActivity(intent);
//    }

    private class GetUserList extends AsyncTask<Void, Void, Bundle> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Loading Profiles....");
            pd.show();
        }

        @Override
        protected Bundle doInBackground(Void... voids) {
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
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("users", dbUserIds);
            bundle.putString("id", userId);
            bundle.putInt("position", position);

            return bundle;
        }

        @Override
        protected void onPostExecute(Bundle bundle) {
            super.onPostExecute(bundle);
            Fragment selectedFragment = new SearchingFragment();
            selectedFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            pd.dismiss();
        }
    }


    private void getUsers(DataSnapshot dataSnapshot) {

        for(DataSnapshot child : dataSnapshot.getChildren() ){

            for(DataSnapshot child2 : child.getChildren() ){
                dbUserIds.add(child2.getKey());
            }
        }
        dbUserIds.remove(userId);

    }

}

