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
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MdaDashboard extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    TextView totalRL, name, uniqueId, txtpaid, txtendorsed, txtaudited;
    ArrayList<GroupRow> parentList = new ArrayList<>();
    ArrayList<GroupRow> showTheseParentList = new ArrayList<>();
    ArrayList<String> Array_rl_num;
    ArrayList<String> Array_description;
    ArrayList<String> Array_shortcut;
    ArrayList<String> Array_payee;
    ArrayList<String> Array_paymentstatus;
    ArrayList<String> Array_amount;
    ArrayList<String> Array_date;
    int total_rl;
    LinearLayout home, explore_kd, helpdesk, profile, more;
    SharedPreferences sharedpreferences1_mda, sharedPreferences_rl_mda, sharedPreferences_description_mda, sharedPreferences_shortcut_mda, sharedPreferences_payee_mda, sharedPreferences_payment_mda, sharedPreferences_amount_mda, sharedPreferences_date_mda;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mda_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        totalRL = findViewById(R.id.totalRL);
        name = findViewById(R.id.name);
        uniqueId = findViewById(R.id.uniqueid);
        txtpaid = findViewById(R.id.paid);
        txtendorsed = findViewById(R.id.endorsed);
        txtaudited = findViewById(R.id.audited);


        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(MdaDashboard.this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView naviview = findViewById(R.id.navigationview);
        if (naviview != null) {
            setupDrawerContent(naviview);
        }

        Intent i = getIntent();
        Array_rl_num = i.getExtras().getStringArrayList("rl_num");
        Array_description = i.getExtras().getStringArrayList("description");
        Array_shortcut = i.getExtras().getStringArrayList("shortcut");
        Array_payee = i.getExtras().getStringArrayList("payee");
        Array_paymentstatus = i.getExtras().getStringArrayList("paymentstatus");
        Array_amount = i.getExtras().getStringArrayList("amount");
        Array_date = i.getExtras().getStringArrayList("date");
        final String mda_name = i.getStringExtra("mda_name");
        final String mda_dimension_code = i.getStringExtra("mda_dimension_code");
        final String mda_code = i.getStringExtra("mda_code");
        final String mda_email = i.getStringExtra("mda_email");
        String queried = i.getStringExtra("queried");
        String paid = i.getStringExtra("paid");
        String endorsed = i.getStringExtra("endorsed");
        String audited = i.getStringExtra("audited");
        total_rl = i.getIntExtra("total_rl", 0);

        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.explore:
//                        Intent i = new Intent(MdaDashboard.this, ExploreKaduna.class);
//                        i.putExtra("vendor_name", mda_name);
//                        i.putExtra("vendor_number", mda_code);
//                        i.putExtra("code", "mda");
//                        startActivity(i);
//                        break;
//                    case R.id.help:
//                        Intent j = new Intent(MdaDashboard.this, HelpDesk.class);
//                        j.putExtra("vendor_name", mda_name);
//                        j.putExtra("vendor_number", mda_code);
//                        j.putExtra("code", "mda");
//                        startActivity(j);
//                        break;
//                    case R.id.profile:
//                        Intent k = new Intent(MdaDashboard.this, MdaProfile.class);
//                        k.putExtra("code", "mda");
//                        startActivity(k);
//                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });





        //using shared preference to store all fields
        sharedpreferences1_mda = getSharedPreferences("My Preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences1_mda.edit();
        editor.putString("vendor_number", mda_code);
        editor.putString("vendor_name", mda_name);
        editor.putString("vendor_email", mda_email);
        editor.putString("paid", paid);
        editor.putString("endorsed", endorsed);
        editor.putString("audited", audited);
        editor.putInt("total_rl", total_rl);
        editor.commit();

        //shared pref for rl_number
        sharedPreferences_rl_mda = getSharedPreferences("preference_rl", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences_rl_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor2.putString("rl_" + j, Array_rl_num.get(j));
        }
        editor2.commit();

        //shared pref for description
        sharedPreferences_description_mda = getSharedPreferences("preference_description", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences_description_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor3.putString("description_" + j, Array_description.get(j));
        }
        editor3.commit();

        //shared pref for shortcut
        sharedPreferences_shortcut_mda = getSharedPreferences("preference_shortcut", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences_shortcut_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor4.putString("shortcut_" + j, Array_shortcut.get(j));
        }
        editor4.commit();

        //shared pref for payee
        sharedPreferences_payee_mda = getSharedPreferences("preference_payee", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedPreferences_payee_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor5.putString("payee_" + j, Array_payee.get(j));
        }
        editor5.commit();

        //shared pref for payment
        sharedPreferences_payment_mda = getSharedPreferences("preference_payment", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedPreferences_payment_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor6.putString("payment_" + j, Array_paymentstatus.get(j));
        }
        editor6.commit();

        //shared pref for amount
        sharedPreferences_amount_mda = getSharedPreferences("preference_amount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor7 = sharedPreferences_amount_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor7.putString("amount_" + j, Array_amount.get(j));
        }
        editor7.commit();

        //shared pref for date
        sharedPreferences_date_mda = getSharedPreferences("preference_date", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor8 = sharedPreferences_date_mda.edit();
        for(int j=0;j<Array_rl_num.size();j++)
        {
            editor8.putString("date_" + j, Array_date.get(j));
        }
        editor8.commit();


        home = findViewById(R.id.home);
        helpdesk = findViewById(R.id.help_desk);
        profile = findViewById(R.id.profile);
        explore_kd = findViewById(R.id.explore_kd);
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

            }
        });
        explore_kd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MdaDashboard.this, ExploreKaduna.class);
                i.putExtra("vendor_name", mda_name);
                i.putExtra("vendor_number", mda_code);
                i.putExtra("code", "mda");
                startActivity(i);
            }
        });
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MdaDashboard.this, HelpDesk.class);
                i.putExtra("vendor_name", mda_name);
                i.putExtra("vendor_number", mda_code);
                i.putExtra("code", "mda");
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MdaDashboard.this, MdaProfile.class);
                i.putExtra("code", "mda");
                startActivity(i);
            }
        });


        parentList = new ArrayList<>();
        showTheseParentList = new ArrayList<>();

        totalRL.setText(String.valueOf(total_rl));
        name.setText(mda_name);
        uniqueId.setText(mda_code);
        txtpaid.setText(paid);
        txtendorsed.setText(endorsed);
        txtaudited.setText(audited);

        displayList();


        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                if(parent.isGroupExpanded(groupPosition))
                {
                    parent.collapseGroup(groupPosition);

                }
                else{
                    parent.expandGroup(groupPosition);

                }
                return true;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
    }

    private void displayList(){
        loadData();

        expandableListView = findViewById(R.id.listview);
        listAdapter = new ExpandableListAdapter(MdaDashboard.this, parentList);
        expandableListView.setAdapter(listAdapter);
    }

    private void loadData() {

        for(int i=0; i<total_rl; i++){
            ArrayList<ItemRow> childRows = new ArrayList<>();
            GroupRow groupRow = null;
            childRows.add(new ItemRow(Array_amount.get(i), Array_date.get(i), Array_shortcut.get(i), Array_paymentstatus.get(i)));
            groupRow = new GroupRow(Array_rl_num.get(i), Array_description.get(i), childRows);
            parentList.add(groupRow);
        }
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
