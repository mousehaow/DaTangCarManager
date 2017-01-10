package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.DriveRecord;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/5.
 */

public interface IDriveRecordView {
    void getDriveRecordsSuccess(Responce<DriveRecord> responce);
}
