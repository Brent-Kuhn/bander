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

public class YourProfileFragment extends Fragment {

    private Button editFilters;
    private Button editProfile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_your_profile, container, false);

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



}
