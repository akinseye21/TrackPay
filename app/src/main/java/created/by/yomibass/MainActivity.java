package created.by.yomibass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import created.by.yomibass.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] language={"English","Hausa","Igbo","Yoruba","","German", "French"};
    Point p;
    TextView lang_opt;
    RadioGroup radioGroup;
    RadioButton rdbtn_lang;
    int selectedId;

    RelativeLayout signupLogin, exploreKD, chat, adminCpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        signupLogin = findViewById(R.id.relLogin);
        signupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpLogin.class);
                startActivity(intent);
            }
        });

        exploreKD = findViewById(R.id.relExplore);
        exploreKD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExploreKaduna.class);
                intent.putExtra("code", "no user");
                intent.putExtra("vendor_name", "Unregistered");
                intent.putExtra("vendor_number", "001");
                startActivity(intent);
            }
        });

        chat = findViewById(R.id.relChat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LiveChat.class);
                startActivity(intent);
            }
        });

        adminCpanel = findViewById(R.id.adminCPanel);
        adminCpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DBLoginRegister.class);
//                startActivity(intent);

            }
        });

        loadLocale();
    }


    public void move(View view) {
        if(p != null){
            showPopup(MainActivity.this, p);
        }
    }

    // Get the x and y position after the button is draw on screen
// (It's important to note that we can't get the position in the onCreate(),
// because at that stage most probably the view isn't drawn yet, so it will return (0, 0))
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        lang_opt = findViewById(R.id.lang_opt);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        lang_opt.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    // The method that displays the popup.
    private void showPopup(final AppCompatActivity context, Point p) {
        int popupWidth = 500;
        int popupHeight = 700;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = layoutInflater.inflate(R.layout.popup_language, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = -250;
        int OFFSET_Y = -320;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = layout.findViewById(R.id.close);
        radioGroup = layout.findViewById(R.id.radiogroup);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedId=radioGroup.getCheckedRadioButtonId();
                rdbtn_lang = layout.findViewById(selectedId);
//                Toast.makeText(MainActivity.this,rdbtn_lang.getText(),Toast.LENGTH_SHORT).show();
                if (rdbtn_lang.getText().equals("English")){
                    setLocale("en");
                }
                else if(rdbtn_lang.getText().equals("Hausa")){
                    setLocale("ha");
                }
                else if(rdbtn_lang.getText().equals("Igbo")){
                    setLocale("ig");
                }
                else if(rdbtn_lang.getText().equals("Yoruba")){
                    setLocale("yo");
                }
                else if(rdbtn_lang.getText().equals("German")){
                    setLocale("de");
                }
                else if(rdbtn_lang.getText().equals("French")){
                    setLocale("fr");
                }
                popup.dismiss();
            }
        });
    }




    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        //save data to sharedpreference
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }


    //load language saved in sharedpreference
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", AppCompatActivity.MODE_PRIVATE);
        String languages = prefs.getString("My_lang", "");
        setLocale(languages);
    }

}
