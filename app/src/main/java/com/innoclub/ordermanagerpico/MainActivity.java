package com.innoclub.ordermanagerpico;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.innoclub.ordermanagerpico.NewOrder.NewOrderMainFragment;
import com.innoclub.ordermanagerpico.Shipping.NewShippingMainFragment;
import com.innoclub.ordermanagerpico.UIFramework.ClickTabsFramework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by presisco on 2016/4/6.
 */

public class MainActivity extends AppCompatActivity {
    ClickTabsFramework mTabsFramework;
    List<ContentPage> mContentPages = new ArrayList<>();
    int defaultTabColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareContentPages();
        defaultTabColor = Color.TRANSPARENT;

        mTabsFramework = new ClickTabsFramework();
        mTabsFramework.setContentItems(getContentFragments());
        mTabsFramework.setDistributeEvenly(true);
        mTabsFramework.setTabLayout(R.layout.click_tabs_item);
        mTabsFramework.setCustomTabDraw(new CustomTabDraw());

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.mainContentFrame, mTabsFramework);
        trans.commit();
    }

    private void prepareContentPages() {
        mContentPages.add(new ContentPage(getResources().getString(R.string.title_new_order_fragment),
                getResources().getColor(R.color.selected_new_order_tab),
                NewOrderMainFragment.newInstance()));

        mContentPages.add(new ContentPage(getResources().getString(R.string.title_new_shipping_fragment),
                getResources().getColor(R.color.selected_new_shipping_tab),
                NewShippingMainFragment.newInstance()));
    }

    private List<Fragment> getContentFragments() {
        List<Fragment> list = new ArrayList<>();
        for (ContentPage item : mContentPages) {
            list.add(item.getFragment());
        }
        return list;
    }

    static class ContentPage {
        final Fragment fragment;
        final String title;
        final int pickedColor;

        ContentPage(String t, int c, Fragment f) {
            title = t;
            pickedColor = c;
            fragment = f;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public String getTitle() {
            return title;
        }

        public int getPickedColor() {
            return pickedColor;
        }
    }

    private class CustomTabDraw implements ClickTabsFramework.TabDraw {
        @Override
        public void initDraw(View v, int pos) {
            v.setBackgroundColor(defaultTabColor);
            ((TextView) v.findViewById(R.id.tabTitle)).setText(mContentPages.get(pos).getTitle());
        }

        @Override
        public void onClickedDraw(View last, int lastpos, View now, int pos) {
            if (last != null && lastpos != -1) {
                last.setBackgroundColor(defaultTabColor);
            }
            now.setBackgroundColor(mContentPages.get(pos).getPickedColor());
        }
    }
}
