package com.example.ndif_yemmanuel.trackpay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlacesView extends AppCompatActivity {

    TextView name, num;
    TextView placeName, placeDescription, placeLocation, placeContact;
    ImageView placeImage;

    LinearLayout home, explore_kd, helpdesk, profile, more;
    SharedPreferences sharedpreferences1, sharedPreferences_rl, sharedPreferences_description, sharedPreferences_shortcut, sharedPreferences_payee, sharedPreferences_payment, sharedPreferences_amount, sharedPreferences_date;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ArrayList<String> Array_rl_num = new ArrayList<>();
    ArrayList<String> Array_description = new ArrayList<>();
    ArrayList<String> Array_shortcut = new ArrayList<>();
    ArrayList<String> Array_payee = new ArrayList<>();
    ArrayList<String> Array_paymentstatus = new ArrayList<>();
    ArrayList<String> Array_amount = new ArrayList<>();
    ArrayList<String> Array_date = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_view);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Intent i = getIntent();
        final String vendor_name = i.getStringExtra("vendor_name");
        final String vendor_number = i.getStringExtra("vendor_number");
        final String Place_Name = i.getStringExtra("place name");
        final String Place_Image = i.getStringExtra("place image");
        final String Place_Description = i.getStringExtra("place description");
        final String Place_Location = i.getStringExtra("place location");
        final String Place_Contact = i.getStringExtra("place contact");
        final String code = i.getStringExtra("code");


        placeName = findViewById(R.id.place_name);
        placeDescription = findViewById(R.id.place_description);
        placeLocation = findViewById(R.id.place_location);
        placeContact = findViewById(R.id.place_contact);
        placeImage = findViewById(R.id.place_img);
        name = findViewById(R.id.name);
        num = findViewById(R.id.uniqueid);

        name.setText(vendor_name);
        num.setText(vendor_number);
        placeName.setText(Place_Name);
        placeDescription.setText(Place_Description);
        placeLocation.setText(Place_Location);
        placeContact.setText(Place_Contact);
        Picasso.with(this).load(Place_Image).into(placeImage);

        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(PlacesView.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView naviview = findViewById(R.id.navigationview);
        if (naviview != null) {
            setupDrawerContent(naviview);
        }

        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.explore:
//                        Intent i = new Intent(PlacesView.this, ExploreKaduna.class);
//                        i.putExtra("vendor_name", vendor_name);
//                        i.putExtra("vendor_number", vendor_number);
//                        i.putExtra("code", code);
//                        startActivity(i);
//                        break;
//                    case R.id.help:
//                        Intent j = new Intent(PlacesView.this, HelpDesk.class);
//                        j.putExtra("vendor_name", vendor_name);
//                        j.putExtra("vendor_number", vendor_number);
//                        j.putExtra("code", code);
//                        startActivity(j);
//                        break;
//                    case R.id.profile:
//                        if (code.equals("vendor")){
//                            Intent k = new Intent(PlacesView.this, VendorProfile.class);
//                            startActivity(k);
//                        }
//                        else if (code.equals("mda")){
//                            Intent l = new Intent(PlacesView.this, MdaProfile.class);
//                            startActivity(l);
//                        }
//                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });


        //get fields from sharedpreference
        sharedpreferences1 = getSharedPreferences("My Preference", Context.MODE_PRIVATE);
        final String vendorNum = sharedpreferences1.getString("vendor_number", "");
        final String vendorName = sharedpreferences1.getString("vendor_name", "");
        final String paid = sharedpreferences1.getString("paid", "");
        final String endorsed = sharedpreferences1.getString("endorsed", "");
        final String audited = sharedpreferences1.getString("audited", "");
        final String vendorEmail = sharedpreferences1.getString("vendor_email", "");
        final int total_rl = sharedpreferences1.getInt("total_rl", 0);

        //get fields from sharedpreference_rl
        sharedPreferences_rl = getSharedPreferences("preference_rl", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            String node = sharedPreferences_rl.getString("rl_"+j, "");
//            System.out.println("node "+j+" = "+node);
//            Toast.makeText(getApplicationContext(), "node "+j+" = "+node, Toast.LENGTH_LONG).show();
            Array_rl_num.add(sharedPreferences_rl.getString("rl_" + j, ""));
        }

        //get fields from sharedpreference_description
        sharedPreferences_description = getSharedPreferences("preference_description", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            Array_description.add(sharedPreferences_description.getString("description_" + j, ""));
        }

        //get fields from sharedpreference_shortcut
        sharedPreferences_shortcut = getSharedPreferences("preference_shortcut", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            Array_shortcut.add(sharedPreferences_shortcut.getString("shortcut_" + j, ""));
        }

        //get fields from sharedpreference_payee
        sharedPreferences_payee = getSharedPreferences("preference_payee", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            Array_payee.add(sharedPreferences_payee.getString("payee_" + j, ""));
        }

        //get fields from sharedpreference_payment
        sharedPreferences_payment = getSharedPreferences("preference_payment", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            Array_paymentstatus.add(sharedPreferences_payment.getString("payment_" + j, ""));
        }

        //get fields from sharedpreference_amount
        sharedPreferences_amount = getSharedPreferences("preference_amount", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            Array_amount.add(sharedPreferences_amount.getString("amount_" + j, ""));
        }

        //get fields from sharedpreference_date
        sharedPreferences_date = getSharedPreferences("preference_date", Context.MODE_PRIVATE);
        for(int j=0;j<total_rl;j++)
        {
            Array_date.add(sharedPreferences_date.getString("date_" + j, ""));
        }


        more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        //handling bottom menu
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(code.equals("vendor")){
                    Intent i = new Intent(PlacesView.this, VendorDashboard.class);
                    i.putExtra("vendor_name", vendor_name);
                    i.putExtra("vendor_number", vendor_number);
                    i.putExtra("vendor_email", vendorEmail);
                    i.putExtra("paid", paid);
                    i.putExtra("endorsed", endorsed);
                    i.putExtra("audited", audited);
                    i.putExtra("total_rl", total_rl);
                    i.putStringArrayListExtra("rl_num", Array_rl_num);
                    i.putStringArrayListExtra("description", Array_description);
                    i.putStringArrayListExtra("shortcut", Array_shortcut);
                    i.putStringArrayListExtra("payee", Array_payee);
                    i.putStringArrayListExtra("paymentstatus", Array_paymentstatus);
                    i.putStringArrayListExtra("amount", Array_amount);
                    i.putStringArrayListExtra("date", Array_date);
                    startActivity(i);
                }
                else if(code.equals("mda")){
                    Intent i = new Intent(PlacesView.this, MdaDashboard.class);
                    i.putExtra("mda_name", vendorName);
                    i.putExtra("mda_code", vendorNum);
                    i.putExtra("mda_email", vendorEmail);
                    i.putExtra("paid", paid);
                    i.putExtra("endorsed", endorsed);
                    i.putExtra("audited", audited);
                    i.putExtra("total_rl", total_rl);
                    i.putStringArrayListExtra("rl_num", Array_rl_num);
                    i.putStringArrayListExtra("description", Array_description);
                    i.putStringArrayListExtra("shortcut", Array_shortcut);
                    i.putStringArrayListExtra("payee", Array_payee);
                    i.putStringArrayListExtra("paymentstatus", Array_paymentstatus);
                    i.putStringArrayListExtra("amount", Array_amount);
                    i.putStringArrayListExtra("date", Array_date);
                    startActivity(i);
                }

            }
        });
        explore_kd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlacesView.this, ExploreKaduna.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("code", code);
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlacesView.this, HelpDesk.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("code", code);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.equals("vendor")){
                    Intent i = new Intent(PlacesView.this, VendorProfile.class);
                    startActivity(i);
                }
                else if (code.equals("mda")){
                    Intent i = new Intent(PlacesView.this, MdaProfile.class);
                    startActivity(i);
                }
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
