package com.example.ndif_yemmanuel.trackpay;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class SignUpLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    public void vendorlogin(View view) {
        Intent mv = new Intent(SignUpLogin.this, VendorLogin.class);
        startActivity(mv);
    }

    public void stafflogin(View view) {
        Intent mv2 = new Intent(SignUpLogin.this, StaffLogin.class);
        startActivity(mv2);
    }

    public void mdalogin(View view) {
        Intent mv3 = new Intent(SignUpLogin.this, MdaLogin.class);
        startActivity(mv3);
    }

    public void vendorsignup1(View view) {
        Intent mv4 = new Intent(SignUpLogin.this, VendorSignup1.class);
        startActivity(mv4);
    }
}
