package com.innoclub.ordermanagerpico.NetworkOperation;

import android.os.AsyncTask;

import com.innoclub.ordermanagerpico.DataModel.NewOrder;

/**
 * Created by presisco on 2016/4/6.
 */
public class SubmitOrderTask extends AsyncTask<NewOrder,Void,Integer> {

    public final static String TAG=SubmitOrderTask.class.getSimpleName();
    public final static Integer SUBMIT_SUCCEED=0;
    public final static Integer SUBMIT_FAILED_NO_RESPONSE=1;
    public final static Integer SUBMIT_FAILED_ILLEGAL=2;
    public interface OnSubmitOrderResult{
        public void onSubmitFailed(Integer errorCode);
        public void onSubmitComplete();
    }

    private OnSubmitOrderResult onSubmitOrderResult;

    public SubmitOrderTask(OnSubmitOrderResult onResult) {
        super();
        onSubmitOrderResult=onResult;
    }

    @Override
    protected Integer doInBackground(NewOrder... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer==SUBMIT_SUCCEED){
            onSubmitOrderResult.onSubmitComplete();
        }else{
            onSubmitOrderResult.onSubmitFailed(integer);
        }
    }
}
