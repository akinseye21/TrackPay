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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MdaUpdate1 extends AppCompatActivity {
    EditText mdacode;
    String MdaCode;
    String status, name_, dimension_code, mda_code, email;
    ProgressBar progressBar;

    public static final String MDA_UPDATE = "https://arrearskdsg.com.ng/mobile/mdalogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mda_update1);
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

        mdacode = findViewById(R.id.edtEmail);
        progressBar = findViewById(R.id.progressBar);
    }

    public void nxt(View view) {
        MdaCode = mdacode.getText().toString().trim();
        if(MdaCode.isEmpty()){
            mdacode.setError("code can not be empty");
        }
        else{
            progressBar.setVisibility(View.VISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, MDA_UPDATE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            System.out.println("Response = "+response);
//                            Toast.makeText(VendorSignup1.this, "Response = "+response, Toast.LENGTH_LONG).show();

                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                status = jsonObject.getString("status");
                                dimension_code = jsonObject.getString("dimensioncode");
                                name_ = jsonObject.getString("name");
                                mda_code = jsonObject.getString("mdacode");
                                email = jsonObject.getString("email");

                                if(status.equals("user found")){
                                    Intent mv = new Intent(MdaUpdate1.this, MdaUpdate2.class);
                                    mv.putExtra("mda_name", name_);
                                    mv.putExtra("mda_code", mda_code);
                                    mv.putExtra("dimension_code", dimension_code);
                                    mv.putExtra("email", email);
                                    startActivity(mv);
                                }else{
                                    Toast.makeText(MdaUpdate1.this, "User not found", Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(MdaUpdate1.this, "Please check network connectivity", Toast.LENGTH_LONG).show();
                                System.out.println("Error "+e.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(MdaUpdate1.this, "Error! Please try agaim", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("mdacode", MdaCode);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MdaUpdate1.this);
            requestQueue.add(stringRequest);

        }
    }
}
