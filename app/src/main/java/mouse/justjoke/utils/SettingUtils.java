

package mouse.justjoke.utils;

import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mouse.justjoke.app.AppApplication;
import mouse.justjoke.utils.log.Slog;

public class SettingUtils {

    private static final String TAG = SettingUtils.class.getSimpleName();

    public static final String FILE_LAST_HOT_FEED = "file_last_hot_feed";

    public static String DIRECTORY_PATH = StorageUtils.getCacheDirectory(AppApplication.context).getPath();


    /**
     * Save Serializable object
     *
     * @param fileName
     * @param object
     */
    public static void saveObject(String fileName, Object object) {
        try {
            File file = new File(DIRECTORY_PATH + fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream bytetOut = new FileOutputStream(file);
            ObjectOutputStream outer = new ObjectOutputStream(bytetOut);
            outer.writeObject(object);
            outer.flush();
            outer.close();
        } catch (Exception e) {
            Slog.e(TAG, "saveObject Exception " + e.getMessage());
        }
    }

    public static void clearObject(String fileName) {
        try {
            File file = new File(DIRECTORY_PATH + fileName);
            if (file.exists()) {
                file.delete();
                Slog.e(TAG, "delete file success");
            }
        } catch (Exception e) {
            Slog.e(TAG, "clearObject Exception " + e.getMessage());
        }
    }

    /**
     * Read Serializable object
     *
     * @param fileName
     * @return
     */
    public static Object readObject(String fileName) {
        Object object = null;
        try {
            File f = new File(DIRECTORY_PATH + fileName);
            FileInputStream byteOut = new FileInputStream(f);
            ObjectInputStream out = new ObjectInputStream(byteOut);
            object = out.readObject();
            out.close();
            byteOut.close();
        } catch (Exception e) {
            Slog.e(TAG, "readObject Exception " + e.getMessage());
        }
        return object;
    }


}
