package com.wega.gankclient.remote.api;

import com.wega.gankclient.data.GankBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by lwj on 2017/12/20 00:05.
 */

public interface GankService {

    String ANDROID="data/Android/";
    String IOS="data/iOS/";
    String GIRL="data/福利/";
//    String ANDROID="http://gank.io/api/data/Android/";

    @GET(ANDROID+"{page_rows}/{page}")
    Observable<GankBean> getAndriod(
            @Path("page_rows") int page_rows ,
                                    @Path("page") int page);
    @GET(IOS+"{page_rows}/{page}")
    Observable<GankBean> getIOS(
            @Path("page_rows") int page_rows ,
            @Path("page") int page);
    @GET(GIRL+"{page_rows}/{page}")
    Observable<GankBean> getGirl(
            @Path("page_rows") int page_rows ,
            @Path("page") int page);
}
