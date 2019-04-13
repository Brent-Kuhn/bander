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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> dbUserIds = new ArrayList<>();
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String userId;
    private int position = 0;
    private int notDoneYet = 0;



    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Firebase auth instance
        auth = FirebaseAuth.getInstance();


        p = new ProgressDialog(this);
        p.setMessage("Loading Profiles....");


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
                            AsyncTaskExample asyncTask=new AsyncTaskExample();
                            asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");
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
                                        Bundle bundle = new Bundle();
                                        bundle.putStringArrayList("users", dbUserIds);
                                        bundle.putInt("position", position);
                                        selectedFragment.setArguments(bundle);

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
       /* for(int i = 0; i < dbUserIds.size(); i++)
        Log.v("Users",dbUserIds.get(i));*/

        notDoneYet = 1;

    }

    private class AsyncTaskExample extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

            p = new ProgressDialog(MainActivity.this);
            p.setMessage("Please wait...It is downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
    @Override
    protected Void doInBackground(Void... arg) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return
    }
    @Override
    protected void onPostExecute() {
        if(true) {
            p.hide();

        }else {
            p.show();
        }
    }
}

}

