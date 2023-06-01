package com.example.sit708_my_application_01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText fullname, username, password, confirmpassword;

    Button register;

    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.etFullName);
        username = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        confirmpassword = findViewById(R.id.etConfirmPassword);

        register = findViewById(R.id.btnReigister);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fullname.getText().toString().isEmpty()||
                        username.getText().toString().isEmpty()||
                        password.getText().toString().isEmpty() || confirmpassword.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "Fields can't be blank",Toast.LENGTH_LONG).show();
                }
                else if (password.getText().toString().length()<3)
                {
                    Toast.makeText(getApplicationContext(),
                            "Password must have more than three characters",
                            Toast.LENGTH_LONG).show();

                }
                else if (!password.getText().toString().equals(confirmpassword.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),
                            "Password and confirm password should match",Toast.LENGTH_LONG).show();
                }
                else {
                    UserClass userClass=new UserClass(username.getText().toString(),
                            fullname.getText().toString(),
                            password.getText().toString());
                    if (dbHelper.CreateNewUser((userClass))) {

                        Toast.makeText(getApplicationContext(), "User created Successfully", Toast.LENGTH_LONG).show();


                    }else
                    {
                        Toast.makeText(getApplicationContext(),
                                "User creation Failed",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}