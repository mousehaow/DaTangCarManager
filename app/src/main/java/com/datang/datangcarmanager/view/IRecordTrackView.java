package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.RecordTrack;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/17.
 */

public interface IRecordTrackView {
    void getRecordTrackInfoSuccess(Responce<RecordTrack> responce);
    void loadError();
}
