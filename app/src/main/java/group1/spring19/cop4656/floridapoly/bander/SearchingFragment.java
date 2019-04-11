package group1.spring19.cop4656.floridapoly.bander;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchingFragment extends Fragment {
    List<String> dbUserIds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //list
        //position
        // if position > max listval postion = 0
        // if position < 0 position = max listval

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            // How to access the values
            ArrayList<String> userList = bundle.getStringArrayList("users");
            String userId = bundle.getString("id");
            int position = bundle.getInt("position");
        }
        return inflater.inflate(R.layout.fragment_searching,container, false);

        //onclick next
        //  pass list
        //  pass position++
        //  start SearchingFragment

        //onClick prev
        //  pass list
        //  pass position--
        // start SearchingFragment
    }
}