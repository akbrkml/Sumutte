package io.realm;


import android.util.JsonReader;
import io.realm.exceptions.RealmException;
import io.realm.internal.RealmJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.plapsstudio.sumutte.model.LatLngModel;
import com.plapsstudio.sumutte.model.LocationModel;

class RealmJsonImpl
    implements RealmJson {

    @Override
    public <E extends RealmObject> void populateUsingJsonObject(E obj, JSONObject json)
        throws JSONException {
        String classQualifiedName = (obj.realm != null) ? obj.getClass().getSuperclass().getName() : obj.getClass().getName();
        if (classQualifiedName.equals(LocationModel.class.getName())) {
            LocationModelRealmProxy.populateUsingJsonObject((LocationModel) obj, json);
        } else if (classQualifiedName.equals(LatLngModel.class.getName())) {
            LatLngModelRealmProxy.populateUsingJsonObject((LatLngModel) obj, json);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + classQualifiedName);
        }
    }

    @Override
    public <E extends RealmObject> void populateUsingJsonStream(E obj, JsonReader reader)
        throws IOException {
        String classQualifiedName = (obj.realm != null) ? obj.getClass().getSuperclass().getName() : obj.getClass().getName();
        if (classQualifiedName.equals(LocationModel.class.getName())) {
            LocationModelRealmProxy.populateUsingJsonStream((LocationModel) obj, reader);
        } else if (classQualifiedName.equals(LatLngModel.class.getName())) {
            LatLngModelRealmProxy.populateUsingJsonStream((LatLngModel) obj, reader);
        } else {
            throw new RealmException("Could not find the generated proxy class for " + classQualifiedName);
        }
    }

}
