package id.sikerang.mobile.adapter;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private TypedArray mMenuIcons;
    private String[] mMenuTitles;

    public MenuAdapter(TypedArray icons, String[] titles) {
        mMenuIcons = icons;
        mMenuTitles = titles;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        holder.mImageViewMenu.setImageResource(mMenuIcons.getResourceId(position, 0));
        holder.mTextViewMenu.setText(mMenuTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mMenuTitles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewMenu;
        TextView mTextViewMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewMenu = (ImageView) itemView.findViewById(R.id.iv_menu);
            mTextViewMenu = (TextView) itemView.findViewById(R.id.tv_menu);
        }
    }
}