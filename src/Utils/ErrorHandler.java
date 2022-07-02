package Utils;

import java.sql.Time;
import java.sql.Timestamp;

public class ErrorHandler {
    private static String message;

    public static String getMessage() {
        return message;
    }

    public static void setError(String m){
        message = m;
    }

}
