package id.sikerang.mobile.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class LocationTracker implements LocationListener {
    private static final String TAG = LocationTracker.class.getSimpleName();

    private static volatile LocationTracker INSTANCE = null;

    private final Context mContext;

    private Location mLocation;
    private double mLatitude;
    private double mLongitude;

    protected LocationManager locationManager;

    public static synchronized LocationTracker getInstance(Context pContext) {
        if (INSTANCE == null) {
            INSTANCE = new LocationTracker(pContext);
        }

        return INSTANCE;
    }

    public LocationTracker(Context pContext) {
        mContext = pContext;
        getLocation();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    public Location getLocation() {
        boolean isGPSEnabled;
        boolean isNetworkEnabled;

        try {
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Log.i(TAG, String.format("isGPSEnabled=%s", isGPSEnabled));

            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.i(TAG, String.format("isNetworkEnabled=%s", isNetworkEnabled));

            if (isNetworkEnabled) {
                mLocation = null;
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Constants.MIN_TIME, Constants.MIN_DISTANCE, this);
                Log.i(TAG, "Network is Enabled!");

                if (locationManager != null) {
                    mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if (mLocation != null) {
                        mLatitude = mLocation.getLatitude();
                        mLongitude = mLocation.getLongitude();
                    }
                    locationManager.removeUpdates(this);
                }
            }

            if (isGPSEnabled) {
                mLocation = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Constants.MIN_TIME, Constants.MIN_DISTANCE, this);
                Log.i(TAG, "GPS is Enabled!");

                if (locationManager != null) {
                    mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (mLocation != null) {
                        mLatitude = mLocation.getLatitude();
                        mLongitude = mLocation.getLongitude();
                    }
                    locationManager.removeUpdates(this);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return mLocation;
    }

    public double getLatitude() {
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
        }

        return mLatitude;
    }

    public double getLongitude() {
        if (mLocation != null) {
            mLongitude = mLocation.getLongitude();
        }

        return mLongitude;
    }
}