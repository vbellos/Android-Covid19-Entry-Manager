package com.example.final_exam.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_exam.Locale.BaseActivity;
import com.example.final_exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends BaseActivity {

    Button register;
    EditText email,passwd;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth= FirebaseAuth.getInstance();
        email = findViewById(R.id.reg_emailTXT);
        passwd = findViewById(R.id.reg_passwdTXT);

        register = findViewById(R.id.reg_signupBTN);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (email.getText().toString().isEmpty()||passwd.getText().toString().isEmpty()) {
                    new AlertDialog.Builder(RegisterActivity.this)

                            .setMessage(getString(R.string.empty_fields))
                            .setCancelable(true)
                            .show();
                }
                else if(passwd.getText().toString().length()<8)
                {
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle(getString(R.string.weak_pass))
                            .setMessage(getString(R.string.weak_pass_txt))
                            .setCancelable(true)
                            .show();
                }
                else{
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),passwd.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),getString(R.string.reg_succ),Toast.LENGTH_LONG).show();
                                        finish();

                                    }else{
                                        new AlertDialog.Builder(RegisterActivity.this)
                                                .setTitle(getString(R.string.db_error))
                                                .setMessage(getString(R.string.db_error_txt))
                                                .setCancelable(true)
                                                .show();
                                    }
                                }
                            });
                }
            }

        });
    }
}