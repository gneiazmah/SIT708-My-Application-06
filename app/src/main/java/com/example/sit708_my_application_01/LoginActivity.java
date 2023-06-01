package com.example.sit708_my_application_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    Button login;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txt_L_UserId);
        password = findViewById(R.id.txt_L_Password);

        login = findViewById(R.id.btn_L_Login);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<UserClass> userDetails =
                        dbHelper.ValidLogin(username.getText().toString(),
                                password.getText().toString());

                if (userDetails.size() != 0) {
                    UserClass user = userDetails.get(0);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
                }

            }



        });
    }
}
