package com.example.ndif_yemmanuel.trackpay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlacesToVisit extends AppCompatActivity {

    GridView simpleList;
    public static final String PLACES_TO_VISIT = "http://arrearskdsg.com.ng/mobile/places";
    int ArrayLength;
    TextView name, num;
    ProgressBar progressBar;

    LinearLayout home, explore_kd, helpdesk, profile;
    SharedPreferences sharedpreferences1, sharedPreferences_rl, sharedPreferences_description, sharedPreferences_shortcut, sharedPreferences_payee, sharedPreferences_payment, sharedPreferences_amount, sharedPreferences_date;

    ArrayList<String> Array_rl_num = new ArrayList<>();
    ArrayList<String> Array_description1 = new ArrayList<>();
    ArrayList<String> Array_shortcut = new ArrayList<>();
    ArrayList<String> Array_payee = new ArrayList<>();
    ArrayList<String> Array_paymentstatus = new ArrayList<>();
    ArrayList<String> Array_amount = new ArrayList<>();
    ArrayList<String> Array_date = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_to_visit);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i = getIntent();
        final String vendor_name = i.getStringExtra("vendor_name");
        final String vendor_number = i.getStringExtra("vendor_number");


        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);

        name = findViewById(R.id.name);
        num = findViewById(R.id.uniqueid);
        progressBar = findViewById(R.id.progressBar);

        name.setText(vendor_name);
        num.setText(vendor_number);


        final ArrayList<String> Array_id = new ArrayList<>();
        final ArrayList<String> Array_name = new ArrayList<>();
        final ArrayList<String> Array_description2 = new ArrayList<>();
        final ArrayList<String> Array_location = new ArrayList<>();
        final ArrayList<String> Array_image = new ArrayList<>();
        final ArrayList<String> Array_contact = new ArrayList<>();

        simpleList = findViewById(R.id.simpleGridView);

        progressBar.setVisibility(View.VISIBLE);

        //get JSON's from API
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PLACES_TO_VISIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        System.out.println("Response = "+response);
//                            Toast.makeText(VendorLogin.this, "Response = "+response, Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayLength = jsonArray.length();

                            for (int i = 0; i < ArrayLength; i++) {
                                JSONObject section = jsonArray.getJSONObject(i);
                                String id = section.getString("id");
                                String name = section.getString("name");
                                String description2 = section.getString("description");
                                String location = section.getString("location");
                                String image = section.getString("image");
                                String contact = section.getString("phone");

                                Array_id.add(id);
                                Array_name.add(name);
                                Array_description2.add(description2);
                                Array_location.add(location);
                                Array_image.add(image);
                                Array_contact.add(contact);
                            }

//                            System.out.println("Array name = "+Array_name);

                            //populate values on the gridview
                            GridViewAdapter gridViewAdapter = new GridViewAdapter(getApplicationContext(), Array_image, Array_name, Array_description2, Array_location, Array_contact);
                            simpleList.setAdapter(gridViewAdapter);

                            //close progressbar
                            progressBar.setVisibility(View.GONE);

                            //event handling
                            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String nam = Array_name.get(position);
                                    String im = Array_image.get(position);
                                    String desc = Array_description2.get(position);
                                    String locate = Array_location.get(position);
                                    String cont = Array_contact.get(position);

                                    Intent intent = new Intent(PlacesToVisit.this, PlacesView.class);
                                    intent.putExtra("vendor_name", vendor_name);
                                    intent.putExtra("vendor_number", vendor_number);
                                    intent.putExtra("place name", nam);
                                    intent.putExtra("place image", im);
                                    intent.putExtra("place description", desc);
                                    intent.putExtra("place location", locate);
                                    intent.putExtra("place contact", cont);
                                    startActivity(intent);

                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PlacesToVisit.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            System.out.println("Error "+e.getMessage());
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PlacesToVisit.this, "Error loaded places", Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(PlacesToVisit.this);
        requestQueue.add(stringRequest);






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
            Array_description1.add(sharedPreferences_description.getString("description_" + j, ""));
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
                Intent i = new Intent(PlacesToVisit.this, VendorDashboard.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("vendor_email", vendorEmail);
                i.putExtra("paid", paid);
                i.putExtra("endorsed", endorsed);
                i.putExtra("audited", audited);
                i.putExtra("total_rl", total_rl);
                i.putStringArrayListExtra("rl_num", Array_rl_num);
                i.putStringArrayListExtra("description", Array_description1);
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
                Intent i = new Intent(PlacesToVisit.this, ExploreKaduna.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlacesToVisit.this, HelpDesk.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlacesToVisit.this, VendorProfile.class);
                startActivity(i);
            }
        });


    }
}
