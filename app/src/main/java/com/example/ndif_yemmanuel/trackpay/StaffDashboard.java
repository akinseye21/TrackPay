package com.example.ndif_yemmanuel.trackpay;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffDashboard extends AppCompatActivity {

    LinearLayout home, explore_kd, helpdesk, profile, more;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    CircleImageView userimage;
    TextView staff_name, mda_type;
    TextView firstname, surname, dob, marital_status, religion, gender, phonenum, email, address, emp_status, department, duty_station;

    RelativeLayout contact_info, contact_full_info;
    RelativeLayout general_info, rel_geninfo;
    RelativeLayout job_info, job_full_info;
    RelativeLayout salary_info;
    LinearLayout salary_full_info;
    Button btnchange, btnchangejob, btnchangesalary, btnchangegeninfo;

//    ExpandableListView expandableListView;
//    ExpandableListAdapterStaff listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        staff_name = findViewById(R.id.staffname);
        mda_type = findViewById(R.id.mda_type);

        userimage = findViewById(R.id.userimage);
        firstname = findViewById(R.id.firstname);
        surname = findViewById(R.id.surname);
        dob = findViewById(R.id.dob);
        marital_status = findViewById(R.id.marital_status);
        religion = findViewById(R.id.religion);
        gender = findViewById(R.id.gender);
        phonenum = findViewById(R.id.phonenum);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        emp_status = findViewById(R.id.emp_status);
        department = findViewById(R.id.department);
        duty_station = findViewById(R.id.duty_station);

        contact_info = findViewById(R.id.contact_info);
        contact_full_info = findViewById(R.id.contact_full_info);
        btnchange = findViewById(R.id.btnchange);

        general_info = findViewById(R.id.general_info);
        rel_geninfo = findViewById(R.id.rel_geninfo);
        btnchangegeninfo = findViewById(R.id.btnchangegen_info);

        job_info = findViewById(R.id.job_info);
        job_full_info = findViewById(R.id.job_full_info);
        btnchangejob = findViewById(R.id.btnchangejob);

        salary_info = findViewById(R.id.salary_info);
        salary_full_info = findViewById(R.id.salary_full_info);
        btnchangesalary = findViewById(R.id.btnchangesalary);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(StaffDashboard.this, mDrawerLayout, R.string.open, R.string.close);
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
                    case R.id.signout:

                        AlertDialog.Builder builder = new AlertDialog.Builder(StaffDashboard.this);
                        builder.setTitle("Exit");
                        builder.setMessage("Do you want to exit Trackpay?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(StaffDashboard.this,  MainActivity.class);
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
                        Intent i = new Intent(StaffDashboard.this,  LiveChat.class);
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




        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        profile.setVisibility(View.GONE);
        explore_kd = findViewById(R.id.explore_kd);
        more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        explore_kd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StaffDashboard.this, ExploreKaduna.class);
//                i.putExtra("vendor_name", mda_name);
//                i.putExtra("vendor_number", mda_code);
                i.putExtra("code", "staff");
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StaffDashboard.this, HelpDesk.class);
//                i.putExtra("vendor_name", mda_name);
//                i.putExtra("vendor_number", mda_code);
                i.putExtra("code", "staff");
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(StaffDashboard.this, MdaProfile.class);
//                i.putExtra("code", "staff");
//                startActivity(i);
            }
        });


        contact_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnchange.getText().toString().equals("+")){
                    contact_full_info.setVisibility(View.VISIBLE);
                    btnchange.setText("-");
                }else{
                    contact_full_info.setVisibility(View.GONE);
                    btnchange.setText("+");
                }

            }
        });

        general_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnchangegeninfo.getText().toString().equals("+")){
                    rel_geninfo.setVisibility(View.VISIBLE);
                    btnchangegeninfo.setText("-");
                }else{
                    rel_geninfo.setVisibility(View.GONE);
                    btnchangegeninfo.setText("+");
                }
            }
        });


        job_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnchangejob.getText().toString().equals("+")){
                    job_full_info.setVisibility(View.VISIBLE);
                    btnchangejob.setText("-");
                }else{
                    job_full_info.setVisibility(View.GONE);
                    btnchangejob.setText("+");
                }

            }
        });

        salary_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnchangesalary.getText().toString().equals("v")){
                    salary_full_info.setVisibility(View.GONE);
                    btnchangesalary.setText("+");
                    btnchangesalary.setBackgroundResource(R.drawable.rounded_white);
                }else{
                    salary_full_info.setVisibility(View.VISIBLE);
                    btnchangesalary.setText("v");
                    btnchangesalary.setBackgroundResource(R.drawable.rounded_blue);
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

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
