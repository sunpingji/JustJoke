package mouse.justjoke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import mouse.justjoke.R;
import mouse.justjoke.app.Constant;
import mouse.justjoke.business.request.SuperRequest;
import mouse.justjoke.business.result.Feed;
import mouse.justjoke.business.result.HotResponse;
import mouse.justjoke.ui.fragment.common.SuperFragment;
import mouse.justjoke.utils.log.Slog;

/**
 * Created by mouse on 15/3/12.
 */
public class HotFragment extends SuperFragment {

    private static final String TAG = HotFragment.class.getSimpleName();

    private Button btnSend;

    private SuperRequest request;

    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        init();
    }

    private void initView() {
        btnSend = (Button) getView().findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(request);
            }
        });
    }

    private void init() {
//        request = new JsonObjectRequest(Constant.API_9GAG + "0", null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                Gson gson = new Gson();
//                Feed.FeedRequestData response = gson.fromJson(jsonObject.toString(), Feed.FeedRequestData.class);
//
//                Slog.d(TAG,"response "+jsonObject.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Slog.d(TAG, "error");
//            }
//        });
        request = new SuperRequest(Constant.API_9GAG + "0", Feed.FeedRequestData.class, new Response.Listener() {
            @Override
            public void onResponse(Object o) {
                Slog.d(TAG, "response " + o.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Slog.d(TAG, "onErrorResponse " + volleyError.toString());
            }
        });

    }
}
