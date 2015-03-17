package mouse.justjoke.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import mouse.justjoke.R;
import mouse.justjoke.business.bean.Feed;
import mouse.justjoke.ui.activity.common.SuperActivity;

public class DetailActivity extends SuperActivity {

    private List<Feed> feedList;
    private int position;

    private ImageView gifImageView;

    private static final String EXTRA_LIST = "extra_list";

    private static final String EXTRA_POS = "extra_pos";

    public static void launch(Context context, ArrayList<Feed> list, int pos) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_LIST, list);
        intent.putExtra(EXTRA_POS, pos);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        initData();
    }

    private void initData() {
        List<Feed> temp = (ArrayList<Feed>) getIntent().getSerializableExtra(EXTRA_LIST);
        position = getIntent().getIntExtra(EXTRA_POS, 0);
        if (null == temp || temp.size() == 0) {
            finish();
            return;
        }
        feedList = new ArrayList<>();
        feedList.addAll(temp);
        ImageLoader.getInstance().displayImage(feedList.get(position).images.large, gifImageView);
    }

    private void initViews() {
        gifImageView = (ImageView) findViewById(R.id.iv_detail);

    }

}
