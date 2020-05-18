package com.shooter.kotlinfetchaddress.model;

import com.squareup.moshi.Json;

public class ApiResponse {

    @Json(name = "requestId")
    private String requestId;
    @Json(name = "data")
    private Data data;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}