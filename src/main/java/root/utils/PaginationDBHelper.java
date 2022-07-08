package root.utils;

import com.google.gson.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class PaginationDBHelper {

    private long limit;
    private long skip;

    public PaginationDBHelper() {
        this.limit = 500;
    }

    public PaginationDBHelper(long limit) {
        this.limit = limit;
    }

    /**
     * Convert given resultSet i.e. select query result into its json equivalent JsonArray
     *
     * @param resultSet ResultSet is the result of Select Query. It contains list of records and columns
     *                  which needs to be converted into its json equivalent JsonArray
     * @return Array of each record in Json equivalent JsonArray i.e. returns JsonArray which is simply an array
     * of JsonObject representing each record of the table denoted by resultSet
     * @throws SQLException SQL Exception
     */
    public static ArrayList<String> resultSetToArrayList(final ResultSet resultSet) throws SQLException {

        final ArrayList<String> list = new ArrayList<>();

        //  Iterate all the rows
        while (resultSet.next()) {
            //  Each row contains only single column
            list.add(resultSet.getString(1));
        }

        if (list.isEmpty()) return null;
        return list;
    }

    /**
     * Convert given resultSet i.e. select query result into its json equivalent JsonArray
     *
     * @param resultSet ResultSet is the result of Select Query. It contains list of records and columns
     *                  which needs to be converted into its json equivalent JsonArray
     * @return Array of each record in Json equivalent JsonArray i.e. returns JsonArray which is simply an array
     * of JsonObject representing each record of the table denoted by resultSet
     * @throws SQLException SQL Exception
     */
    public static JsonArray resultSetToJsonArray(final ResultSet resultSet) throws SQLException {

        final ResultSetMetaData metaData = resultSet.getMetaData();
        final JsonArray jsonArray = new JsonArray();    // JsonArray is a Gson built-in class to hold JSON arrays
        boolean isResultSetEmpty = true;

        //  Iterate all the rows
        while (resultSet.next()) {
            isResultSetEmpty = false;
            //  Convert each row to jsonElement and add it to jsonArray
            jsonArray.add(resultSetRowToJsonObject(resultSet, metaData));
        }

        if (isResultSetEmpty) return null;

        return jsonArray;
    }

    /**
     * Convert the given resultSet (corresponding to individual record of the table) into its Json equivalent JsonObject
     *
     * @param resultSet ResultSet representing Individual record of a table
     * @param metaData  ResultSetMetaData representing the meta data of the record
     * @return Json equivalent JsonElement which corresponds to the given record of the table
     * @throws SQLException SQL Exception
     */
    private static JsonElement resultSetRowToJsonObject(final ResultSet resultSet, final ResultSetMetaData metaData)
            throws SQLException {

        final int columnCount = metaData.getColumnCount();
        final JsonObject jsonObject = new JsonObject(); // Every result set row is a JsonObject equivalent

        // JDBC uses 1-based index
        for (int i = 1; i <= columnCount; i++) {
//            jsonObject.add(metaData.getColumnName(i), fieldToJsonElement(resultSet, metaData, i));
            String str = resultSet.getString(i);
            JsonElement jsonElement = str != null ? new JsonPrimitive(str) : JsonNull.INSTANCE;
            jsonObject.add(metaData.getColumnName(i), jsonElement);
        }

        return jsonObject;
    }

    public void resetSkip() {
        skip = 0;
    }

    public void updateSkipWithLimit() {
        skip += limit;
    }

    /**
     * Return select query with limit clause for selecting sub-set of records to avoid overflow.
     * Also, increment skip with limit after every method call.
     *
     * @param tableName Name of the table for which records needs to be selected
     * @return Return query for selecting sub-set of records
     */
    public String generateSubsetSelectQuery(String tableName, String orderByColumn) {

        //  Generate select query with skip & limit params
        String query = String.format("SELECT * FROM %1$s ORDER BY %2$s LIMIT %3$d, %4$d",
                tableName, orderByColumn, skip, limit);

        //  Update skip for next round by adding limit i.e. number of records selected
        skip += limit;

        return query;
    }

    private static JsonElement fieldToJsonElement(final ResultSet resultSet, final ResultSetMetaData metaData, final int column)
            throws SQLException {

        //  Reference: https://stackoverflow.com/questions/42636611/mysql-resultset-into-gson-array

        final int columnType = metaData.getColumnType(column);
        final Optional<JsonElement> jsonElement;
        // Process each SQL type mapping a value to a JSON tree equivalent
        switch (columnType) {
            case Types.BIT:
            case Types.TINYINT:
            case Types.SMALLINT:
                throw new UnsupportedOperationException("TODO: " + JDBCType.valueOf(columnType));
            case Types.INTEGER:
                // resultSet.getInt() returns 0 in case of null, so it must be extracted with getObject and cast, then converted to a JsonPrimitive
                jsonElement = Optional.ofNullable((Integer) resultSet.getObject(column)).map(JsonPrimitive::new);
                break;
            case Types.BIGINT:
            case Types.FLOAT:
            case Types.REAL:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL:
            case Types.CHAR:
            case Types.VARCHAR:
                jsonElement = Optional.ofNullable(resultSet.getString(column)).map(JsonPrimitive::new);
                break;
            case Types.LONGVARCHAR:
            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
            case Types.NULL:
            case Types.OTHER:
            case Types.JAVA_OBJECT:
            case Types.DISTINCT:
            case Types.STRUCT:
            case Types.ARRAY:
            case Types.BLOB:
            case Types.CLOB:
            case Types.REF:
            case Types.DATALINK:
            case Types.BOOLEAN:
            case Types.ROWID:
            case Types.NCHAR:
            case Types.NVARCHAR:
            case Types.LONGNVARCHAR:
            case Types.NCLOB:
            case Types.SQLXML:
            case Types.REF_CURSOR:
            case Types.TIME_WITH_TIMEZONE:
            case Types.TIMESTAMP_WITH_TIMEZONE:
            default:
                throw new UnsupportedOperationException("Unknown type: " + columnType);
        }
        // If the optional value is missing, assume it's a null
        return jsonElement.orElse(JsonNull.INSTANCE);

    }

    public long getLimit() {
        return limit;
    }

    public long getSkip() {
        return skip;
    }
}
