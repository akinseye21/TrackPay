package created.by.yomibass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import created.by.yomibass.MessageActivity;
import created.by.yomibass.Model.User;
import created.by.yomibass.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<String> u_id;
    List<String> uses;
    LayoutInflater inflter;

    public TextView username;
    public CircleImageView profile_image;
    public RelativeLayout relClick;

    public CustomAdapter(Context context, List<String> uses, List<String> u_id) {
        this.context = context;
        this.uses = uses;
        this.u_id = u_id;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return uses.size();
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
        view = inflter.inflate(R.layout.agents_item, null);

        username = view.findViewById(R.id.username);
        profile_image = view.findViewById(R.id.profile_image);
        relClick = view.findViewById(R.id.relclick);

//        username.setText( mUsers.get(i).toString());
        username.setText( uses.get(i));

        relClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("username", uses.get(i) );
                intent.putExtra("id", u_id.get(i) );
                context.startActivity(intent);
            }
        });

        return view;
    }
}
