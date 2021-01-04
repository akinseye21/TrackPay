package com.example.ndif_yemmanuel.trackpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class VendorDashboard extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        totalRL = findViewById(R.id.totalRL);
        name = findViewById(R.id.name);
        uniqueId = findViewById(R.id.uniqueid);
        txtpaid = findViewById(R.id.paid);
        txtendorsed = findViewById(R.id.endorsed);
        txtaudited = findViewById(R.id.audited);

        Intent i = getIntent();
        Array_rl_num = i.getExtras().getStringArrayList("rl_num");
        Array_description = i.getExtras().getStringArrayList("description");
        Array_shortcut = i.getExtras().getStringArrayList("shortcut");
        Array_payee = i.getExtras().getStringArrayList("payee");
        Array_paymentstatus = i.getExtras().getStringArrayList("paymentstatus");
        Array_amount = i.getExtras().getStringArrayList("amount");
        Array_date = i.getExtras().getStringArrayList("date");
        String vendor_num = i.getStringExtra("vendor_number");
        String vendor_name = i.getStringExtra("vendor_name");
        String vendor_email = i.getStringExtra("vendor_email");
        String paid = i.getStringExtra("paid");
        String endorsed = i.getStringExtra("endorsed");
        String audited = i.getStringExtra("audited");
        total_rl = i.getIntExtra("total_rl", 0);


        parentList = new ArrayList<>();
        showTheseParentList = new ArrayList<>();

        totalRL.setText(String.valueOf(total_rl));
        name.setText(vendor_name);
        uniqueId.setText(vendor_num);
        txtpaid.setText(paid);
        txtendorsed.setText(endorsed);
        txtaudited.setText(audited);

        displayList();
//        expandAll();

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

    private void loadData() {

        for(int i=0; i<total_rl; i++){
            ArrayList<ItemRow> childRows = new ArrayList<>();
            GroupRow groupRow = null;
            childRows.add(new ItemRow(Array_amount.get(i), Array_date.get(i), Array_shortcut.get(i), Array_paymentstatus.get(i)));
            groupRow = new GroupRow(Array_rl_num.get(i), Array_description.get(i), childRows);
            parentList.add(groupRow);
        }
    }

    private void expandAll(){
        int count = listAdapter.getGroupCount();
        for(int i=0; i<count; i++){
            expandableListView.expandGroup(i);
        }
    }

    private void displayList(){
        loadData();

        expandableListView = findViewById(R.id.listview);
        listAdapter = new ExpandableListAdapter(VendorDashboard.this, parentList);
        expandableListView.setAdapter(listAdapter);
    }
}
