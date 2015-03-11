package mouse.justjoke.app;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by mouse on 15/3/11.
 */
public class AppApplication extends Application {

    private static AppApplication app;

    private RequestQueue requestQueue;

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        app = this;
        context = this;
        AVOSCloud.initialize(this, Constant.Lean_APP_ID, Constant.Lean_APP_KEY);
        requestQueue = Volley.newRequestQueue(this);
    }

    public static RequestQueue getVolleyQueue() {
        return app.requestQueue;
    }

    public static Context getContext() {
        return app.context;
    }
}
