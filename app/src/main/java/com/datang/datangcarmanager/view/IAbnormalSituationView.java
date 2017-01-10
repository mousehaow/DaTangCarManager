package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/7.
 */

public interface IAbnormalSituationView {
    void getAbnormalSituationInfoSuccess(Responce<AbnormalSituation> responce, boolean loadMore);
    void loadMoreAbnormalSituationInfoSuccess(Responce<AbnormalSituation> responce, boolean loadMore);
    void loadError();
}
