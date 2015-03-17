package mouse.justjoke.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import mouse.justjoke.R;
import mouse.justjoke.app.Constant;
import mouse.justjoke.business.adapter.UltraAdapter;
import mouse.justjoke.business.request.SuperRequest;
import mouse.justjoke.business.bean.Feed;
import mouse.justjoke.ui.fragment.common.SuperFragment;
import mouse.justjoke.utils.SettingUtils;
import mouse.justjoke.utils.log.Slog;

/**
 * Created by mouse on 15/3/12.
 */
public class HotFragment extends SuperFragment {

    private static final String TAG = HotFragment.class.getSimpleName();

    private static final String PAGE_START = "0";

    private SuperRequest request;

    private UltimateRecyclerView recyclerView;
    private UltraAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Boolean isRefresh = false;


    private String count = "0";

    private List<Feed> list = new ArrayList<>();


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
        initData();
        nextPage();
    }

    private void initData() {
        List<Feed> temp = (List<Feed>) SettingUtils.readObject(SettingUtils.FILE_LAST_HOT_FEED);
        if (temp != null && temp.size() > 0) {
            list = temp;
            mAdapter.refreshData(list);
        }
    }

    private void initView() {
        recyclerView = (UltimateRecyclerView) getView().findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new UltraAdapter(list, getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setRefreshing(true);
                count = "0";
                nextPage();
            }
        });

        recyclerView.enableLoadmore();
        mAdapter.setCustomLoadMoreView(LayoutInflater.from(getActivity())
                .inflate(R.layout.custom_bottom_progressbar, null));

        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int lastVisibleItem, int totalItemCount) {
                if (lastVisibleItem >= totalItemCount - 4 && !isRefresh) {
                    nextPage();
                }
            }
        });

    }

    private void nextPage() {
        isRefresh = true;
        request = new SuperRequest(Constant.API_9GAG + count, Feed.FeedRequestData.class, this, this);
        sendRequest(request);
    }

    @Override
    public void onResponse(Object o) {
        super.onResponse(o);
        if (o != null) {
            Feed.FeedRequestData data = (Feed.FeedRequestData) o;
            if (count.equals(PAGE_START)) {
                list.clear();
            }
            list.addAll(data.data);
            count = data.getPage();
            mAdapter.refreshData(list);
        }
        isRefresh = false;
        recyclerView.setRefreshing(false);
        Slog.d(TAG, "onResponse" + o.toString());
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        isRefresh = false;
        recyclerView.setRefreshing(false);
        if (null == list || list.size() == 0) {
            count = PAGE_START;
            nextPage();
        }
        Slog.d(TAG, "onErrorResponse" + volleyError.toString());
    }

    @Override
    public void onDestroy() {
        saveNewlyList();
        super.onDestroy();
    }

    private void saveNewlyList() {
        if (list != null && list.size() > 0) {
            int size = list.size();
            if (size > 10) {
                size = 10;
            }
            List<Feed> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                temp.add(list.get(i));
            }
            SettingUtils.saveObject(SettingUtils.FILE_LAST_HOT_FEED, temp);
        }
    }
}
