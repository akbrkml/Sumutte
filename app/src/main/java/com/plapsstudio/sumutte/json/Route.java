package com.plapsstudio.sumutte.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Route {
    @SerializedName("legs")
    private List<com.plapsstudio.sumutte.json.Legs> legsList;

    public List<Legs> getLegsList() {
        return legsList;
    }

    public void setLegsList(List<com.plapsstudio.sumutte.json.Legs> legsList) {
        this.legsList = legsList;
    }
}
