package id.sikerang.mobile.komoditas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasHolder implements View.OnClickListener {
    @Bind(R.id.iv_komoditas)
    ImageView mImageViewKomoditas;

    @Bind(R.id.tv_komoditas)
    TextView mTextViewKomoditas;

    @Bind(R.id.tv_statement)
    TextView mTextViewStatment;

    @Bind(R.id.ratingbar_satisfaction)
    RatingBar mRatingBarSatisfaction;

    @Bind(R.id.tv_satisfaction)
    TextView mTextViewSatisfaction;

    @Bind(R.id.tv_location)
    TextView mTextViewLocation;

    private final SharedPreferencesUtils mSharedPreferencesUtils;
    private final View mView;

    private Boolean mIsLikes;

    public KomoditasHolder(final int pPosition, final LayoutInflater pLayoutInflater, final ViewGroup pContainer) {
        mSharedPreferencesUtils = SharedPreferencesUtils.getInstance(SiKerang.getContext());
        mView = pLayoutInflater.inflate(R.layout.row_komoditas, pContainer, false);
        ButterKnife.bind(this, mView);
        initComponents(pPosition);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_murah: {
                mIsLikes = true;
                break;
            }
            case R.id.fab_mahal: {
                mIsLikes = false;
                break;
            }
            default: {
                return;
            }
        }

        behaveLike();
    }

    private void initComponents(int position) {
        switch (position) {
            case 0: {
                getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_rice));
                getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_rice));
                getRatingBarSatisfaction().setNumStars(1);
                getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_1));
                mIsLikes = mSharedPreferencesUtils.getRiceLikes();
                break;
            }
            case 1: {
                getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_corn));
                getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_corn));
                getRatingBarSatisfaction().setNumStars(2);
                getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_2));
                mIsLikes = mSharedPreferencesUtils.getCornLikes();
                break;
            }
            case 2: {
                getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_soya));
                getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_soya));
                getRatingBarSatisfaction().setNumStars(3);
                getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_3));
                mIsLikes = mSharedPreferencesUtils.getSoyLikes();
                break;
            }
            case 3: {
                getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_chicken));
                getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_chicken));
                getRatingBarSatisfaction().setNumStars(3);
                getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_3));
                mIsLikes = mSharedPreferencesUtils.getChickenLikes();
                break;
            }
            case 4: {
                getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_meal));
                getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_meal));
                getRatingBarSatisfaction().setNumStars(2);
                getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_2));
                mIsLikes = mSharedPreferencesUtils.getBeefLikes();
                break;
            }
            case 5: {
                getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_sugar));
                getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_sugar));
                getRatingBarSatisfaction().setNumStars(1);
                getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_1));
                mIsLikes = mSharedPreferencesUtils.getSugarLikes();
                break;
            }
        }

        behaveLike();
    }

    /**
     * Displaying interface behaviour related to current like status,
     * such as displaying/hiding text, color, etc.
     */
    private void behaveLike() {
        if (isLikes() == null) {
            return;
        }

        final int color, text;
        if (isLikes()) {
            color = R.color.teal_500;
            text = R.string.text_murah;
        } else {
            color = R.color.red_500;
            text = R.string.text_mahal;
        }

        getTextViewStatment().setTextColor(SiKerang.getContext().getResources().getColor(color));
        getTextViewStatment().setText(SiKerang.getContext().getResources().getString(text));
    }

    public ImageView getImageViewKomoditas() {
        return mImageViewKomoditas;
    }

    public TextView getTextViewKomoditas() {
        return mTextViewKomoditas;
    }

    public TextView getTextViewStatment() {
        return mTextViewStatment;
    }

    public RatingBar getRatingBarSatisfaction() {
        return mRatingBarSatisfaction;
    }

    public TextView getTextViewSatisfaction() {
        return mTextViewSatisfaction;
    }

    public TextView getTextViewLocation() {
        return mTextViewLocation;
    }

    public View getView() {
        return mView;
    }

    public Boolean isLikes() {
        return mIsLikes;
    }
}