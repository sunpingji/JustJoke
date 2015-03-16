package mouse.justjoke.business.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import mouse.justjoke.app.AppApplication;
import mouse.justjoke.business.bean.Feed;

/**
 * Created by mouse on 15/3/16.
 */
public class DBManager {
    private static DBManager ourInstance = new DBManager();

    public static DBManager getInstance() {
        return ourInstance;
    }

    private DBManager() {
        helper = new DBHelper(AppApplication.context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    private DBHelper helper;
    private SQLiteDatabase db;


    public void insertOrReplaceItem(Feed feed) {
        if (feed == null) return;

        if (db != null) {
            db.execSQL("replace into feed" + "(id,caption,link,normal_image,large_image,vote) values ('"
                    + feed.id + "','"
                    + feed.caption + "','"
                    + feed.link + "','"
                    + feed.images.normal + "','"
                    + feed.images.large + "','"
                    + feed.votes.count + "')");
        }
    }


    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
