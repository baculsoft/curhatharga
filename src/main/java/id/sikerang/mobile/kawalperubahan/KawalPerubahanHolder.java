package id.sikerang.mobile.kawalperubahan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.tv_date)
    TextView mTextViewDate;

    @Bind(R.id.tv_title)
    TextView mTextViewTitle;

    @Bind(R.id.tv_content)
    TextView mTextViewContent;

    private IKawalPerubahanListener mKawalPerubahanListener;

    public KawalPerubahanHolder(View itemView, IKawalPerubahanListener pKawalPerubahanListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mKawalPerubahanListener = pKawalPerubahanListener;
    }

    @Override
    public void onClick(View view) {
        mKawalPerubahanListener.onClick(view, getAdapterPosition());
    }

    public TextView getTextViewDate() {
        return mTextViewDate;
    }

    public TextView getTextViewTitle() {
        return mTextViewTitle;
    }

    public TextView getTextViewContent() {
        return mTextViewContent;
    }

    public void setKawalPerubahanListener(IKawalPerubahanListener pKawalPerubahanListener) {
        mKawalPerubahanListener = pKawalPerubahanListener;
    }
}