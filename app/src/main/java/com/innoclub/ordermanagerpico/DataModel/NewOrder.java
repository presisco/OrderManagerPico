package com.innoclub.ordermanagerpico.DataModel;

/**
 * Created by presisco on 2016/4/6.
 */
public class NewOrder implements Serializable {
    public String mCustomerId;
    public String mProductId;
    public String mPrice;
    public String mSalesmanId;

    public NewOrder(String cid,String pid,String prc,String sid){
        mCustomerId=cid;
        mProductId=pid;
        mPrice=prc;
        mSalesmanId=sid;
    }
}
