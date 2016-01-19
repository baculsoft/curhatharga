package id.sikerang.mobile.kawalperubahan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.utils.Configs;
import id.sikerang.mobile.utils.Constants;
import id.sikerang.mobile.utils.DateUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KawalPerubahanAdapter extends RecyclerView.Adapter<KawalPerubahanHolder> {
    private KawalPerubahan mKawalPerubahan;

    public KawalPerubahanAdapter(final KawalPerubahan pKawalPerubahan) {
        mKawalPerubahan = pKawalPerubahan;
    }

    @Override
    public KawalPerubahanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kawal_perubahan, parent, false);
        return new KawalPerubahanHolder(view, null);
    }

    @Override
    public void onBindViewHolder(KawalPerubahanHolder holder, int position) {
        String convertedDate = DateUtils.convertDate(mKawalPerubahan.getKawalPerubahanContents().get(position).getDate());
        String date = SiKerang.getContext().getResources().getString(R.string.text_tanggal).concat(convertedDate);
        String title = mKawalPerubahan.getKawalPerubahanContents().get(position).getTitle().substring(0, Constants.MAX_TITLE).concat("...");
        String content = mKawalPerubahan.getKawalPerubahanContents().get(position).getContent().substring(0, Constants.MAX_CONTENT).concat("...");

        holder.getTextViewDate().setText(date);
        holder.getTextViewTitle().setText(title);
        holder.getTextViewContent().setText(content);
        holder.setKawalPerubahanListener(new IKawalPerubahanListener() {
            @Override
            public void onClick(View view, long position) {
                Intent intent = new Intent(view.getContext(), KawalPerubahanActivity.class);
                Bundle extras = new Bundle();
                Long item = position;
                String convertedDate = DateUtils.convertDate(mKawalPerubahan.getKawalPerubahanContents().get(item.intValue()).getDate());
                String dates = SiKerang.getContext().getResources().getString(R.string.text_tanggal).concat(convertedDate);
                String titles = mKawalPerubahan.getKawalPerubahanContents().get(item.intValue()).getTitle();
                String contents = mKawalPerubahan.getKawalPerubahanContents().get(item.intValue()).getContent();

                extras.putString(Configs.KAWAL_DATES, dates);
                extras.putString(Configs.KAWAL_TITLES, titles);
                extras.putString(Configs.KAWAL_CONTENTS, contents);
                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKawalPerubahan.getSize();
    }
}