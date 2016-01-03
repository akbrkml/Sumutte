package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.plapsstudio.sumutte.model.LatLngModel;
import com.plapsstudio.sumutte.model.LocationModel;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;
import io.realm.internal.ColumnType;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationModelRealmProxy extends LocationModel {

    @Override
    public String getId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(Realm.columnIndices.get("LocationModel").get("id"));
    }

    @Override
    public void setId(String value) {
        realm.checkIfValid();
        row.setString(Realm.columnIndices.get("LocationModel").get("id"), (String) value);
    }

    @Override
    public String getAsal() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(Realm.columnIndices.get("LocationModel").get("asal"));
    }

    @Override
    public void setAsal(String value) {
        realm.checkIfValid();
        row.setString(Realm.columnIndices.get("LocationModel").get("asal"), (String) value);
    }

    @Override
    public String getTujuan() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(Realm.columnIndices.get("LocationModel").get("tujuan"));
    }

    @Override
    public void setTujuan(String value) {
        realm.checkIfValid();
        row.setString(Realm.columnIndices.get("LocationModel").get("tujuan"), (String) value);
    }

    @Override
    public String getDistance() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(Realm.columnIndices.get("LocationModel").get("distance"));
    }

    @Override
    public void setDistance(String value) {
        realm.checkIfValid();
        row.setString(Realm.columnIndices.get("LocationModel").get("distance"), (String) value);
    }

    @Override
    public String getDuration() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(Realm.columnIndices.get("LocationModel").get("duration"));
    }

    @Override
    public void setDuration(String value) {
        realm.checkIfValid();
        row.setString(Realm.columnIndices.get("LocationModel").get("duration"), (String) value);
    }

    @Override
    public RealmList<LatLngModel> getLatLngRealmList() {
        return new RealmList<LatLngModel>(LatLngModel.class, row.getLinkList(Realm.columnIndices.get("LocationModel").get("latLngRealmList")), realm);
    }

    @Override
    public void setLatLngRealmList(RealmList<LatLngModel> value) {
        LinkView links = row.getLinkList(Realm.columnIndices.get("LocationModel").get("latLngRealmList"));
        if (value == null) {
            return;
        }
        for (RealmObject linkedObject : (RealmList<? extends RealmObject>) value) {
            links.add(linkedObject.row.getIndex());
        }
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if(!transaction.hasTable("class_LocationModel")) {
            Table table = transaction.getTable("class_LocationModel");
            table.addColumn(ColumnType.STRING, "id");
            table.addColumn(ColumnType.STRING, "asal");
            table.addColumn(ColumnType.STRING, "tujuan");
            table.addColumn(ColumnType.STRING, "distance");
            table.addColumn(ColumnType.STRING, "duration");
            if (!transaction.hasTable("class_LatLngModel")) {
                LatLngModelRealmProxy.initTable(transaction);
            }
            table.addColumnLink(ColumnType.LINK_LIST, "latLngRealmList", transaction.getTable("class_LatLngModel"));
            table.setIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_LocationModel");
    }

    public static void validateTable(ImplicitTransaction transaction) {
        if(transaction.hasTable("class_LocationModel")) {
            Table table = transaction.getTable("class_LocationModel");
            if(table.getColumnCount() != 6) {
                throw new IllegalStateException("Column count does not match");
            }
            Map<String, ColumnType> columnTypes = new HashMap<String, ColumnType>();
            for(long i = 0; i < 6; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }
            if (!columnTypes.containsKey("id")) {
                throw new IllegalStateException("Missing column 'id'");
            }
            if (columnTypes.get("id") != ColumnType.STRING) {
                throw new IllegalStateException("Invalid type 'String' for column 'id'");
            }
            if (!columnTypes.containsKey("asal")) {
                throw new IllegalStateException("Missing column 'asal'");
            }
            if (columnTypes.get("asal") != ColumnType.STRING) {
                throw new IllegalStateException("Invalid type 'String' for column 'asal'");
            }
            if (!columnTypes.containsKey("tujuan")) {
                throw new IllegalStateException("Missing column 'tujuan'");
            }
            if (columnTypes.get("tujuan") != ColumnType.STRING) {
                throw new IllegalStateException("Invalid type 'String' for column 'tujuan'");
            }
            if (!columnTypes.containsKey("distance")) {
                throw new IllegalStateException("Missing column 'distance'");
            }
            if (columnTypes.get("distance") != ColumnType.STRING) {
                throw new IllegalStateException("Invalid type 'String' for column 'distance'");
            }
            if (!columnTypes.containsKey("duration")) {
                throw new IllegalStateException("Missing column 'duration'");
            }
            if (columnTypes.get("duration") != ColumnType.STRING) {
                throw new IllegalStateException("Invalid type 'String' for column 'duration'");
            }
            if(!columnTypes.containsKey("latLngRealmList")) {
                throw new IllegalStateException("Missing column 'latLngRealmList'");
            }
            if(columnTypes.get("latLngRealmList") != ColumnType.LINK_LIST) {
                throw new IllegalStateException("Invalid type 'LatLngModel' for column 'latLngRealmList'");
            }
            if (!transaction.hasTable("class_LatLngModel")) {
                throw new IllegalStateException("Missing table 'class_LatLngModel' for column 'latLngRealmList'");
            }
        }
    }

    public static List<String> getFieldNames() {
        return Arrays.asList("id", "asal", "tujuan", "distance", "duration", "latLngRealmList");
    }

    public static void populateUsingJsonObject(LocationModel obj, JSONObject json)
        throws JSONException {
        boolean standalone = obj.realm == null;
        if (json.has("id")) {
            obj.setId((String) json.getString("id"));
        }
        if (json.has("asal")) {
            obj.setAsal((String) json.getString("asal"));
        }
        if (json.has("tujuan")) {
            obj.setTujuan((String) json.getString("tujuan"));
        }
        if (json.has("distance")) {
            obj.setDistance((String) json.getString("distance"));
        }
        if (json.has("duration")) {
            obj.setDuration((String) json.getString("duration"));
        }
        if (json.has("latLngRealmList")) {
            if (standalone) {
                obj.setLatLngRealmList(new RealmList<com.plapsstudio.sumutte.model.LatLngModel>());
            }
            JSONArray array = json.getJSONArray("latLngRealmList");
            for (int i = 0; i < array.length(); i++) {
                com.plapsstudio.sumutte.model.LatLngModel item = standalone ? new com.plapsstudio.sumutte.model.LatLngModel() : obj.realm.createObject(com.plapsstudio.sumutte.model.LatLngModel.class);
                LatLngModelRealmProxy.populateUsingJsonObject(item, array.getJSONObject(i));
                obj.getLatLngRealmList().add(item);
            }
        }
    }

    public static void populateUsingJsonStream(LocationModel obj, JsonReader reader)
        throws IOException {
        boolean standalone = obj.realm == null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id") && reader.peek() != JsonToken.NULL) {
                obj.setId((String) reader.nextString());
            } else if (name.equals("asal")  && reader.peek() != JsonToken.NULL) {
                obj.setAsal((String) reader.nextString());
            } else if (name.equals("tujuan")  && reader.peek() != JsonToken.NULL) {
                obj.setTujuan((String) reader.nextString());
            } else if (name.equals("distance")  && reader.peek() != JsonToken.NULL) {
                obj.setDistance((String) reader.nextString());
            } else if (name.equals("duration")  && reader.peek() != JsonToken.NULL) {
                obj.setDuration((String) reader.nextString());
            } else if (name.equals("latLngRealmList")  && reader.peek() != JsonToken.NULL) {
                reader.beginArray();
                if (standalone) {
                    obj.setLatLngRealmList(new RealmList<com.plapsstudio.sumutte.model.LatLngModel>());
                }
                while (reader.hasNext()) {
                    com.plapsstudio.sumutte.model.LatLngModel item = standalone ? new com.plapsstudio.sumutte.model.LatLngModel() : obj.realm.createObject(com.plapsstudio.sumutte.model.LatLngModel.class);
                    LatLngModelRealmProxy.populateUsingJsonStream(item, reader);
                    obj.getLatLngRealmList().add(item);
                }
                reader.endArray();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    public static LocationModel copyOrUpdate(Realm realm, LocationModel object, boolean update) {
        LocationModel realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(LocationModel.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstString(pkColumnIndex, object.getId());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new LocationModelRealmProxy();
                realmObject.realm = realm;
                realmObject.row = table.getRow(rowIndex);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object);
        } else {
            return copy(realm, object, update);
        }
    }

    public static LocationModel copy(Realm realm, LocationModel newObject, boolean update) {
        LocationModel realmObject = realm.createObject(LocationModel.class);
        realmObject.setId(newObject.getId() != null ? newObject.getId() : "");
        realmObject.setAsal(newObject.getAsal() != null ? newObject.getAsal() : "");
        realmObject.setTujuan(newObject.getTujuan() != null ? newObject.getTujuan() : "");
        realmObject.setDistance(newObject.getDistance() != null ? newObject.getDistance() : "");
        realmObject.setDuration(newObject.getDuration() != null ? newObject.getDuration() : "");

        RealmList<LatLngModel> latLngRealmListList = newObject.getLatLngRealmList();
        if (latLngRealmListList != null) {
            RealmList<LatLngModel> latLngRealmListRealmList = realmObject.getLatLngRealmList();
            for (int i = 0; i < latLngRealmListList.size(); i++) {
                latLngRealmListRealmList.add(LatLngModelRealmProxy.copyOrUpdate(realm, latLngRealmListList.get(i), update));
            }
        }

        return realmObject;
    }

    static LocationModel update(Realm realm, LocationModel realmObject, LocationModel newObject) {
        realmObject.setAsal(newObject.getAsal() != null ? newObject.getAsal() : "");
        realmObject.setTujuan(newObject.getTujuan() != null ? newObject.getTujuan() : "");
        realmObject.setDistance(newObject.getDistance() != null ? newObject.getDistance() : "");
        realmObject.setDuration(newObject.getDuration() != null ? newObject.getDuration() : "");
        RealmList<LatLngModel> latLngRealmListList = newObject.getLatLngRealmList();
        RealmList<LatLngModel> latLngRealmListRealmList = realmObject.getLatLngRealmList();
        latLngRealmListRealmList.clear();
        if (latLngRealmListList != null) {
            for (int i = 0; i < latLngRealmListList.size(); i++) {
                latLngRealmListRealmList.add(LatLngModelRealmProxy.copyOrUpdate(realm, latLngRealmListList.get(i), true));
            }
        }
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("LocationModel = [");
        stringBuilder.append("{id:");
        stringBuilder.append(getId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{asal:");
        stringBuilder.append(getAsal());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tujuan:");
        stringBuilder.append(getTujuan());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{distance:");
        stringBuilder.append(getDistance());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{duration:");
        stringBuilder.append(getDuration());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{latLngRealmList:");
        stringBuilder.append("RealmList<LatLngModel>[").append(getLatLngRealmList().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationModelRealmProxy aLocationModel = (LocationModelRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aLocationModel.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aLocationModel.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aLocationModel.row.getIndex()) return false;

        return true;
    }

}
