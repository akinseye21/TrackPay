package com.example.ndif_yemmanuel.trackpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class VendorSignup2 extends AppCompatActivity {

    String email, vendornum, name;
    EditText t_uniqueid, t_password, t_confirmpassword;
    String uniqueid, password, confirmpassword;
    ProgressBar progressBar;
    String gotten_Status, gotten_Vendornum, gotten_Name, gotten_Email, gotten_paid, gotten_endorsed, gotten_audited;
    int project_len;

    public static final String VENDOR_UPDATE = "http://arrearskdsg.com.ng/mobile/vupdate";

    ArrayList<String> Array_rl_num = new ArrayList<>();
    ArrayList<String> Array_description = new ArrayList<>();
    ArrayList<String> Array_shortcut = new ArrayList<>();
    ArrayList<String> Array_payee = new ArrayList<>();
    ArrayList<String> Array_paymentStatus = new ArrayList<>();
    ArrayList<String> Array_amount = new ArrayList<>();
    ArrayList<String> Array_date = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        vendornum = intent.getStringExtra("vendor_number");
        email = intent.getStringExtra("vendor_email");
        name = intent.getStringExtra("vendor_name");

//        Toast.makeText(VendorSignup2.this, "Email = "+email, Toast.LENGTH_LONG).show();

        t_uniqueid = findViewById(R.id.uniqueid);
        t_uniqueid.setText(vendornum);
        t_password = findViewById(R.id.password);
        t_confirmpassword = findViewById(R.id.confirmpassword);
        progressBar = findViewById(R.id.progressBar);


    }

    public void dashboard(View view) {

        progressBar.setVisibility(View.VISIBLE);

        uniqueid = t_uniqueid.getText().toString().trim();
        password = t_password.getText().toString().trim();
        confirmpassword = t_confirmpassword.getText().toString().trim();

        if (!password.equals(confirmpassword)){
            progressBar.setVisibility(View.GONE);

            t_password.setError("Password do not match");
            t_confirmpassword.setError("Password do not match");
        }
        else if(password.isEmpty() || confirmpassword.isEmpty()){
            progressBar.setVisibility(View.GONE);

            Toast.makeText(VendorSignup2.this, "Password can not be empty", Toast.LENGTH_LONG).show();
        }
        else if(password.length()<5 || confirmpassword.length()<5){
            progressBar.setVisibility(View.GONE);

            t_password.setError("Password length should be more than 5 characters");
            t_confirmpassword.setError("Password length should be more than 5 characters");
        }
        else{
//            progressBar.setVisibility(View.VISIBLE);
            //send to dbase
            StringRequest stringRequest = new StringRequest(Request.Method.POST, VENDOR_UPDATE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            System.out.println("Response = "+response);
//                            Toast.makeText(VendorSignup2.this, "Response = "+response, Toast.LENGTH_LONG).show();

                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray project_Array = jsonObject.getJSONArray("projects");
                                project_len = project_Array.length();

                                for(int i = 0; i<project_Array.length(); i++){
                                    JSONObject section = project_Array.getJSONObject(i);
                                    String rl_num = section.getString("rl_num");
                                    String description = section.getString("description");
                                    String shortcut = section.getString("shortcut");
                                    String payee = section.getString("payee");
                                    String paymentstatus = section.getString("paymentstatus");
                                    String amount = section.getString("amount");
                                    String date = section.getString("docdate");

                                    Array_rl_num.add(rl_num);
                                    Array_description.add(description);
                                    Array_shortcut.add(shortcut);
                                    Array_payee.add(payee);
                                    Array_paymentStatus.add(paymentstatus);
                                    Array_amount.add(amount);
                                    Array_date.add(date);
                                }

                                gotten_Status = jsonObject.getString("status");
                                gotten_Vendornum = jsonObject.getString("vendor_no");
                                gotten_Name = jsonObject.getString("name");
                                gotten_Email = jsonObject.getString("email");
                                gotten_paid = jsonObject.getString("paid");
                                gotten_endorsed = jsonObject.getString("endorsed");
                                gotten_audited = jsonObject.getString("audited");


                                if(gotten_Status.equals("user updated")){
                                    Toast.makeText(VendorSignup2.this, "Updated successfully", Toast.LENGTH_LONG).show();

                                    Intent mv = new Intent(VendorSignup2.this, VendorDashboard.class);
                                    //pass all arraylist
                                    mv.putStringArrayListExtra("rl_num", Array_rl_num);
                                    mv.putStringArrayListExtra("description", Array_description);
                                    mv.putStringArrayListExtra("shortcut", Array_shortcut);
                                    mv.putStringArrayListExtra("payee", Array_payee);
                                    mv.putStringArrayListExtra("paymentstatus", Array_paymentStatus);
                                    mv.putStringArrayListExtra("amount", Array_amount);
                                    mv.putStringArrayListExtra("date", Array_date);
                                    //pass all strings
                                    mv.putExtra("vendor_number", gotten_Vendornum);
                                    mv.putExtra("vendor_name", gotten_Name);
                                    mv.putExtra("vendor_email", gotten_Email);
                                    mv.putExtra("total_rl", project_len);
                                    mv.putExtra("paid", gotten_paid);
                                    mv.putExtra("endorsed", gotten_endorsed);
                                    mv.putExtra("audited", gotten_audited);
                                    startActivity(mv);
                                }else{
                                    Toast.makeText(VendorSignup2.this, "Update failed, please try again", Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(VendorSignup2.this, "Please check network connectivity", Toast.LENGTH_LONG).show();
                                System.out.println("Error "+e.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(VendorSignup2.this, "Error! Please try agaim", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("email", vendornum);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(VendorSignup2.this);
            requestQueue.add(stringRequest);
        }



    }
}
