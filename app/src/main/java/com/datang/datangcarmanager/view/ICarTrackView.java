package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.CarTrack;
import com.datang.datangcarmanager.model.Responce;

/**
 * Created by toby on 2017/1/6.
 */

public interface ICarTrackView {
    void getCarTrackInfoSuccess(Responce<CarTrack> responce);
}
