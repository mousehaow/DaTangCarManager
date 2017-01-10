package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.AlarmCostDetails;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/7.
 */

public interface IAlarmCostDetailsView {
    void getAlarmCostDetailsInfoSuccess(Responce<AlarmCostDetails> responce);
    void loadError();
}
