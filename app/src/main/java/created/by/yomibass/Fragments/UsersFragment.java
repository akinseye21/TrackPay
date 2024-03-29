package created.by.yomibass.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import created.by.yomibass.Adapter.CustomAdapter;
import created.by.yomibass.Adapter.UserAdapter;
import created.by.yomibass.Model.User;
import created.by.yomibass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private List<User> mUsers;

    List<String> uses;
    List<String> u_id;

    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);

//        recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);

        listView = view.findViewById(R.id.list_view);

        u_id = new ArrayList<>();
        uses = new ArrayList<>();
        
        readUsers();

        return view;
    }

    private void readUsers() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);

//                    assert firebaseUser != null;

                    //for the admin side
                    if (!user.getId().equals(firebaseUser.getUid())){
                        uses.add(user.getUsername());
                        u_id.add(user.getId());
                    }

                    //for the user side
//                    String name = snapshot.child("username").getValue().toString();
//                    uses.add(name);
//                    String id = snapshot.child("id").getValue().toString();
//                    u_id.add(id);

//                    assert user != null;
//                    assert firebaseUser != null;

//                    System.out.println("My output "+user);
//                    Toast.makeText(getContext(), "My Output "+user, Toast.LENGTH_LONG).show();

//                    if(!user.getId().equals(firebaseUser.getUid())){
//                        mUsers.add(user);
//                    }
                }
                CustomAdapter customAdapter = new CustomAdapter(getContext(), uses, u_id);
                listView.setAdapter(customAdapter);
//                userAdapter = new UserAdapter(getContext(), mUsers);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
