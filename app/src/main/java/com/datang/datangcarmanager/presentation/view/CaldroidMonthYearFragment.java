package com.datang.datangcarmanager.presentation.view;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.view
 * @class describe
 * @anthor toby
 * @time 16/11/19 下午8:24
 * @change
 * @chang time
 * @class describe
 */
public class CaldroidMonthYearFragment extends CaldroidFragment {

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        return new CaldroidGridAdapter(getActivity(), month, year, null, extraData);
    }
}
