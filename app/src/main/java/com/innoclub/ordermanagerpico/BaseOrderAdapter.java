package com.innoclub.ordermanagerpico;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by presisco on 2016/4/6.
 */
public class BaseOrderAdapter extends RecyclerView.Adapter<BaseOrderAdapter.BaseViewHolder> {
    private int mFooterResId;
    private int mHeaderResId;

    public interface ActionHub{
        public void onItemClicked(int position);
        public void onItemLongClicked(int position);
        public void onHeaderSwipeRefresh();
        public void onFooterLoadMore();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setFooterAndHeader(int footerId,int headerId){
        mFooterResId=footerId;
        mHeaderResId=headerId;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
