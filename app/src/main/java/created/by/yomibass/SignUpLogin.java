package created.by.yomibass;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import created.by.yomibass.R;

public class SignUpLogin extends AppCompatActivity {

    LinearLayout explore, investment, help_desk, chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
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

        explore = findViewById(R.id.explore_kd);
        investment = findViewById(R.id.investment);
        help_desk = findViewById(R.id.help_desk);
        chat = findViewById(R.id.chat);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpLogin.this, ExploreKaduna.class);
                i.putExtra("code", "no user");
                i.putExtra("vendor_name", "Unregistered");
                i.putExtra("vendor_number", "001");
                startActivity(i);
            }
        });

        investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpLogin.this, "Coming soon", Toast.LENGTH_LONG).show();
            }
        });

        help_desk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpLogin.this, HelpDesk.class);
                i.putExtra("code", "no user");
                i.putExtra("vendor_name", "Unregistered");
                i.putExtra("vendor_number", "001");
                startActivity(i);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpLogin.this, LiveChat.class);
                startActivity(i);
            }
        });

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

    public void mdaUpdate(View view) {
        Intent mv5 = new Intent(SignUpLogin.this, MdaUpdate1.class);
        startActivity(mv5);
    }
}
