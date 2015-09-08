package id.sikerang.mobile.controller;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import id.sikerang.mobile.utils.LocationTracker;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ProductController {
    private static final String TAG = ProductController.class.getSimpleName();

    private final Context mContext;

    private LocationTracker mLocationTracker;

    public ProductController(Context context) {
        mContext = context;
        mLocationTracker = new LocationTracker(context);
    }

    public String getLatitude() {
        Log.d(TAG, String.format("Latitude is %s", mLocationTracker.getLatitude()));
        return String.valueOf(mLocationTracker.getLatitude());
    }

    public String getLongitude() {
        Log.d(TAG, String.format("Longitude is %s", mLocationTracker.getLongitude()));
        return String.valueOf(mLocationTracker.getLongitude());
    }

    public List<Address> getAddress() throws IOException {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        return geocoder.getFromLocation(LocationTracker.getInstance(mContext).getLatitude(), LocationTracker.getInstance(mContext).getLongitude(), 1);
    }
}