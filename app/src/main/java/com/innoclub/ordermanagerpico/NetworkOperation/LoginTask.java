package com.innoclub.ordermanagerpico.NetworkOperation;

import android.os.AsyncTask;

/**
 * Created by presisco on 2016/4/6.
 */
public class LoginTask extends AsyncTask<String,Void,Integer> {
    public final static String TAG=LoginTask.class.getSimpleName();
    public final static Integer LOGIN_SUCCEED=0;
    public final static Integer LOGIN_FAILED_NO_USERNAME=1;
    public final static Integer LOGIN_FAILED_WRONG_PASSWORD=2;
    public interface OnLoginResult{
        public void onLoginFailed(Integer errorCode);
        public void onLoginSucceed();
    }

    private OnLoginResult onLoginResult;

    public LoginTask(OnLoginResult parentListener) {
        super();
        onLoginResult=parentListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        return 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer i) {
        super.onPostExecute(i);
        if(i==LOGIN_SUCCEED){
            onLoginResult.onLoginSucceed();
        }else{
            onLoginResult.onLoginFailed(i);
        }
    }
}
