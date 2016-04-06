package com.innoclub.ordermanagerpico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.innoclub.ordermanagerpico.NetworkOperation.LoginTask;

/**
 * Created by presisco on 2016/4/6.
 */
public class LoginActivity extends AppCompatActivity implements LoginTask.OnLoginResult{
    EditText mEditUsername;
    EditText mEditPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditUsername=(EditText)findViewById(R.id.editTextUsername);
        mEditPassword=(EditText)findViewById(R.id.editTextPassword);
    }

    public void onLogin(View v){
        String name=mEditUsername.getText().toString().trim();
        String pwd=mEditPassword.getText().toString().trim();
        if(name.length()==0||pwd.length()==0){
            Toast.makeText(this,getResources().getString(R.string.text_message_empty_input),Toast.LENGTH_SHORT).show();
            return;
        }else{
            new LoginTask(this).execute(new String[]{name,pwd});
        }
    }

    @Override
    public void onLoginSucceed() {
        SharedPreferences.Editor editor=getSharedPreferences(ConstParams.MAIN_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(ConstParams.MAIN_SHARED_PREFERENCE_KEY_LOGGEDIN,true);
        editor.commit();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed(Integer errorCode) {
        if(errorCode==LoginTask.LOGIN_FAILED_NO_USERNAME){
            Toast.makeText(this,getResources().getString(R.string.text_message_wrong_username),Toast.LENGTH_SHORT).show();
        }else if(errorCode==LoginTask.LOGIN_FAILED_WRONG_PASSWORD){
            Toast.makeText(this,getResources().getString(R.string.text_message_wrong_pwd),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getResources().getString(R.string.text_message_unknown_error),Toast.LENGTH_SHORT).show();
        }
    }
}
