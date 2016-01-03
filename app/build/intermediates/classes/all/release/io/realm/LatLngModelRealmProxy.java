package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.plapsstudio.sumutte.model.LatLngModel;
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

public class LatLngModelRealmProxy extends LatLngModel {

    @Override
    public double getLat() {
        realm.checkIfValid();
        return (double) row.getDouble(Realm.columnIndices.get("LatLngModel").get("lat"));
    }

    @Override
    public void setLat(double value) {
        realm.checkIfValid();
        row.setDouble(Realm.columnIndices.get("LatLngModel").get("lat"), (double) value);
    }

    @Override
    public double getLng() {
        realm.checkIfValid();
        return (double) row.getDouble(Realm.columnIndices.get("LatLngModel").get("lng"));
    }

    @Override
    public void setLng(double value) {
        realm.checkIfValid();
        row.setDouble(Realm.columnIndices.get("LatLngModel").get("lng"), (double) value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if(!transaction.hasTable("class_LatLngModel")) {
            Table table = transaction.getTable("class_LatLngModel");
            table.addColumn(ColumnType.DOUBLE, "lat");
            table.addColumn(ColumnType.DOUBLE, "lng");
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_LatLngModel");
    }

    public static void validateTable(ImplicitTransaction transaction) {
        if(transaction.hasTable("class_LatLngModel")) {
            Table table = transaction.getTable("class_LatLngModel");
            if(table.getColumnCount() != 2) {
                throw new IllegalStateException("Column count does not match");
            }
            Map<String, ColumnType> columnTypes = new HashMap<String, ColumnType>();
            for(long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }
            if (!columnTypes.containsKey("lat")) {
                throw new IllegalStateException("Missing column 'lat'");
            }
            if (columnTypes.get("lat") != ColumnType.DOUBLE) {
                throw new IllegalStateException("Invalid type 'double' for column 'lat'");
            }
            if (!columnTypes.containsKey("lng")) {
                throw new IllegalStateException("Missing column 'lng'");
            }
            if (columnTypes.get("lng") != ColumnType.DOUBLE) {
                throw new IllegalStateException("Invalid type 'double' for column 'lng'");
            }
        }
    }

    public static List<String> getFieldNames() {
        return Arrays.asList("lat", "lng");
    }

    public static void populateUsingJsonObject(LatLngModel obj, JSONObject json)
        throws JSONException {
        boolean standalone = obj.realm == null;
        if (json.has("lat")) {
            obj.setLat((double) json.getDouble("lat"));
        }
        if (json.has("lng")) {
            obj.setLng((double) json.getDouble("lng"));
        }
    }

    public static void populateUsingJsonStream(LatLngModel obj, JsonReader reader)
        throws IOException {
        boolean standalone = obj.realm == null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("lat") && reader.peek() != JsonToken.NULL) {
                obj.setLat((double) reader.nextDouble());
            } else if (name.equals("lng")  && reader.peek() != JsonToken.NULL) {
                obj.setLng((double) reader.nextDouble());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    public static LatLngModel copyOrUpdate(Realm realm, LatLngModel object, boolean update) {
        return copy(realm, object, false);
    }

    public static LatLngModel copy(Realm realm, LatLngModel newObject, boolean update) {
        LatLngModel realmObject = realm.createObject(LatLngModel.class);
        realmObject.setLat(newObject.getLat());
        realmObject.setLng(newObject.getLng());
        return realmObject;
    }

    static LatLngModel update(Realm realm, LatLngModel realmObject, LatLngModel newObject) {
        realmObject.setLat(newObject.getLat());
        realmObject.setLng(newObject.getLng());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("LatLngModel = [");
        stringBuilder.append("{lat:");
        stringBuilder.append(getLat());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lng:");
        stringBuilder.append(getLng());
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
        LatLngModelRealmProxy aLatLngModel = (LatLngModelRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aLatLngModel.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aLatLngModel.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aLatLngModel.row.getIndex()) return false;

        return true;
    }

}
