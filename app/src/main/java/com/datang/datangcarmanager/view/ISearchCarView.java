package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.SearchCarInfo;

/**
 * Created by toby on 2017/1/7.
 */

public interface ISearchCarView {
    void getSearchCarInfoSuccess(Responce<SearchCarInfo> responce, boolean loadMore);
    void loadMoreSearchCarInfoSuccess(Responce<SearchCarInfo> responce, boolean loadMore);
    void loadError();
}
