package com.sbp.manage.network;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface ApiClient {

    @POST
    Flowable<Object> login(String username, String password);
}
