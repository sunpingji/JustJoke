package mouse.justjoke.business.request;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import mouse.justjoke.app.AppApplication;

/**
 * Created by mouse on 15/3/12.
 */
public class RequestManager {

    private static RequestQueue requestQueue = Volley.newRequestQueue(AppApplication.context);

    public static void addRequest(Request request) {
        requestQueue.add(request);
    }


}
