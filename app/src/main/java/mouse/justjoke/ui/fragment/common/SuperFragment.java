package mouse.justjoke.ui.fragment.common;

import android.support.v4.app.Fragment;

import com.android.volley.Request;

import mouse.justjoke.app.AppApplication;
import mouse.justjoke.ui.activity.common.SuperActivity;

/**
 * Created by mouse on 15/3/11.
 */
public class SuperFragment extends Fragment {

    protected void sendRequest(Request request) {
        ((SuperActivity) getActivity()).sendRequest(request);
    }

}
