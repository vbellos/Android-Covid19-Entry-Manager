package com.example.final_exam.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.final_exam.Locale.App;
import com.example.final_exam.Locale.BaseActivity;
import com.example.final_exam.Locale.LocaleHelper;
import com.example.final_exam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import info.hoang8f.android.segmented.SegmentedGroup;

public class StartActivity extends BaseActivity {
    EditText email,password;
    private FirebaseAuth fAuth;
    FirebaseUser fuser;

    SegmentedGroup rdg;

    Context context;
    Resources resources;

    private int easter_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        rdg = findViewById(R.id.lang_group);
        email = findViewById(R.id.email_txt);
        password = findViewById(R.id.passwd_txt);
        String lang = LocaleHelper.getLanguage(this);
        if (lang.equals("en"))
        {
            rdg.check(R.id.english);
        }
        else{
            rdg.check(R.id.greek);
        }

        fAuth = FirebaseAuth.getInstance();
        fuser = fAuth.getCurrentUser();

        if(fuser!=null){
            Intent i=new Intent(StartActivity.this,AdminMainPage.class);
            startActivity(i);
            finish();
        }

        easter_click=0;
    }
    public void login(View view)
    {
        if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),getString(R.string.suc_login),Toast.LENGTH_LONG).show();
                                fuser = fAuth.getCurrentUser();

                                Intent i=new Intent(StartActivity.this,AdminMainPage.class);
                                startActivity(i);
                                finish();
                            }else{
                                new AlertDialog.Builder(StartActivity.this).setTitle(getString(R.string.fail_auth))
                                        .setMessage(getString(R.string.fail_cred)).setCancelable(true).show();
                            }
                        }
                    });
        }else{
            new AlertDialog.Builder(StartActivity.this).setTitle(getString(R.string.fail_auth))
                    .setMessage(getString(R.string.empty_fields)).setCancelable(true).show();
        }
    }


    public void register(View view)
    {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }



    public void SetLangGreek(View view)
    {
        //((App)getApplicationContext()).changeLang("el");

        context = LocaleHelper.setLocale(StartActivity.this, "el");
        resources = context.getResources();
        Intent refresh= new Intent(this, StartActivity.class);
        startActivity(refresh);
    }
    public void SetLangEnglish(View view)
    {
        context = LocaleHelper.setLocale(StartActivity.this, "en");
        resources = context.getResources();
        Intent refresh= new Intent(this, StartActivity.class);
        startActivity(refresh);
    }

    public void onClickTitle(View view)
    {
        easter_click+=1;
        if (easter_click >= 3)
        {
            easter_click = 0;
            Intent i = new Intent(this,EasterEgg.class);
            startActivity(i);
        }
    }

}

