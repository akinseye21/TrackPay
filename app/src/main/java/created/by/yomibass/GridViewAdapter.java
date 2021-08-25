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

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> img;
    private ArrayList<String> name;
    private ArrayList<String> description;
    private ArrayList<String> location;
    private ArrayList<String> contact;


    public GridViewAdapter (Context context, ArrayList<String> img, ArrayList<String> name, ArrayList<String> description, ArrayList<String> location, ArrayList<String> contact){
        //Getting all the values
        this.context = context;
        this.img = img;
        this.name = name;
        this.description = description;
        this.location = location;
        this.contact = contact;
    }


    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflaInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflaInflater.inflate(R.layout.list_gridview, parent, false);
        }

        ImageView imag = convertView.findViewById(R.id.image_grid);
        TextView txt = convertView.findViewById(R.id.text_grid);


        Picasso.with(context).load(img.get(position)).into(imag);
        txt.setText(name.get(position));


        return convertView;
    }
}
