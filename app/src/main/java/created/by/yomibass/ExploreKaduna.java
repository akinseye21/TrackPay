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

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExploreKaduna extends AppCompatActivity {

    RelativeLayout places_to_visit, restaurant, hotel, shopping;
    LinearLayout home, explore_kd, helpdesk, profile, more;
    TextView name, num,ui;
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

    LinearLayout headLayer;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_kaduna);
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

        Intent i = getIntent();
        final String vendor_name = i.getStringExtra("vendor_name");
        final String vendor_number = i.getStringExtra("vendor_number");
        final String code = i.getStringExtra("code");

        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);
        more = findViewById(R.id.more);

        name = findViewById(R.id.name);
        num = findViewById(R.id.uniqueid);

        name.setText(vendor_name);
        num.setText(vendor_number);
        ui = findViewById(R.id.ui);

        headLayer = findViewById(R.id.headlayer);
        img = findViewById(R.id.img);

        if (code.equals("mda")){
            headLayer.setBackgroundResource(R.drawable.vendor_login_bg);
            img.setImageResource(R.drawable.main_mda);
            explore_kd.setBackgroundColor(Color.parseColor("#b3ccff"));
            toolbar.setBackgroundColor(Color.parseColor("#040e67"));
            num.setTextColor(Color.parseColor("#ffffff"));
            ui.setTextColor(Color.parseColor("#ffffff"));
        }

        if (code.equals("staff")){
            headLayer.setBackgroundResource(R.drawable.vendor_login_bg);
            img.setImageResource(R.drawable.main_mda);
            explore_kd.setBackgroundColor(Color.parseColor("#b3ccff"));
            toolbar.setBackgroundColor(Color.parseColor("#040e67"));
            num.setTextColor(Color.parseColor("#ffffff"));
            ui.setTextColor(Color.parseColor("#ffffff"));
            ui.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
        }

        if (code.equals("no user")){
            headLayer.setBackgroundResource(R.drawable.vendors_dashboard_bg);
            img.setImageResource(R.drawable.main_mda);
            explore_kd.setBackgroundColor(Color.parseColor("#f9a737"));

            home.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
        }

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(ExploreKaduna.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView naviview = findViewById(R.id.navigationview);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        if (naviview != null) {
            setupDrawerContent(naviview);
        }

        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.signout:

                        AlertDialog.Builder builder = new AlertDialog.Builder(ExploreKaduna.this);
                        builder.setTitle("Exit");
                        builder.setMessage("Do you want to exit Trackpay?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(ExploreKaduna.this,  MainActivity.class);
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
                        Intent i = new Intent(ExploreKaduna.this,  LiveChat.class);
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
                if (code.equals("vendor")){
                    Intent i = new Intent(ExploreKaduna.this, VendorDashboard.class);
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
                else if (code.equals("mda")){
                    Intent i = new Intent(ExploreKaduna.this, MdaDashboard.class);
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
                else if(code.equals("no user")){
                    Toast.makeText(ExploreKaduna.this, "Unauthorized access", Toast.LENGTH_LONG).show();
                }

            }
        });
        explore_kd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExploreKaduna.this, HelpDesk.class);
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
                    Intent i = new Intent(ExploreKaduna.this, VendorProfile.class);
                    startActivity(i);
                }
                else if(code.equals("mda")){
                    //go to mda profile
                    Intent i = new Intent(ExploreKaduna.this, MdaProfile.class);
                    startActivity(i);
                }
                else if(code.equals("no user")){
                    Toast.makeText(ExploreKaduna.this, "Register or Login to access", Toast.LENGTH_LONG).show();
                }

            }
        });



        places_to_visit = findViewById(R.id.places_to_visit);
        places_to_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExploreKaduna.this, PlacesToVisit.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("code", code);
                startActivity(i);
            }
        });

        restaurant = findViewById(R.id.restaurants);
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExploreKaduna.this, RestaurantsToVisit.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("code", code);
                startActivity(i);
            }
        });

        hotel = findViewById(R.id.hotel);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExploreKaduna.this, HotelsToVisit.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("code", code);
                startActivity(i);
            }
        });

        shopping = findViewById(R.id.shopping_centres);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExploreKaduna.this, ShoppingCentresToVisit.class);
                i.putExtra("vendor_name", vendor_name);
                i.putExtra("vendor_number", vendor_number);
                i.putExtra("code", code);
                startActivity(i);
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
