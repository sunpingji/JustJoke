package mouse.justjoke.utils;


import java.util.HashMap;

import mouse.justjoke.app.AppApplication;

public class PreferenceHelper {
    private static final HashMap<String, String> keyToName;

    static {
        keyToName = new HashMap<String, String>();
    }

    /**
     * main preference
     */
    // long, timestamp of when the user has logged in,
    // if null then the user has NOT logged in yet
    // value should be obtained from SystemClock.elapsedRealTime()
    public static final String KEY_HAS_SHOW_GUIDE = "has_show_guide";

    static {
        keyToName.put(KEY_HAS_SHOW_GUIDE, "main");
    }


    private static String getNameFromKey(String key) {
        if (null == key || key.length() == 0) {
            throw new IllegalArgumentException("key == null or key is empty");
        }
        String name = keyToName.get(key);
        if (null == name) {
            throw new IllegalArgumentException("no name mapped to to key " + key);
        }
        return name;
    }

//    public static void update() {
//        String file = String.format("/data/data/%s/shared_prefs/%s.xml",
//                CoreApplication.packageName, NAME_LEGACY);
//        if (!FileUtil.isFileExist(file)) {
//            TaoAppLog.Logd("msg", "no need to update shared preference");
//            return;
//        }
//        TaoAppLog.Logd("msg", "update " + file);
//        SharedPreferences sp = AppApplication.context.getSharedPreferences(NAME_LEGACY, 0);
//        Map<String, ?> keys =  sp.getAll();
//        for (Map.Entry<String, ?> entry : keys.entrySet()) {
//            TaoAppLog.Logd("msg", "key = " + entry.getKey() + ", value = " + String.valueOf(entry.getValue()));
//            try {
//                Object value = entry.getValue();
//                if (value instanceof String) {
//                    setUserPref(entry.getKey(), (String) value);
//                } else if (value instanceof Boolean) {
//                    setUserPref(entry.getKey(), (Boolean) value);
//                } else if (value instanceof Integer) {
//                    setUserPref(entry.getKey(), (Integer) value);
//                } else if (value instanceof Long) {
//                    setUserPref(entry.getKey(), (Long) value);
//                }
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            }
//        }
//        sp.edit().clear().commit();
//        FileUtil.deleteFile(file);
//        TaoAppLog.Logd("msg", "update done");
//    }

    public static boolean containsKey(String key) {
        return AppApplication.context.getSharedPreferences(
                getNameFromKey(key), 0).contains(key);
    }

    public static boolean getUserPref(String key, boolean defaultBoolean) {
        return AppApplication.context.getSharedPreferences(
                getNameFromKey(key), 0).getBoolean(key, defaultBoolean);
    }

    public static int getUserPref(String key, int defaultInt) {
        return AppApplication.context.getSharedPreferences(
                getNameFromKey(key), 0).getInt(key, defaultInt);
    }

    public static long getUserPref(String key, long defaultLong) {
        return AppApplication.context.getSharedPreferences(
                getNameFromKey(key), 0).getLong(key, defaultLong);
    }

    public static String getUserPref(String key, String defaultString) {
        return AppApplication.context.getSharedPreferences(
                getNameFromKey(key), 0).getString(key, defaultString);
    }

    //确定最小支持的sdk，如果为9则改为apply FIXME
    public static void setUserPref(String key, boolean value) {
        AppApplication.context.getSharedPreferences(getNameFromKey(key), 0).edit().putBoolean(key, value).apply();
    }

    public static void setUserPref(String key, int value) {
        AppApplication.context.getSharedPreferences(getNameFromKey(key), 0).edit().putInt(key, value).apply();
    }

    public static void setUserPref(String key, long value) {
        AppApplication.context.getSharedPreferences(getNameFromKey(key), 0).edit().putLong(key, value).apply();
    }

    public static void setUserPref(String key, String value) {
        AppApplication.context.getSharedPreferences(getNameFromKey(key), 0).edit().putString(key, value).apply();
    }


}
