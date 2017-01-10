package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.AnnualSurveyInfos;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/9.
 */

public interface IAnnualSurveyView {
    void getAnnualSurveyInfosSuccess(Responce<AnnualSurveyInfos> responce, boolean loadMore);
    void loadMoreAnnualSurveyInfosSuccess(Responce<AnnualSurveyInfos> responce, boolean loadMore);
    void loadError();
}
