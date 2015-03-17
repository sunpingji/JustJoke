package mouse.justjoke.ui.fragment.common;

import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import mouse.justjoke.app.AppApplication;
import mouse.justjoke.ui.activity.common.SuperActivity;

/**
 * Created by mouse on 15/3/11.
 */
public class SuperFragment extends Fragment implements Response.Listener, Response.ErrorListener {

    protected String getTAG(){
        return this.getClass().getSimpleName();
    }

    protected void sendRequest(Request request) {
        ((SuperActivity) getActivity()).sendRequest(request);
    }


    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(Object o) {

    }
}
