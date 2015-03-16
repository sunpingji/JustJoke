package mouse.justjoke.ui.activity.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import mouse.justjoke.business.request.RequestManager;

/**
 * Created by mouse on 15/3/11.
 */
public class SuperActivity extends ActionBarActivity implements Response.Listener, Response.ErrorListener {

    protected String getTAG(){
        return getClass().getSimpleName();
    }

    public void sendRequest(Request request) {
        RequestManager.addRequest(request);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(Object o) {

    }
}
