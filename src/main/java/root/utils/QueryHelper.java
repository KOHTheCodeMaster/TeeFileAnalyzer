package root.utils;

public class QueryHelper {

    public static String getUpdateQueryForModTimeHashSHA256OfTempDirsMap(String canonicalPath, String relativeModTimeHashSHA256) {

        return "UPDATE TempDirsMap " +
                "SET relativeModTimeHashSHA256 = \"" + relativeModTimeHashSHA256 + "\" " +
                "WHERE canonicalPath = \"" + canonicalPath + "\";";

    }

}
