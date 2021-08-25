package created.by.yomibass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

import created.by.yomibass.R;
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

public class VendorProfile extends AppCompatActivity {

    EditText Edtfname, Edtsname, Edtphonenum;
    TextView name, uid;
    Button BtnEditProfile, BtnSave;
    SharedPreferences sharedpreferences;
    ProgressBar progressBar;

    LinearLayout home, explore_kd, helpdesk, profile, more;
    SharedPreferences sharedpreferences1, sharedPreferences_rl, sharedPreferences_description, sharedPreferences_shortcut, sharedPreferences_payee, sharedPreferences_payment, sharedPreferences_amount, sharedPreferences_date, SPEditProfile, SP_Get_Profile;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    public static final String UPDATE_PROFILE = "https://arrearskdsg.com.ng/mobile/vupdate2";

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
        setContentView(R.layout.activity_vendor_profile);
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
//
//        //get fields from sharedpreference
//        sharedpreferences = getSharedPreferences("My Preference", Context.MODE_PRIVATE);
//        String vendorNum = sharedpreferences.getString("vendor_number", "");
//        String vendorName = sharedpreferences.getString("vendor_name", "");
//        String vendorEmail = sharedpreferences.getString("vendor_email", "");



        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);
        progressBar = findViewById(R.id.progressBar);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(VendorProfile.this, mDrawerLayout, R.string.open, R.string.close);
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(VendorProfile.this);
                        builder.setTitle("Exit");
                        builder.setMessage("Do you want to exit Trackpay?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(VendorProfile.this,  MainActivity.class);
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
                        Intent i = new Intent(VendorProfile.this,  LiveChat.class);
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
        SP_Get_Profile = getSharedPreferences("Profile", Context.MODE_PRIVATE);
        String f_name = SP_Get_Profile.getString("firstname", "");
        String s_name = SP_Get_Profile.getString("surname", "");
        String p_hone = SP_Get_Profile.getString("phone", "");
        String e_mail = SP_Get_Profile.getString("email", "");


        Edtfname = findViewById(R.id.edtFName);
        Edtsname = findViewById(R.id.edtSName);
        Edtphonenum = findViewById(R.id.edtPhoneNum);
        BtnEditProfile = findViewById(R.id.btnedit);
        BtnSave = findViewById(R.id.btnsave);
        name = findViewById(R.id.name);
        uid = findViewById(R.id.uniqueid);

        //set edittext based on the sharedpreference
        Edtfname.setText(f_name);
        Edtsname.setText(s_name);
        Edtphonenum.setText(p_hone);

        name.setText(vendorName);
        uid.setText(vendorNum);



        BtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    BtnEditProfile.setVisibility(View.INVISIBLE);
                    BtnSave.setVisibility(View.VISIBLE);

                    Edtfname.setFocusable(true);
                    Edtfname.setClickable(true);
                    Edtfname.setFocusableInTouchMode(true);

                    Edtsname.setFocusable(true);
                    Edtsname.setClickable(true);
                    Edtsname.setFocusableInTouchMode(true);

                    Edtphonenum.setFocusable(true);
                    Edtphonenum.setClickable(true);
                    Edtphonenum.setFocusableInTouchMode(true);


            }
        });

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnSave.setVisibility(View.INVISIBLE);
                BtnEditProfile.setVisibility(View.VISIBLE);

                //uneditable edittext
                Edtfname.setFocusable(false);
                Edtfname.setClickable(false);
                Edtfname.setFocusableInTouchMode(false);

                Edtsname.setFocusable(false);
                Edtsname.setClickable(false);
                Edtsname.setFocusableInTouchMode(false);

                Edtphonenum.setFocusable(false);
                Edtphonenum.setClickable(false);
                Edtphonenum.setFocusableInTouchMode(false);

                //get the input and update the user profile
                //show progressbar

                progressBar.setVisibility(View.VISIBLE);

                //load data from API
                StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_PROFILE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                System.out.println("Response = "+response);
//                            Toast.makeText(VendorLogin.this, "Response = "+response, Toast.LENGTH_LONG).show();

                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String firstname = jsonObject.getString("firstname");
                                    String surname = jsonObject.getString("surname");
                                    String phone = jsonObject.getString("phone");
                                    String email = jsonObject.getString("email");

                                    if(status.equals("user updated")){

                                        Toast.makeText(getApplicationContext(), "Profile updated successfully", Toast.LENGTH_LONG).show();

                                        Edtfname.setText(firstname);
                                        Edtsname.setText(surname);
                                        Edtphonenum.setText(phone);

                                        SPEditProfile = getSharedPreferences("Profile", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = SPEditProfile.edit();
                                        editor.putString("firstname", firstname);
                                        editor.putString("surname", surname);
                                        editor.putString("phone", phone);
                                        editor.putString("email", email);
                                        editor.commit();
                                    }

                                    progressBar.setVisibility(View.GONE);

                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                    Toast.makeText(VendorProfile.this, "Error occured", Toast.LENGTH_LONG).show();
                                    System.out.println("Error "+e.getMessage());
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(VendorProfile.this, "Error! Please try again", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<>();
                        params.put("vendor_id", vendorNum);
                        params.put("surname", Edtsname.getText().toString().trim());
                        params.put("firstname", Edtfname.getText().toString().trim());
                        params.put("phone", Edtphonenum.getText().toString().trim());
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(VendorProfile.this);
                requestQueue.add(stringRequest);
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
                Intent i = new Intent(VendorProfile.this, VendorDashboard.class);
                i.putExtra("vendor_name", vendorName);
                i.putExtra("vendor_number", vendorNum);
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
                Intent i = new Intent(VendorProfile.this, ExploreKaduna.class);
                i.putExtra("vendor_name", vendorName);
                i.putExtra("vendor_number", vendorNum);
                i.putExtra("code", "vendor");
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VendorProfile.this, HelpDesk.class);
                i.putExtra("vendor_name", vendorName);
                i.putExtra("vendor_number", vendorNum);
                i.putExtra("code", "vendor");
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
