package created.by.yomibass;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;

import created.by.yomibass.R;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffDashboard extends AppCompatActivity {

    LinearLayout home, explore_kd, helpdesk, profile, more;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    CircleImageView userimage;
    TextView staff_name, mda_type;
    TextView firstname, surname, dob, marital_status, religion, gender, phonenum, email, address, state_of_origin, local_govt, emp_status, department, duty_station;
    TextView staff_emp_status, staff_department, staff_duty_station, staff_designation, staff_cadre, staff_gradelevel;
    TextView bank_account, bank_name, bvn_number, pension_number, pension_admin_number, gross_pay, net_pay;
    TextView updateProfile;
    TextView staffName, mdaType;


    RelativeLayout contact_info, contact_full_info;
    RelativeLayout general_info, rel_geninfo;
    RelativeLayout job_info, job_full_info;
    RelativeLayout salary_info;
    LinearLayout salary_full_info;
    LinearLayout generate_payslip;
    Button btnchange, btnchangejob, btnchangesalary, btnchangegeninfo;

    String emp_email, emp_no, cadre, type, lga, gradelevel, jobtitle, first_name, lastname, designation, empstatus, basic_salary, dutystation, addres, phon, dobirth, sex, maritalstatus;
    String state, religon, bvn, bankname, acctnum, pennumb, passport;
    String dobirth2;

//    ExpandableListView expandableListView;
//    ExpandableListAdapterStaff listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



//        mv.putExtra("status", gotten_Status);
//        mv.putExtra("employee_email", employee_email);
//        mv.putExtra("employee_cadre", employee_cadre);
//        mv.putExtra("type", type);
//        mv.putExtra("lga", lga);
//        mv.putExtra("employee_gradelevel", employee_gradelevel);
//        mv.putExtra("employee_jobtitle", job_title);
//        mv.putExtra("first_name", first_name);
//        mv.putExtra("last_name", last_name);
//        mv.putExtra("designation", designation);
//        mv.putExtra("employmentstatus", employmentStatus);
//        mv.putExtra("basic_salary", basic_salary);
//        mv.putExtra("duty_station", dutyStation);
//        mv.putExtra("address", address);
//        mv.putExtra("phone", phon);
//        mv.putExtra("dob", dob);
//        mv.putExtra("sex", sex);
//        mv.putExtra("marital_status", marital_status);
//        mv.putExtra("state", state);
//        mv.putExtra("religion", religion);
//        mv.putExtra("bvn", bvn);
//        mv.putExtra("bank_name", bank_name);
//        mv.putExtra("account_number", acct_num);
//        mv.putExtra("pension_number", pension_number);

        Intent i = getIntent();
        emp_email = i.getExtras().getString("employee_email");
        emp_no = i.getExtras().getString("employee_no");
        cadre = i.getExtras().getString("employee_cadre");
        type = i.getExtras().getString("type");
        lga = i.getExtras().getString("lga");
        gradelevel = i.getExtras().getString("employee_gradelevel");
        jobtitle = i.getExtras().getString("employee_jobtitle");
        first_name = i.getExtras().getString("first_name");
        lastname = i.getExtras().getString("last_name");
        designation = i.getExtras().getString("designation");
        empstatus = i.getExtras().getString("employmentstatus");
        basic_salary = i.getExtras().getString("basic_salary");
        dutystation = i.getExtras().getString("duty_station");
        addres = i.getExtras().getString("address");
        phon = i.getExtras().getString("phone");
        dobirth = i.getExtras().getString("dob");
        dobirth2 = dobirth.substring(0, 10);
        sex = i.getExtras().getString("sex");
        maritalstatus = i.getExtras().getString("marital_status");
        state = i.getExtras().getString("state");
        religon = i.getExtras().getString("religion");
        bvn = i.getExtras().getString("bvn");
        bankname = i.getExtras().getString("bank_name");
        acctnum = i.getExtras().getString("account_number");
        pennumb = i.getExtras().getString("pension_number");
        passport = i.getExtras().getString("passport");



        staff_name = findViewById(R.id.staffname);
        mda_type = findViewById(R.id.mda_type);

        generate_payslip = findViewById(R.id.generate_payslip);
        generate_payslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Presently unavailable", Toast.LENGTH_LONG).show();
            }
        });

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
        state_of_origin = findViewById(R.id.state);
        local_govt = findViewById(R.id.LGA);
        staff_emp_status = findViewById(R.id.emp_status);
        staff_department = findViewById(R.id.department);
        staff_duty_station = findViewById(R.id.duty_station);
        staff_designation = findViewById(R.id.designation);
        staff_cadre = findViewById(R.id.cadre);
        staff_gradelevel = findViewById(R.id.gradelevel);
        bank_account = findViewById(R.id.bank_account);
        bank_name = findViewById(R.id.bank_name);
        bvn_number = findViewById(R.id.bvn_number);
        pension_number = findViewById(R.id.pension_number);
        pension_admin_number = findViewById(R.id.pension_admin_number);
        gross_pay = findViewById(R.id.gross_pay);
        net_pay = findViewById(R.id.net_pay);

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


//        Toast.makeText(getApplicationContext(), "passport = "+passport, Toast.LENGTH_LONG).show();
//        try{
//            URL url = new URL(passport);
//            Bitmap img = StringToBitMap(passport);
//            Bitmap  image=BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            userimage.setImageBitmap(img);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



//        updateProfile = findViewById(R.id.update_profile);
//
//        updateProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(StaffDashboard.this, StaffProfileUpdate.class);
//                i.putExtra("firstname", first_name);
//                i.putExtra("lastname", lastname);
//                i.putExtra("email", emp_email);
//                i.putExtra("employee_no", emp_no);
//                startActivity(i);
//            }
//        });



        //set the views
        staff_name.setText("Name: "+first_name+" "+lastname);
        mda_type.setText(gradelevel);
        firstname.setText(first_name);
        surname.setText(lastname);
        marital_status.setText(maritalstatus);
        religion.setText(religon);
        dob.setText(dobirth2);
        gender.setText(sex);
        phonenum.setText(phon);
        email.setText(emp_email);
        address.setText(addres);
        firstname.setText(first_name);
        state_of_origin.setText(state);
        local_govt.setText(lga);
        staff_emp_status.setText(empstatus);
        staff_department.setText("");
        staff_duty_station.setText(dutystation);
        staff_designation.setText(designation);
        staff_cadre.setText(cadre);
        staff_gradelevel.setText(gradelevel);
        bank_account.setText(acctnum);
        bank_name.setText(bankname);
        bvn_number.setText(bvn);
        pension_number.setText(pennumb);
        pension_admin_number.setText("XXXXXXXX");
        gross_pay.setText("XXXXXXX");
        net_pay.setText("XXXXXXX");


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

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
