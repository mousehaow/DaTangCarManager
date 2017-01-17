package com.datang.datangcarmanager.api;

import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.AlarmCostDetails;
import com.datang.datangcarmanager.model.AnnualSurveyInfos;
import com.datang.datangcarmanager.model.CarArchive;
import com.datang.datangcarmanager.model.CarTrack;
import com.datang.datangcarmanager.model.Department;
import com.datang.datangcarmanager.model.DriveRecord;
import com.datang.datangcarmanager.model.ParkingRecordList;
import com.datang.datangcarmanager.model.RealTimeDetect;
import com.datang.datangcarmanager.model.RecordTrack;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.SearchCarInfo;
import com.datang.datangcarmanager.model.VehicleList;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.model.Vehicle;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.api
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午9:10
 * @change
 * @chang time
 * @class describe
 */
public interface CarApi {

    @POST("TinyOBDClient/service/BusinessService")
    Observable<Responce<VehicleList>> getEquipmentCars(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<Department>> getDepartmentsInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<RealTimeDetect>> getCarStateInfo(@Body PostRequest request);

    @POST("TinyOBDClient/service/BusinessService")
    Observable<Responce<DriveRecord>> getDriveRecordsInfo(@Body PostRequest request);

    @POST("TinyOBDClient/service/BusinessService")
    Observable<Responce<ParkingRecordList>> getParkingRecordsInfo(@Body PostRequest request);

    @POST("TinyOBDClient/service/BusinessService")
    Observable<Responce<RecordTrack>> getRecordTrackInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<CarTrack>> getCarTrackInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<CarArchive>> getCarArchiveInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<SearchCarInfo>> getSearchCarInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<AbnormalSituation>> getAbnormalSituationInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<AlarmCostDetails>> getAlarmCostDetailsInfo(@Body PostRequest request);

    @POST("saasapi/saasapi")
    Observable<Responce<AnnualSurveyInfos>> getAnnualSurveyInfo(@Body PostRequest request);
}
