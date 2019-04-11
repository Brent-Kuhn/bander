package group1.spring19.cop4656.floridapoly.bander;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                            selectedFragment = new SearchingFragment();
                            break;
//                        case R.id.nav_matches:
//                            selectedFragment = new MatchesFragment();
//                            break;
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