package com.cs160.joleary.catnip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.Gravity;

import java.util.List;

/**
 * Created by chonyi on 3/4/16.
 */
public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List mRows;

    public SampleGridPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    // A simple container for static data in each page
    private static class Page {
        // static resources
        int titleRes;
        int textRes;
        int cardGravity = Gravity.BOTTOM;
        boolean expansionEnabled = true;
        float expansionFactor = 1.0f;
        int expansionDirection = CardFragment.EXPAND_DOWN;

        public Page(int titleRes, int textRes, int gravity) {
            this.titleRes = titleRes;
            this.textRes = textRes;
            this.cardGravity = gravity;
        }

    }

    // Create a static set of pages in a 2D array
    private final Page[][] PAGES = {
            {
                    new Page(R.string.input1, R.string.input2,
                            Gravity.CENTER_VERTICAL),
                    new Page(R.string.input3, R.string.input4,
                            Gravity.CENTER_VERTICAL),
                    new Page(R.string.input5, R.string.input6,
                            Gravity.CENTER_VERTICAL),
            },

    };
        @Override
        public Fragment getFragment(int row, int col) {
            Page page = PAGES[row][col];
            String title = page.titleRes != 0 ? mContext.getString(page.titleRes) : null;
            String text = page.textRes != 0 ? mContext.getString(page.textRes) : null;
            CardFragment fragment = CardFragment.create(title, text);
            // Advanced settings
            fragment.setCardGravity(page.cardGravity);
            fragment.setExpansionEnabled(page.expansionEnabled);
            fragment.setExpansionDirection(page.expansionDirection);
            fragment.setExpansionFactor(page.expansionFactor);
            return fragment;
        }

        @Override
        public int getRowCount() {
            return PAGES.length;
        }

        @Override
        public int getColumnCount(int rowNum) {
            return PAGES[rowNum].length;
        }
    }