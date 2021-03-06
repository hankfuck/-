package com.example.user.hank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class SignUpActivity extends AppCompatActivity {
    EditText uid;
    EditText pwd;
    Button btn;
    String userid,userpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uid=(EditText) findViewById(R.id.account_edit);
        pwd=(EditText) findViewById(R.id.password_edit);
        btn=(Button)findViewById(R.id.signup_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = uid.getText().toString();
                userpwd = pwd.getText().toString();
                ratinglink(userid,userpwd);
            }
        });





    }



    protected void ratinglink(String id,String pwd){
        StringRequest getRequest = new StringRequest("http://120.110.113.104:8080/sign/?id="+id+"&pwd="+pwd,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("OK")){

                            Intent intent = new Intent();
                            intent.setClass(SignUpActivity.this , ChooseActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("name",userid);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if(s.equals("NO"))
                        {
                            Toast toast=Toast.makeText(SignUpActivity.this,"此帳號已被註冊",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        Volley.newRequestQueue(this).add(getRequest);



    }


}