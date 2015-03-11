package mouse.justjoke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import mouse.justjoke.R;
import mouse.justjoke.app.AppApplication;
import mouse.justjoke.ui.fragment.common.SuperFragment;
import mouse.justjoke.utils.log.Slog;


public class JokeFragment extends SuperFragment implements View.OnClickListener {

    private static final String TAG = JokeFragment.class.getSimpleName();

    private StringRequest stringRequest;

    private String jokeResponse;

    private TextView tvContent;

    private Button btnRefresh;

    public static JokeFragment newInstance() {
        JokeFragment fragment = new JokeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        doRequest();
    }

    private void initListener() {
        btnRefresh.setOnClickListener(this);
    }

    private void findViews() {
        tvContent = (TextView) this.getView().findViewById(R.id.tv_content);
        btnRefresh = (Button) this.getView().findViewById(R.id.btn_refresh);
    }

    private void init() {
        String request = "http://api.ajaxsns.com/api.php?key=free&appid=0&msg=";
        try {
            String key = URLEncoder.encode("笑话", "utf-8");
            request = request + key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jokeResponse = response;
                        tvContent.setText(jokeResponse);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Slog.e("TAG", error.getMessage());
            }
        });
    }

    private void doRequest(){
        AppApplication.getVolleyQueue().add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_refresh:
                doRequest();
                break;

        }
    }
}
