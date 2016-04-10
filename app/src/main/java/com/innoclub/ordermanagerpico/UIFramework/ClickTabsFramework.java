package com.innoclub.ordermanagerpico.UIFramework;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.innoclub.ordermanagerpico.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClickTabsFramework#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClickTabsFramework extends Fragment {
    public static final String TAG = ClickTabsFramework.class.getSimpleName();
    private String mWorkingMode = "";
    private LinearLayout mClickTabsContainer;
    private FragmentManager mFragmentManager;
    private TabDraw mCustomTabDraw;
    private List<Fragment> mContentItems = new ArrayList<>();
    private int mTabLayout = 0;
    private boolean isDistributeEvenly = false;
    private View lastTab = null;
    private int lastTabPos = -1;

    public ClickTabsFramework() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClickTabsFramework.
     */
    // TODO: Rename and change types and number of parameters
    public static ClickTabsFramework newInstance() {
        ClickTabsFramework fragment = new ClickTabsFramework();
        return fragment;
    }

    public void setContentItems(List<Fragment> items) {
        mContentItems = items;
    }

    public void setDistributeEvenly(boolean isEven) {
        isDistributeEvenly = isEven;
    }

    public void setTabLayout(int id) {
        mTabLayout = id;
    }

    public void setCustomTabDraw(TabDraw draw) {
        mCustomTabDraw = draw;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.click_tabs_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mClickTabsContainer = new LinearLayout(getContext());
        mClickTabsContainer.setBackgroundColor(Color.RED);
        HorizontalScrollView scrollView = (HorizontalScrollView) view.findViewById(R.id.click_tabs_scroll);
        Log.d(TAG, "tabs scroll width:" + scrollView.getWidth());
        if (isDistributeEvenly) {
            scrollView.addView(mClickTabsContainer, scrollView.getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            scrollView.addView(mClickTabsContainer, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        TabClickListener tabClickListener = new TabClickListener();
        for (int i = 0; i < mContentItems.size(); ++i) {
            View tabView = LayoutInflater.from(getContext()).inflate(R.layout.click_tabs_item, mClickTabsContainer, false);
            tabView.setTag(i);
            tabView.setOnClickListener(tabClickListener);
            if (isDistributeEvenly) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                lp.width = 0;
                lp.weight = 1;
            }
            mCustomTabDraw.initDraw(tabView, i);
            mClickTabsContainer.addView(tabView);
        }
        goTab(0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void goTab(int pos) {
        View now = mClickTabsContainer.findViewWithTag(pos);
        mCustomTabDraw.onClickedDraw(lastTab, lastTabPos, now, pos);
        FragmentTransaction trans = mFragmentManager.beginTransaction();
        trans.replace(R.id.contentFrame, mContentItems.get(pos));
        trans.commit();
        lastTabPos = pos;
        lastTab = now;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment item : mContentItems) {
            item.onActivityResult(requestCode, resultCode, data);
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public interface TabDraw {

        public void initDraw(View v, int pos);

        /**
         * call when tab@pos is clicked
         *
         * @param last    view of last clicked tab
         * @param lastpos pos of last clicked tab
         * @param now     view of clicked tab
         * @param pos     pos of clicked tab
         */
        public void onClickedDraw(View last, int lastpos, View now, int pos);
    }

    private class TabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            if (lastTabPos == pos) {
                return;
            }
            goTab(pos);
        }
    }
}
