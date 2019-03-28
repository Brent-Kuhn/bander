package group1.spring19.cop4656.floridapoly.bander;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class YourProfileFragment extends Fragment {

    private Button editFilters;
    private Button editProfile;
    private Button signOut;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_your_profile, container, false);

        // get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // get current user info
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user ==null) {
                    // If there is no logged in user, bring them to the login activity
                    startActivity(new Intent(getActivity(), LogInActivity.class));
                    getActivity().finish();
                }
            }
        };

        signOut = (Button) v.findViewById(R.id.signOutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        editFilters = (Button) v.findViewById(R.id.editFiltersButton);
        editFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditFilters();

            }
        });

        editProfile = (Button) v.findViewById(R.id.editProfileButton);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditProfile();

            }
        });

        return v;

    }

    public void goToEditFilters() {
        Intent intent = new Intent(getActivity(), FiltersActivity.class);

        startActivity(intent);
    }

    public void goToEditProfile() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);

        startActivity(intent);
    }

    public void signOut() {
        auth.signOut();
        startActivity(new Intent(getActivity(), LogInActivity.class));
        getActivity().finish();
    }

}