package mouse.justjoke.business.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import mouse.justjoke.R;
import mouse.justjoke.business.bean.Feed;

/**
 * Created by mouse on 15/3/13.
 */
public class UltraAdapter extends UltimateViewAdapter {

    private List<Feed> mDataset;

    public UltraAdapter(List<Feed> list) {
        mDataset = list;
    }

    public void refreshData(List<Feed> list) {
        mDataset = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        private ImageView imageview;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.tv_hot);
            imageview = (ImageView) v.findViewById(R.id.iv_normal);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lv_hot_view, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getAdapterItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position < getItemCount() && (customHeaderView != null ? position <= mDataset.size() : position < mDataset.size()) && (customHeaderView != null ? position > 0 : true)) {

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText(mDataset.get(position).caption);
            ImageLoader.getInstance().displayImage(mDataset.get(position).images.normal, viewHolder.imageview);
            // ((ViewHolder) holder).itemView.setActivated(selectedItems.get(position, false));
        }


    }
}
