package com.example.ndif_yemmanuel.trackpay;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HelpDesk extends AppCompatActivity {
    TextView name, num;
    LinearLayout home, explore_kd, helpdesk, profile;
    SharedPreferences sharedpreferences1, sharedPreferences_rl, sharedPreferences_description, sharedPreferences_shortcut, sharedPreferences_payee, sharedPreferences_payment, sharedPreferences_amount, sharedPreferences_date;
    TextView callNum;

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
        setContentView(R.layout.activity_help_desk);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i = getIntent();
        final String vendor_name = i.getStringExtra("vendor_name");
        final String vendor_number = i.getStringExtra("vendor_number");

        name = findViewById(R.id.name);
        num = findViewById(R.id.uniqueid);
        callNum = findViewById(R.id.call_num);

        name.setText(vendor_name);
        num.setText(vendor_number);

        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);

//        callNum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:"+callNum.getText().toString().trim()));
//
//                if (ActivityCompat.checkSelfPermission(HelpDesk.this,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(HelpDesk.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
//                    startActivity(callIntent);
//                }
//
//                if(ActivityCompat.checkSelfPermission(HelpDesk.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
//
//                }
//
//            }
//        });


        //get fields from sharedpreference
        sharedpreferences1 = getSharedPreferences("My Preference", Context.MODE_PRIVATE);
        String vendorNum = sharedpreferences1.getString("vendor_number", "");
        String vendorName = sharedpreferences1.getString("vendor_name", "");
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




        //handling bottom menu
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelpDesk.this, VendorDashboard.class);
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
        });
        explore_kd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelpDesk.this, ExploreKaduna.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelpDesk.this, VendorProfile.class);
                startActivity(i);
            }
        });
    }




    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(HelpDesk.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(HelpDesk.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(HelpDesk.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(HelpDesk.this, new String[]{permission}, requestCode);
            }
        } else {
            System.out.println(permission+" is already granted");
//            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void ask(View v) {
//        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,1);      //permission for location
//        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,1);    //permission to write ex-storage
//        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,1);     //permission to read ex-storage
//        askForPermission(Manifest.permission.CAMERA,1);                    //permission to use camera
        askForPermission(Manifest.permission.CALL_PHONE,1);

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+callNum.getText().toString().trim()));
        if(ActivityCompat.checkSelfPermission(HelpDesk.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            startActivity(callIntent);
        }
    }
}
