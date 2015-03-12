package mouse.justjoke.business.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import mouse.justjoke.R;
import mouse.justjoke.business.result.Feed;

/**
 * Created by mouse on 15/3/12.
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private List<Feed> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
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

    // Provide a suitable constructor (depends on the kind of dataset)
    public HotAdapter(List<Feed> list) {
        mDataset = list;
    }

    public void refreshData(List<Feed> list){
        mDataset = list;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lv_hot_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).caption);
        ImageLoader.getInstance().displayImage(mDataset.get(position).images.normal, holder.imageview);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}