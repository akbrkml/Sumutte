package com.plapsstudio.sumutte.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {
    @SerializedName("routes")
    private List<com.plapsstudio.sumutte.json.Route> routeList;

    public List<com.plapsstudio.sumutte.json.Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<com.plapsstudio.sumutte.json.Route> routeList) {
        this.routeList = routeList;
    }
}
