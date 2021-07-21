package com.example.ndif_yemmanuel.trackpay.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ndif_yemmanuel.trackpay.MessageActivity;
import com.example.ndif_yemmanuel.trackpay.Model.Chat;
import com.example.ndif_yemmanuel.trackpay.Model.User;
import com.example.ndif_yemmanuel.trackpay.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Chat> mChat;
    private String imageURL;

//    TextView show_message;
//    CircleImageView profile_image;

    FirebaseUser fuser;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    public TextView show_message;
    public CircleImageView profile_image;

    String d;
    ArrayList<String> msg = new ArrayList<>();

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageURL) {
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageURL = imageURL;
    }

    @Override
    public int getCount() {
        return mChat.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Chat chat = mChat.get(i);
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        if(mChat.get(i).getSender().equals(fuser.getUid())){
            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, null);

            show_message = view.findViewById(R.id.show_message);
            profile_image = view.findViewById(R.id.profile_image);

            show_message.setText(chat.getMessage());
            return view;
        }else{
            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, null);

            show_message = view.findViewById(R.id.show_message);
            profile_image = view.findViewById(R.id.profile_image);

            show_message.setText(chat.getMessage());
            return view;
        }

//        if (i == MSG_TYPE_RIGHT){
//            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, null);
//
//            show_message = view.findViewById(R.id.show_message);
//            profile_image = view.findViewById(R.id.profile_image);
//
//
//            return view;
//        } else {
//            view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, null);
//
//            show_message = view.findViewById(R.id.show_message);
//            profile_image = view.findViewById(R.id.profile_image);
//
//            show_message.setText(chat.getMessage());
//
//
//            return view;
//        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        fuser = FirebaseAuth.getInstance().getCurrentUser();
//        if(mChat.get(position).getSender().equals(fuser.getUid())){
//            return MSG_TYPE_RIGHT;
//        }else{
//            return MSG_TYPE_LEFT;
//        }
//    }
}






//        extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
//
//    private Context mContext;
//    private List<Chat> mChat;
//    private String imageURL;
//
//    FirebaseUser fuser;
//
//    public static final int MSG_TYPE_LEFT = 0;
//    public static final int MSG_TYPE_RIGHT = 1;
//
//    public MessageAdapter(Context mContext, List<Chat> mChat, String imageURL){
//        this.mChat = mChat;
//        this.mContext = mContext;
//        this.imageURL = imageURL;
//    }
//
//    @NonNull
//    @Override
//    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        if (viewType == MSG_TYPE_RIGHT){
//            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
//            return new MessageAdapter.ViewHolder(view);
//        }else{
//            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
//            return new MessageAdapter.ViewHolder(view);
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
//
//        Chat chat = mChat.get(position);
//
//        holder.show_message.setText(chat.getMessage());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mChat.size();
//    }
//
//    public class  ViewHolder extends RecyclerView.ViewHolder{
//
//        public TextView show_message;
//        public CircleImageView profile_image;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            show_message = itemView.findViewById(R.id.show_message);
//            profile_image = itemView.findViewById(R.id.profile_image);
//
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        fuser = FirebaseAuth.getInstance().getCurrentUser();
//        if(mChat.get(position).getSender().equals("User")){
//            return MSG_TYPE_RIGHT;
//        }else{
//            return MSG_TYPE_LEFT;
//        }
//
//    }
//}