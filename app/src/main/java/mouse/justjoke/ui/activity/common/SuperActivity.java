package mouse.justjoke.ui.activity.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.android.volley.Request;

import mouse.justjoke.business.request.RequestManager;

/**
 * Created by mouse on 15/3/11.
 */
public class SuperActivity  extends ActionBarActivity{

    public void sendRequest(Request request){
        RequestManager.addRequest(request);
    }

}
