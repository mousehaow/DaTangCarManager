package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.CarArchive;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/6.
 */

public interface ICarArchiveView {
    void getCarArchiveInfoSuccess(Responce<CarArchive> responce);
}
