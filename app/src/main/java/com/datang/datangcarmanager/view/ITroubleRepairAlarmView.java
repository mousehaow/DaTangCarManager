package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/7.
 */

public interface ITroubleRepairAlarmView {
    void getTroubleRepairAlarmSuccess(Responce<AbnormalSituation> responce, boolean loadMore);
    void loadMoreTroubleRepairAlarmInfoSuccess(Responce<AbnormalSituation> responce, boolean loadMore);
    void loadError();
}
