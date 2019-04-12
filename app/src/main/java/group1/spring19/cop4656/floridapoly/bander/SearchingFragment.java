package group1.spring19.cop4656.floridapoly.bander;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchingFragment extends Fragment {

    private Button nextProfile;
    private Button prevProfile;

    private DatabaseReference mDatabase;
    private String userId;
    ArrayList<String> dbUserIds = new ArrayList<>();
    private int position;

    private TextView mUserBio;
    private TextView mUserContactInfo;
    private TextView mUserName;
    private TextView mInstrument;
    private TextView mUserType;
    private TextView mGenre;
    private ImageView mUserImage;
    private String image;
    private TextView mLink;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_searching, container, false);
        //list
        //position
        // if position > max listval postion = 0
        // if position < 0 position = max listval

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dbUserIds = bundle.getStringArrayList("users");
            position = bundle.getInt("position");
            Log.v("Users", dbUserIds.toString());
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUserName = (TextView) v.findViewById(R.id.bandNameSrchTextView);
        mUserType = (TextView) v.findViewById(R.id.ArtistTypeSrchTextView);
        mInstrument = (TextView) v.findViewById(R.id.InstrumentSrchTextView);
        mGenre = (TextView) v.findViewById(R.id.GenreSrchTextView);
        mLink = (TextView) v.findViewById(R.id.linkSrchTextView);
        mUserBio = (TextView) v.findViewById(R.id.bioSrchTextView);
        mUserContactInfo = (TextView) v.findViewById(R.id.contactSrchTextView);
        mUserImage = (ImageView) v.findViewById(R.id.SrchImageView);

        userId = dbUserIds.get(position);

        getData();

        return v;

        //onclick next
        //  pass list
        //  pass position++
        //  start SearchingFragment

        //onClick prev
        //  pass list
        //  pass position--
        // start SearchingFragment
    }

    private void getData() {
        UserInfo uInfo = new UserInfo();
        uInfo.setUsername(mDatabase.child("users").child(userId).child("username").toString());
        uInfo.setBio(mDatabase.child("users").child(userId).child("bio").toString());
        uInfo.setContact(mDatabase.child("users").child(userId).child("contact").toString());
        uInfo.setGenre(mDatabase.child("users").child(userId).child("genre").toString());
        uInfo.setInstrument(mDatabase.child("users").child(userId).child("instrument").toString());
        uInfo.setLink(mDatabase.child("users").child(userId).child("link").toString());
        uInfo.setType(mDatabase.child("users").child(userId).child("type").toString());
        uInfo.setImage(mDatabase.child("users").child(userId).child("image").toString());

        mUserName.setText(uInfo.getUsername());
        mUserType.setText(uInfo.getType());
        mInstrument.setText(uInfo.getInstrument());
        mGenre.setText(uInfo.getGenre());
        mLink.setText(uInfo.getLink());
        mUserBio.setText(uInfo.getBio());
        mUserContactInfo.setText(uInfo.getContact());
        image = uInfo.getImage();
        try {
            Glide.with(this).load(image).into(mUserImage);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}