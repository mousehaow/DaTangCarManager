package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.RealTimeDetect;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/5.
 */

public interface ICarStateView {
    void getAllCarInfoSuccess(Responce<RealTimeDetect> responce);
}
