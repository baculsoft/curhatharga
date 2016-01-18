package id.sikerang.mobile.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.sikerang.mobile.R;
import id.sikerang.mobile.SiKerang;
import id.sikerang.mobile.controller.KomoditasController;
import id.sikerang.mobile.utils.SharedPreferencesUtils;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class KomoditasAdapter extends PagerAdapter implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private final LayoutInflater mLayoutInflater;
    private final AtomicInteger mPosition;
    private final Map<Integer, KomoditasViewHolder> mHoldersMap;
    private final KomoditasController mKomoditasController;
    private final SharedPreferencesUtils mSharedPreferenceUtils;

    public KomoditasAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPosition = new AtomicInteger();
        mHoldersMap = new HashMap<>();
        mKomoditasController = new KomoditasController(SiKerang.getContext());
        mSharedPreferenceUtils = SharedPreferencesUtils.getInstance(SiKerang.getContext());
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (!mHoldersMap.containsKey(position)) {
            mHoldersMap.put(position, new KomoditasViewHolder(position, mLayoutInflater, container));
        }

        String location = SharedPreferencesUtils.getInstance(SiKerang.getContext()).getLocationAddress();
        KomoditasViewHolder komoditasViewHolder = mHoldersMap.get(position);
        komoditasViewHolder.getTextViewLocation().setText(location);
        container.addView(komoditasViewHolder.getView());

        return komoditasViewHolder.getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public void onClick(View view) {
        KomoditasViewHolder komoditasViewHolder = mHoldersMap.get(mPosition.get());
        komoditasViewHolder.onClick(view);
        saveLike(komoditasViewHolder.isLikes());

        mKomoditasController.collect(mKomoditasController.getLatitude(),
                                     mKomoditasController.getLongitude(),
                                     mKomoditasController.getScreenName(),
                                     komoditasViewHolder.getTextViewKomoditas().getText().toString(),
                                     mKomoditasController.getComments(),
                                     komoditasViewHolder.isLikes());
    }

    @Override
    public void onPageSelected(int position) {
        mPosition.set(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * Get like status related to current fragment position
     *
     * @return {@code True}, {@code False}, or {@code Null}
     */
    @Nullable
    public Boolean isLike() {
        KomoditasViewHolder komoditasViewHolder = mHoldersMap.get(mPosition.get());
        return komoditasViewHolder.isLikes();
    }

    public void setShowHide(boolean isShown) {
        KomoditasViewHolder komoditasViewHolder = mHoldersMap.get(mPosition.get());
        if (isShown) {
            komoditasViewHolder.getTextViewKomoditas().setVisibility(View.GONE);
            komoditasViewHolder.getRatingBarSatisfaction().setVisibility(View.GONE);
            komoditasViewHolder.getTextViewSatisfaction().setVisibility(View.GONE);
            komoditasViewHolder.getTextViewLocation().setVisibility(View.GONE);
        } else {
            komoditasViewHolder.getTextViewKomoditas().setVisibility(View.VISIBLE);
            komoditasViewHolder.getRatingBarSatisfaction().setVisibility(View.VISIBLE);
            komoditasViewHolder.getTextViewSatisfaction().setVisibility(View.VISIBLE);
            komoditasViewHolder.getTextViewLocation().setVisibility(View.VISIBLE);
        }
    }

    /**
     * Save like status into {@code SharedPreferences}.
     *
     * @param isLike - Like status
     */
    private void saveLike(final boolean isLike) {
        switch (mPosition.get()) {
            case 0: {
                mSharedPreferenceUtils.setRiceLikes(isLike);
                break;
            }
            case 1: {
                mSharedPreferenceUtils.setCornLikes(isLike);
                break;
            }
            case 2: {
                mSharedPreferenceUtils.setSoyLikes(isLike);
                break;
            }
            case 3: {
                mSharedPreferenceUtils.setChickenLikes(isLike);
                break;
            }
            case 4: {
                mSharedPreferenceUtils.setBeefLikes(isLike);
                break;
            }
            case 5: {
                mSharedPreferenceUtils.setSugarLikes(isLike);
                break;
            }
        }
    }

    static final class KomoditasViewHolder implements View.OnClickListener {
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

        private final View mView;
        private final SharedPreferencesUtils mSharedPreferencesUtils;

        private Boolean mIsLikes;

        public KomoditasViewHolder(final int position, final LayoutInflater layoutInflater, final ViewGroup container) {
            mSharedPreferencesUtils = SharedPreferencesUtils.getInstance(SiKerang.getContext());

            mView = layoutInflater.inflate(R.layout.row_komoditas, container, false);
            ButterKnife.bind(this, mView);
            initComponents(position);
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

        public View getView() {
            return mView;
        }

        public Boolean isLikes() {
            return mIsLikes;
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
    }
}