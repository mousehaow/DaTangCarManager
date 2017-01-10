package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.ParkingRecordList;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/6.
 */

public interface IParkingRecordView {
    void getParkingRecordsInfoSuccess(Responce<ParkingRecordList> responce);
}
