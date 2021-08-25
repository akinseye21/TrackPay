package created.by.yomibass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import created.by.yomibass.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> name;
    private ArrayList<String> address;
    private ArrayList<String> open;
    private ArrayList<String> closes;
    private ArrayList<String> image;
    private ArrayList<String> contact;

    public HotelViewAdapter(Context context, ArrayList<String> name, ArrayList<String> address, ArrayList<String> open,
                            ArrayList<String> closes, ArrayList<String> image, ArrayList<String> contact){
        //Getting all the values
        this.context = context;
        this.name = name;
        this.address = address;
        this.open = open;
        this.closes = closes;
        this.image = image;
        this.contact = contact;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflaInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflaInflater.inflate(R.layout.list_hotel, parent, false);
        }

        ImageView res_img = convertView.findViewById(R.id.restaurant_img);
        TextView res_name = convertView.findViewById(R.id.restaurant_name);
        TextView res_add = convertView.findViewById(R.id.restaurant_address);
        TextView open_hr = convertView.findViewById(R.id.open_hour);
        TextView close_hr = convertView.findViewById(R.id.close_hour);
        TextView res_contact = convertView.findViewById(R.id.restaurant_contact);

        Picasso.with(context).load(image.get(position)).into(res_img);
        res_name.setText(name.get(position));
        res_add.setText(address.get(position));
        open_hr.setText(open.get(position));
        close_hr.setText(closes.get(position));
        res_contact.setText(contact.get(position));


        return convertView;
    }
}
