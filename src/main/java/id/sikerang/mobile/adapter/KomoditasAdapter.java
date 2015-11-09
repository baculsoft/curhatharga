package id.sikerang.mobile.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

    private static final String TAG = KomoditasAdapter.class.getSimpleName();

    private final LayoutInflater mLayoutInflater;
    private final AtomicInteger mPosition;
    private final Map<Integer, KomoditasViewHolder> mHoldersMap;
    private final KomoditasController mKomoditasController;

    public KomoditasAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPosition = new AtomicInteger();
        mHoldersMap = new HashMap<>();
        mKomoditasController = new KomoditasController(SiKerang.getContext());
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

        mKomoditasController.collectCommonInfo(mKomoditasController.getLatitude(),
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
        Log.d(TAG, "onPageScrolled KomoditasAdapter.");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged KomoditasAdapter.");
    }

    public Boolean isLike() {
        KomoditasViewHolder komoditasViewHolder = mHoldersMap.get(mPosition.get() );
        return komoditasViewHolder.isLikes();
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

        private Boolean isLikes;

        public KomoditasViewHolder(final int position, final LayoutInflater layoutInflater, final ViewGroup container) {
            mView = layoutInflater.inflate(R.layout.row_komoditas, container, false);
            ButterKnife.bind(this, mView);
            initComponents(position);
        }

        @Override
        public void onClick(View view) {
            final int color;
            final int text;

            switch (view.getId()) {
                case R.id.fab_murah: {
                    color = R.color.teal_500;
                    text = R.string.text_murah;
                    isLikes = true;
                    break;
                }
                case R.id.fab_mahal: {
                    color = R.color.red_500;
                    text = R.string.text_mahal;
                    isLikes = false;
                    break;
                }
                default: {
                    return;
                }
            }

            getTextViewStatment().setTextColor(SiKerang.getContext().getResources().getColor(color));
            getTextViewStatment().setText(SiKerang.getContext().getResources().getString(text));
        }

        // FIXME Hardcoded value
        private void initComponents(int position) {
            switch (position) {
                case 0: {
                    getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_rice));
                    getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_rice));
                    getRatingBarSatisfaction().setNumStars(3);
                    getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_3));
                    break;
                }
                case 1: {
                    getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_corn));
                    getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_corn));
                    getRatingBarSatisfaction().setNumStars(2);
                    getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_2));
                    break;
                }
                case 2: {
                    getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_soya));
                    getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_soya));
                    getRatingBarSatisfaction().setNumStars(1);
                    getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_1));
                    break;
                }
                case 3: {
                    getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_chicken));
                    getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_chicken));
                    getRatingBarSatisfaction().setNumStars(1);
                    getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_1));
                    break;
                }
                case 4: {
                    getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_meal));
                    getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_meal));
                    getRatingBarSatisfaction().setNumStars(2);
                    getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_2));
                    break;
                }
                case 5: {
                    getImageViewKomoditas().setBackgroundDrawable(SiKerang.getContext().getResources().getDrawable(R.mipmap.ic_sugar));
                    getTextViewKomoditas().setText(SiKerang.getContext().getResources().getString(R.string.product_sugar));
                    getRatingBarSatisfaction().setNumStars(3);
                    getTextViewSatisfaction().setText(SiKerang.getContext().getResources().getString(R.string.satisfaction_level_3));
                    break;
                }
            }
        }

        public View getView() {
            return mView;
        }

        public Boolean isLikes() {
            return isLikes;
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