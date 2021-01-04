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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VendorSignup1 extends AppCompatActivity {

    EditText email;
    String Email;
    String gotten_Status, gotten_Email, gotten_Vendornum, gotten_Name;
    ProgressBar progressBar;

    public static final String VENDOR_SIGNUP = "http://arrearskdsg.com.ng/mobile/vlogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup1);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email = findViewById(R.id.edtEmail);
        progressBar = findViewById(R.id.progressBar);

    }

    public void vendorsignup2(View view) {

        Email = email.getText().toString().trim();
        if(Email.isEmpty()){
            email.setError("Email can not be empty");
        }
        else{
            progressBar.setVisibility(View.VISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, VENDOR_SIGNUP,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            System.out.println("Response = "+response);
//                            Toast.makeText(VendorSignup1.this, "Response = "+response, Toast.LENGTH_LONG).show();

                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                gotten_Status = jsonObject.getString("status");
                                gotten_Vendornum = jsonObject.getString("vendor_no");
                                gotten_Name = jsonObject.getString("name");
                                gotten_Email = jsonObject.getString("email");

                                if(gotten_Status.equals("user found")){
                                    Intent mv = new Intent(VendorSignup1.this, VendorSignup2.class);
                                    mv.putExtra("vendor_number", gotten_Vendornum);
                                    mv.putExtra("vendor_name", gotten_Name);
                                    mv.putExtra("vendor_email", gotten_Email);
                                    startActivity(mv);
                                }else{
                                    Toast.makeText(VendorSignup1.this, "User not found", Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(VendorSignup1.this, "Please check network connectivity", Toast.LENGTH_LONG).show();
                                System.out.println("Error "+e.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(VendorSignup1.this, "Error! Please try agaim", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("email", Email);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(VendorSignup1.this);
            requestQueue.add(stringRequest);

        }


    }
}
