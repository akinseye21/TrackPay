package com.example.ndif_yemmanuel.trackpay;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ndif_yemmanuel.trackpay.Adapter.MessageAdapter;
import com.example.ndif_yemmanuel.trackpay.Model.Chat;
import com.example.ndif_yemmanuel.trackpay.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    FirebaseUser fuser;
    DatabaseReference reference, reference2;

    ImageView btn_send;
    EditText txt_send;

    MessageAdapter messageAdapter;
    List<Chat> mChat;

    ListView listView;

    Intent intent;
    String uid;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.list_view);


        username = findViewById(R.id.username);

        btn_send = findViewById(R.id.btn_send);
        txt_send = findViewById(R.id.txt_send);

        intent = getIntent();
        String uname = intent.getStringExtra("username");
        uid = intent.getStringExtra("id");

        fuser = FirebaseAuth.getInstance().getCurrentUser();


        reference2 = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    username.setText(user.getUsername());

                readMessages(fuser.getUid(), uid, user.getImageURL());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txt_send.getText().toString().trim();
                if(!msg.equals("")){
                    sendMessage(fuser.getUid(), uid, msg);
                }else{
                    Toast.makeText(MessageActivity.this, "You can not send an empty message", Toast.LENGTH_LONG).show();
                }
                txt_send.setText("");
            }
        });

    }

    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        reference2.child("Chats").push().setValue(hashMap);
    }

    private void readMessages(String myid, String userid, String imageurl){
        mChat = new ArrayList<Chat>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(myid)){
                        mChat.add(chat);
                    }

//
//                    if(chat.getSender().equals("User")){
//                        mChat.add(chat);
//                    }

//                    Chat message = snapshot.child("message").getValue().toString();
//                    String receiver = snapshot.child("receiver").getValue().toString();
//                    String sender = snapshot.child("sender").getValue().toString();
//                    mChat.add(message);

                }
                messageAdapter = new MessageAdapter(MessageActivity.this, mChat, imageurl);
                listView.setAdapter(messageAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
