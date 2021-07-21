package com.example.ndif_yemmanuel.trackpay.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ndif_yemmanuel.trackpay.Adapter.CustomAdapter;
import com.example.ndif_yemmanuel.trackpay.Model.Chat;
import com.example.ndif_yemmanuel.trackpay.Model.User;
import com.example.ndif_yemmanuel.trackpay.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

//    ListView listView;
//    private CustomAdapter customAdapter;
//    private List<User> mUser;
//
//    FirebaseUser fuser;
//    DatabaseReference reference;
//
//    private List<String> usersList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

//        listView = v.findViewById(R.id.list_view);
//        fuser = FirebaseAuth.getInstance().getCurrentUser();
//        usersList = new ArrayList<>();
//        reference = FirebaseDatabase.getInstance().getReference();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Chat chat = snapshot.getValue(Chat.class);
//
//                    if(chat.getSender().equals(fuser.getUid())){
//                        usersList.add(chat.getReceiver());
//                    }
//
//                    if(chat.getReceiver().equals(fuser.getUid())){
//                        usersList.add(chat.getSender());
//                    }
//
//                }
//
//                readChats();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return v;
    }

//    private void readChats() {
//        mUser = new ArrayList<>();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    User user = snapshot.getValue(User.class);
//
//                    for(String id : usersList){
//                        if(user.getId().equals(id)){
//                            if(mUser.size() != 0){
//                                for(User user1 : mUser){
//                                    if(!user.getId().equals(user1.getId())){
//                                        mUser.add(user);
//                                    }
//                                }
//                            }else{
//                                mUser.add(user);
//                            }
//                        }
//                    }
//                }
//
////                customAdapter = new CustomAdapter(getContext(), )
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


}
