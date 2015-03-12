package mouse.justjoke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.VolleyError;

import mouse.justjoke.R;
import mouse.justjoke.app.Constant;
import mouse.justjoke.business.adapter.MyAdapter;
import mouse.justjoke.business.request.SuperRequest;
import mouse.justjoke.business.result.Feed;
import mouse.justjoke.ui.fragment.common.SuperFragment;
import mouse.justjoke.utils.log.Slog;

/**
 * Created by mouse on 15/3/12.
 */
public class HotFragment extends SuperFragment {

    private static final String TAG = HotFragment.class.getSimpleName();

    private Button btnSend;

    private SuperRequest request;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


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

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String[] strings = new String[]{"A","B","C","D","E","F","G","H","I","J"};
        mAdapter = new MyAdapter(strings);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void init() {
        request = new SuperRequest(Constant.API_9GAG + "0", Feed.FeedRequestData.class, this, this);
    }

    @Override
    public void onResponse(Object o) {
        super.onResponse(o);
        Slog.d(TAG, "onResponse" + o.toString());
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        Slog.d(TAG, "onErrorResponse" + volleyError.toString());
    }
}
