package created.by.yomibass;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import created.by.yomibass.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StaffLogin extends AppCompatActivity {

    Button login;
    ProgressBar progressBar;
    EditText uid, phone;
    String user_id, phone_num;
    String gotten_Status, employee_no, employee_name, employee_email, employee_cadre, type, lga, employee_gradelevel, job_title;
    String first_name, last_name, designation, employmentStatus, basic_salary, dutyStation, address, phon, dob, sex, marital_status, state, religion, bvn, bank_name, acct_num, pension_number, passport;


    public static final String STAFF_LOGIN = "https://arrearskdsg.com.ng/mobile/employees_login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
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

        progressBar = findViewById(R.id.progressBar);
        uid = findViewById(R.id.uid);
        phone = findViewById(R.id.phone);

        login = findViewById(R.id.btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                user_id = uid.getText().toString().trim();
                phone_num = phone.getText().toString().trim();

                if(user_id.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    uid.setError("Field can not be empty");
                }
                else if(phone_num.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    phone.setError("Field can not be empty");
                }
                else{
                    //send to server to check validity
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, STAFF_LOGIN,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar.setVisibility(View.GONE);

                                    System.out.println("Response = "+response);
//                            Toast.makeText(VendorLogin.this, "Response = "+response, Toast.LENGTH_LONG).show();

                                    try{
                                        JSONObject jsonObject = new JSONObject(response);
                                        gotten_Status = jsonObject.getString("status");
                                        employee_email = jsonObject.getString("email");
                                        employee_cadre = jsonObject.getString("cadre");
                                        type = jsonObject.getString("mda");
                                        lga = jsonObject.getString("lga");
                                        employee_gradelevel = jsonObject.getString("gradelevel");
                                        job_title = jsonObject.getString("jobtitle");
                                        employee_no = jsonObject.getString("Employee_no");

                                        first_name = jsonObject.getString("firstname");
                                        last_name = jsonObject.getString("lastname");
                                        designation = jsonObject.getString("designation");
                                        employmentStatus = jsonObject.getString("employmentStatus");
                                        basic_salary = jsonObject.getString("basic_salary");
                                        dutyStation = jsonObject.getString("dutyStation");
                                        address = jsonObject.getString("address");
                                        phon = jsonObject.getString("phone");
                                        dob = jsonObject.getString("dob");
                                        sex = jsonObject.getString("sex");
                                        marital_status = jsonObject.getString("marital_status");
                                        state = jsonObject.getString("state");
                                        religion = jsonObject.getString("religion");
                                        bvn = jsonObject.getString("bvn");
                                        bank_name = jsonObject.getString("bankname");
                                        acct_num = jsonObject.getString("account_number");
                                        pension_number = jsonObject.getString("pension_number");
                                        passport = jsonObject.getString("passport");


                                        if(gotten_Status.equals("login successful")){
                                            Toast.makeText(StaffLogin.this, "login successful", Toast.LENGTH_LONG).show();

                                            Intent mv = new Intent(StaffLogin.this, StaffDashboard.class);
                                            //pass all strings
                                            mv.putExtra("status", gotten_Status);
                                            mv.putExtra("employee_email", employee_email);
                                            mv.putExtra("employee_cadre", employee_cadre);
                                            mv.putExtra("employee_no", employee_no);
                                            mv.putExtra("type", type);
                                            mv.putExtra("lga", lga);
                                            mv.putExtra("employee_gradelevel", employee_gradelevel);
                                            mv.putExtra("employee_jobtitle", job_title);
                                            mv.putExtra("first_name", first_name);
                                            mv.putExtra("last_name", last_name);
                                            mv.putExtra("designation", designation);
                                            mv.putExtra("employmentstatus", employmentStatus);
                                            mv.putExtra("basic_salary", basic_salary);
                                            mv.putExtra("duty_station", dutyStation);
                                            mv.putExtra("address", address);
                                            mv.putExtra("phone", phon);
                                            mv.putExtra("dob", dob);
                                            mv.putExtra("sex", sex);
                                            mv.putExtra("marital_status", marital_status);
                                            mv.putExtra("state", state);
                                            mv.putExtra("religion", religion);
                                            mv.putExtra("bvn", bvn);
                                            mv.putExtra("bank_name", bank_name);
                                            mv.putExtra("account_number", acct_num);
                                            mv.putExtra("pension_number", pension_number);
                                            mv.putExtra("passport", passport);
                                            startActivity(mv);
                                        }else{
                                            Toast.makeText(StaffLogin.this, "Login failed. Please try again", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                    catch (JSONException e){
                                        e.printStackTrace();
                                        Toast.makeText(StaffLogin.this, "Login Failed.", Toast.LENGTH_LONG).show();
                                        System.out.println("Error "+e.getMessage());
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(StaffLogin.this, "Error! Please try again", Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String, String> params = new HashMap<>();
                            params.put("uid", user_id);
                            params.put("phone", phone_num);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(StaffLogin.this);
                    requestQueue.add(stringRequest);
                }

//                Intent i = new Intent(StaffLogin.this, StaffDashboard.class);
//                startActivity(i);
            }
        });
    }
}
