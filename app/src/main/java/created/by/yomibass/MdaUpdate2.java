package created.by.yomibass;

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
import created.by.yomibass.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MdaUpdate2 extends AppCompatActivity {
    String mda_name, mda_code, dimension_code, email_gotten;
    EditText t_uniqueid, e_mail, p_word, confirm_p_word;
    String uniqueid, email, password, confirmpassword;
    ProgressBar progressBar;
    String gotten_Status, gotten_Name, gotten_DimensionCode, gotten_MdaCode, gotten_Email, gotten_paid, gotten_endorsed, gotten_audited, gotten_Queried;
    int project_len;

    public static final String MDA_UPDATE2 = "https://arrearskdsg.com.ng/mobile/mdaupdate";

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
        setContentView(R.layout.activity_mda_update2);
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

        Intent intent = getIntent();
        mda_name = intent.getStringExtra("mda_name");
        mda_code = intent.getStringExtra("mda_code");
        dimension_code = intent.getStringExtra("dimension_code");
        email_gotten = intent.getStringExtra("email");

        t_uniqueid = findViewById(R.id.uniqueid);
        t_uniqueid.setText(mda_code);
        e_mail = findViewById(R.id.email);
        e_mail.setText(email_gotten);
        p_word = findViewById(R.id.password);
        confirm_p_word = findViewById(R.id.confirmpassword);
        progressBar = findViewById(R.id.progressBar);
    }

    public void dashboard(View view) {
        progressBar.setVisibility(View.VISIBLE);

        uniqueid = t_uniqueid.getText().toString().trim();
        email = e_mail.getText().toString().trim();
        password = p_word.getText().toString().trim();
        confirmpassword = confirm_p_word.getText().toString().trim();

        if (!password.equals(confirmpassword)){
            progressBar.setVisibility(View.GONE);

            p_word.setError("Password do not match");
            confirm_p_word.setError("Password do not match");
        }
        else if(password.isEmpty() || confirmpassword.isEmpty()){
            progressBar.setVisibility(View.GONE);

            Toast.makeText(MdaUpdate2.this, "Password can not be empty", Toast.LENGTH_LONG).show();
        }
        else if(password.length()<5 || confirmpassword.length()<5){
            progressBar.setVisibility(View.GONE);

            p_word.setError("Password length should be more than 5 characters");
            confirm_p_word.setError("Password length should be more than 5 characters");
        }
        else{
            //send to dbase
            StringRequest stringRequest = new StringRequest(Request.Method.POST, MDA_UPDATE2,
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
                                gotten_Name = jsonObject.getString("name");
                                gotten_DimensionCode = jsonObject.getString("dimensioncode");
                                gotten_MdaCode = jsonObject.getString("mdacode");
                                gotten_Email = jsonObject.getString("email");
                                gotten_Queried = jsonObject.getString("queried");
                                gotten_paid = jsonObject.getString("paid");
                                gotten_endorsed = jsonObject.getString("endorsed");
                                gotten_audited = jsonObject.getString("audited");


                                if(gotten_Status.equals("user updated")){
                                    Toast.makeText(MdaUpdate2.this, "Updated successfully", Toast.LENGTH_LONG).show();

                                    Intent mv = new Intent(MdaUpdate2.this, MdaDashboard.class);
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
                                    Toast.makeText(MdaUpdate2.this, "Update failed, please try again", Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(MdaUpdate2.this, "Please check network connectivity", Toast.LENGTH_LONG).show();
                                System.out.println("Error "+e.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(MdaUpdate2.this, "Error! Please try agaim", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("mdacode", mda_code);
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MdaUpdate2.this);
            requestQueue.add(stringRequest);
        }

    }
}
