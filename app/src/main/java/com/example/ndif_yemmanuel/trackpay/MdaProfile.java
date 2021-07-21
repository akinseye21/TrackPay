package com.example.ndif_yemmanuel.trackpay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MdaProfile extends AppCompatActivity {

    EditText edt_mdacode, edt_mdaemail, edt_password, edt_confirm_password;
    TextView name, uid;
    Button BtnEditProfile, BtnSave;
    SharedPreferences sharedpreferences;
    ProgressBar progressBar;
    String gotten_Status, gotten_MdaCode, gotten_Email;

    LinearLayout home, explore_kd, helpdesk, profile, more;
    SharedPreferences sharedpreferences1, sharedPreferences_rl, sharedPreferences_description, sharedPreferences_shortcut, sharedPreferences_payee, sharedPreferences_payment, sharedPreferences_amount, sharedPreferences_date, SPEditProfile, SP_Get_Profile;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    public static final String UPDATE_PROFILE = "https://arrearskdsg.com.ng/mobile/mdaupdate";

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
        setContentView(R.layout.activity_mda_profile);
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

        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);
        progressBar = findViewById(R.id.progressBar);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(MdaProfile.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView naviview = findViewById(R.id.navigationview);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        if (naviview != null) {
            setupDrawerContent(naviview);
        }

        //get fields from sharedpreference
        sharedpreferences1 = getSharedPreferences("My Preference", Context.MODE_PRIVATE);
        final String vendorNum = sharedpreferences1.getString("vendor_number", "");
        final String vendorName = sharedpreferences1.getString("vendor_name", "");
        final String paid = sharedpreferences1.getString("paid", "");
        final String endorsed = sharedpreferences1.getString("endorsed", "");
        final String audited = sharedpreferences1.getString("audited", "");
        final String vendorEmail = sharedpreferences1.getString("vendor_email", "");
        final int total_rl = sharedpreferences1.getInt("total_rl", 0);

        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.signout:

                        AlertDialog.Builder builder = new AlertDialog.Builder(MdaProfile.this);
                        builder.setTitle("Exit");
                        builder.setMessage("Do you want to exit Trackpay?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(MdaProfile.this,  MainActivity.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                                dialog.dismiss();
                                mDrawerLayout.closeDrawers();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                    case R.id.chat:
                        Intent i = new Intent(MdaProfile.this,  LiveChat.class);
                        startActivity(i);
                        break;

                    case R.id.openticket:
//                        Intent j = new Intent(ExploreKaduna.this,  LiveChat.class);
//                        startActivity(j);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });



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

        //get fields from sharedpreference_profile
//        SP_Get_Profile = getSharedPreferences("Profile", Context.MODE_PRIVATE);
//        String f_name = SP_Get_Profile.getString("firstname", "");
//        String s_name = SP_Get_Profile.getString("surname", "");
//        String p_hone = SP_Get_Profile.getString("phone", "");
//        String e_mail = SP_Get_Profile.getString("email", "");


        edt_mdacode = findViewById(R.id.mdacode);
        edt_mdaemail = findViewById(R.id.mdaemail);
        edt_password = findViewById(R.id.mdapassword);
        edt_confirm_password = findViewById(R.id.mdaconfirmpassword);
        BtnEditProfile = findViewById(R.id.btnedit);
        BtnSave = findViewById(R.id.btnsave);
        name = findViewById(R.id.name);
        uid = findViewById(R.id.uniqueid);

        edt_mdacode.setText(vendorNum);
        edt_mdaemail.setText(vendorEmail);

        //set edittext based on the sharedpreference
//        Edtfname.setText(f_name);
//        Edtsname.setText(s_name);
//        Edtphonenum.setText(p_hone);

        name.setText(vendorName);
        uid.setText(vendorNum);

        BtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnEditProfile.setVisibility(View.INVISIBLE);
                BtnSave.setVisibility(View.VISIBLE);

                edt_mdacode.setFocusable(true);
                edt_mdacode.setClickable(true);
                edt_mdacode.setFocusableInTouchMode(true);

                edt_mdaemail.setFocusable(true);
                edt_mdaemail.setClickable(true);
                edt_mdaemail.setFocusableInTouchMode(true);

                edt_password.setFocusable(true);
                edt_password.setClickable(true);
                edt_password.setFocusableInTouchMode(true);

                edt_confirm_password.setFocusable(true);
                edt_confirm_password.setClickable(true);
                edt_confirm_password.setFocusableInTouchMode(true);

            }
        });

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BtnSave.setVisibility(View.INVISIBLE);
                BtnEditProfile.setVisibility(View.VISIBLE);

                edt_mdacode.setFocusable(false);
                edt_mdacode.setClickable(false);
                edt_mdacode.setFocusableInTouchMode(false);

                edt_mdaemail.setFocusable(false);
                edt_mdaemail.setClickable(false);
                edt_mdaemail.setFocusableInTouchMode(false);

                edt_password.setFocusable(false);
                edt_password.setClickable(false);
                edt_password.setFocusableInTouchMode(false);

                edt_confirm_password.setFocusable(false);
                edt_confirm_password.setClickable(false);
                edt_confirm_password.setFocusableInTouchMode(false);

                String gotten_password = edt_password.getText().toString().trim();
                String gotten_confirm_password = edt_confirm_password.getText().toString().trim();


                progressBar.setVisibility(View.VISIBLE);

                if (!gotten_password.equals(gotten_confirm_password)){
                    progressBar.setVisibility(View.GONE);

                    edt_password.setError("Password do not match");
                    edt_confirm_password.setError("Password do not match");
                }
                else if(gotten_password.isEmpty() || gotten_confirm_password.isEmpty()){
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(MdaProfile.this, "Password can not be empty", Toast.LENGTH_LONG).show();
                }
                else if(gotten_password.length()<5 || gotten_confirm_password.length()<5){
                    progressBar.setVisibility(View.GONE);

                    edt_password.setError("Password length should be more than 5 characters");
                    edt_confirm_password.setError("Password length should be more than 5 characters");
                }
                else{

                    //send to dbase
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_PROFILE,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar.setVisibility(View.GONE);

                                    System.out.println("Response = "+response);

                                    try{
                                        JSONObject jsonObject = new JSONObject(response);
                                        gotten_Status = jsonObject.getString("status");
                                        gotten_MdaCode = jsonObject.getString("mdacode");
                                        gotten_Email = jsonObject.getString("email");


                                        if(gotten_Status.equals("user updated")){

                                            Toast.makeText(MdaProfile.this, "Updated successfully", Toast.LENGTH_LONG).show();

                                        }else{
                                            Toast.makeText(MdaProfile.this, "Update failed, please try again", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                    catch (JSONException e){
                                        e.printStackTrace();
                                        Toast.makeText(MdaProfile.this, "Error!!! Please check inputs", Toast.LENGTH_LONG).show();
                                        System.out.println("Error "+e.getMessage());
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(MdaProfile.this, "Error! Please try again", Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String, String> params = new HashMap<>();
                            params.put("mdacode", edt_mdacode.getText().toString().trim());
                            params.put("email", edt_mdaemail.getText().toString().trim());
                            params.put("password", edt_password.getText().toString().trim());
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MdaProfile.this);
                    requestQueue.add(stringRequest);

                }

            }
        });


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
                Intent i = new Intent(MdaProfile.this, MdaDashboard.class);
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
        });
        explore_kd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MdaProfile.this, ExploreKaduna.class);
                i.putExtra("vendor_name", vendorName);
                i.putExtra("vendor_number", vendorNum);
                i.putExtra("code", "mda");
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MdaProfile.this, HelpDesk.class);
                i.putExtra("vendor_name", vendorName);
                i.putExtra("vendor_number", vendorNum);
                i.putExtra("code", "mda");
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
