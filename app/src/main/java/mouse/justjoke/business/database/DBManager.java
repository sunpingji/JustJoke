package mouse.justjoke.business.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mouse.justjoke.business.bean.Feed;

/**
 * Created by mouse on 15/3/13.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }


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
