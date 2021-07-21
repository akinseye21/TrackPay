package com.example.ndif_yemmanuel.trackpay;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class MdaLogin extends AppCompatActivity {

    EditText t_email, t_password;
    String email, password;
    ProgressBar progressBar;
    String gotten_Status, gotten_Name, gotten_DimensionCode, gotten_MdaCode, gotten_Email, gotten_paid, gotten_endorsed, gotten_audited, gotten_Queried;
    int project_len;

    public static final String MDA_LOGIN = "https://arrearskdsg.com.ng/mobile/mdalogin2";

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
        setContentView(R.layout.activity_mda_login);
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

        t_email = findViewById(R.id.email);
        t_password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
    }

    public void dashboard(View view) {

        progressBar.setVisibility(View.VISIBLE);

        email = t_email.getText().toString().trim();
        password = t_password.getText().toString().trim();

        if(email.isEmpty()){
            progressBar.setVisibility(View.GONE);
            t_email.setError("Field can not be empty");
        }
        else if(password.isEmpty()){
            progressBar.setVisibility(View.GONE);
            t_password.setError("Field can not be empty");
        }
        else{
            //send to server
//            progressBar.setVisibility(View.VISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, MDA_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            System.out.println("Response = "+response);
//                            Toast.makeText(VendorLogin.this, "Response = "+response, Toast.LENGTH_LONG).show();

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
                                gotten_Name = jsonObject.getString("name");
                                gotten_DimensionCode = jsonObject.getString("dimensioncode");
                                gotten_MdaCode = jsonObject.getString("mdacode");
                                gotten_Email = jsonObject.getString("email");
                                gotten_Queried = jsonObject.getString("queried");
                                gotten_paid = jsonObject.getString("paid");
                                gotten_endorsed = jsonObject.getString("endorsed");
                                gotten_audited = jsonObject.getString("audited");


                                if(gotten_Status.equals("login successful")){
                                    Toast.makeText(MdaLogin.this, "Login successful", Toast.LENGTH_LONG).show();

                                    Intent mv = new Intent(MdaLogin.this, MdaDashboard.class);
                                    //pass all arraylist
                                    mv.putStringArrayListExtra("rl_num", Array_rl_num);
                                    mv.putStringArrayListExtra("description", Array_description);
                                    mv.putStringArrayListExtra("shortcut", Array_shortcut);
                                    mv.putStringArrayListExtra("payee", Array_payee);
                                    mv.putStringArrayListExtra("paymentstatus", Array_paymentStatus);
                                    mv.putStringArrayListExtra("amount", Array_amount);
                                    mv.putStringArrayListExtra("date", Array_date);
                                    //pass all strings
                                    mv.putExtra("mda_name", gotten_Name);
                                    mv.putExtra("mda_dimension_code", gotten_DimensionCode);
                                    mv.putExtra("mda_code", gotten_MdaCode);
                                    mv.putExtra("mda_email", gotten_Email);
                                    mv.putExtra("total_rl", project_len);
                                    mv.putExtra("queried", gotten_Queried);
                                    mv.putExtra("paid", gotten_paid);
                                    mv.putExtra("endorsed", gotten_endorsed);
                                    mv.putExtra("audited", gotten_audited);
                                    startActivity(mv);
                                }else{
                                    Toast.makeText(MdaLogin.this, "Login failed. Please try again", Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(MdaLogin.this, "Login Failed.", Toast.LENGTH_LONG).show();
                                System.out.println("Error "+e.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(MdaLogin.this, "Error! Please try again", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MdaLogin.this);
            requestQueue.add(stringRequest);
        }
    }
}
