package mouse.justjoke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import mouse.justjoke.R;
import mouse.justjoke.app.AppApplication;
import mouse.justjoke.app.Constant;
import mouse.justjoke.business.request.SuperRequest;
import mouse.justjoke.business.result.ApiResponse;
import mouse.justjoke.ui.fragment.common.SuperFragment;
import mouse.justjoke.utils.log.Slog;


public class JokeFragment extends SuperFragment implements View.OnClickListener {

    private static final String TAG = JokeFragment.class.getSimpleName();

    private SuperRequest request;

    private TextView tvContent;

    private Button btnRefresh;

    public static JokeFragment newInstance() {
        JokeFragment fragment = new JokeFragment();
        return fragment;
    }

    public JokeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews();
        init();
        initListener();
        sendRequest(request);
    }

    private void initListener() {
        btnRefresh.setOnClickListener(this);
    }

    private void findViews() {
        tvContent = (TextView) this.getView().findViewById(R.id.tv_content);
        btnRefresh = (Button) this.getView().findViewById(R.id.btn_refresh);
    }

    private void init() {
        String url = "http://api.ajaxsns.com/api.php?key=free&appid=0&msg=";
        try {
            String key = URLEncoder.encode("笑话", "utf-8");
            url = url + key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request = new SuperRequest(url, ApiResponse.class, this, this);


    }

    private String refactorWebToLocal(String temp) {
        String tmp = temp;
        String reg = "\\{br\\}";
        tmp = temp.replaceAll(reg, "\n");
        tmp = tmp.replace(getString(R.string.joke_tips), "");
        return tmp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false);
    }

    @Override
    public void onResponse(Object o) {
        super.onResponse(o);
        if (isAdded()) {

            String temp = ((ApiResponse) o).getContent();
            if (TextUtils.isEmpty(temp)) {
                temp = getString(R.string.sorry_for_no_joke);
            } else {
                temp = refactorWebToLocal(temp);
            }

            tvContent.setText(temp);
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                sendRequest(request);
                break;

        }
    }
}
