package mouse.justjoke.utils.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by mouse on 15/3/11.
 */
public class Slog {

    public static boolean isShowLog = true;

    public static void i(String tag, String msg) {
        if (checkNeedShow(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (checkNeedShow(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (checkNeedShow(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (checkNeedShow(msg)) {
            Log.w(tag, msg);
        }
    }

    private static boolean checkNeedShow(String msg) {
        if (!TextUtils.isEmpty(msg) && isShowLog) {
            return true;
        } else {
            return false;
        }
    }

}
