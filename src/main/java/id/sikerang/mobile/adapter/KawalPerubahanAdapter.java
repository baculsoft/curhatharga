package id.sikerang.mobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanAdapter extends RecyclerView.Adapter<KawalPerubahanAdapter.KawalPerubahanHolder> {
    private static final String TAG = KawalPerubahanAdapter.class.getSimpleName();

    @Override
    public KawalPerubahanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kawal_perubahan, parent, false);
        return new KawalPerubahanHolder(view, null);
    }

    @Override
    public void onBindViewHolder(KawalPerubahanHolder holder, int position) {
        holder.getTextViewDate().setText("15/08/2015");
        holder.getTextViewTitle().setText("Tiga Jam Operasi Pasar, Ayam di Bandung Ludes Diserbu");
        holder.getTextViewContent().setText("Kepala Dinas Peternakan dan Ketahanan Pangan Kota Bandung, Elly Wasilah mengatakan, dalam kurun waktu tiga jam sebanyak 500..");

        holder.mKawalPerubahanListener = new KawalPerubahanListener() {
            @Override
            public void onClick(View view, long position) {
                // TODO Not yet implemented
                Log.d(TAG, String.format("Not yet implemented %d", position));
            }
        };
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class KawalPerubahanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tv_date)
        TextView mTextViewDate;

        @Bind(R.id.tv_title)
        TextView mTextViewTitle;

        @Bind(R.id.tv_content)
        TextView mTextViewContent;

        private KawalPerubahanListener mKawalPerubahanListener;

        public KawalPerubahanHolder(View itemView, final KawalPerubahanListener kawalPerubahanListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mKawalPerubahanListener = kawalPerubahanListener;
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
    }

    interface KawalPerubahanListener {
        void onClick(View view, long position);
    }
}