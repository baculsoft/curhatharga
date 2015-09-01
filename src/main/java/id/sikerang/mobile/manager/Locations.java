package id.sikerang.mobile.manager;

import android.content.Context;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class Locations implements LocationListener, GpsStatus.Listener {
    private static final String TAG = Locations.class.getSimpleName();

    private Location mLocation;
    private LocationManager mLocationManager;

    private String mProviderCoarse;
    private String mProviderFine;

    public Locations(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.addGpsStatusListener(this);

        Criteria criteria = new Criteria();
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        mProviderCoarse = mLocationManager.getBestProvider(criteria, true);

        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        mProviderFine = mLocationManager.getBestProvider(criteria, true);

        setCurrentLocation();
    }

    @Override
    public void onGpsStatusChanged(int status) {
        GpsStatus gpsStatus = mLocationManager.getGpsStatus(null);
        switch (status) {
            case GpsStatus.GPS_EVENT_STARTED: {
                gpsStatus.getMaxSatellites();
                break;
            }
            case GpsStatus.GPS_EVENT_FIRST_FIX: {
                gpsStatus.getTimeToFirstFix();
                break;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        String latitude;
        String longitude;
        if (location.getProvider().equals(mProviderCoarse)) {
            latitude = Double.toString(location.getLatitude());
            longitude = Double.toString(location.getLongitude());
            Log.d(TAG, String.format("Location Coarse: %s, %s", latitude, longitude));
        }

        if (location.getProvider().equals(mProviderFine)) {
            latitude = Double.toString(location.getLatitude());
            longitude = Double.toString(location.getLongitude());
            Log.d(TAG, String.format("Location Fine: %s, %s", latitude, longitude));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {
        if (provider.equalsIgnoreCase(mProviderCoarse)) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderCoarse);
            mLocationManager.requestLocationUpdates(mProviderCoarse, mLocation.getTime(), mLocation.getAccuracy(), this);
        }

        if (provider.equalsIgnoreCase(mProviderFine)) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderFine);
            mLocationManager.requestLocationUpdates(mProviderFine, mLocation.getTime(), mLocation.getAccuracy(), this);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (provider.equalsIgnoreCase(mProviderCoarse)) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderCoarse);
            mLocationManager.requestLocationUpdates(mProviderCoarse, mLocation.getTime(), mLocation.getAccuracy(), this);
        }

        if (provider.equalsIgnoreCase(mProviderFine)) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderFine);
            mLocationManager.requestLocationUpdates(mProviderFine, mLocation.getTime(), mLocation.getAccuracy(), this);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equalsIgnoreCase(mProviderCoarse)) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderCoarse);
            mLocationManager.requestLocationUpdates(mProviderCoarse, mLocation.getTime(), mLocation.getAccuracy(), this);
        }

        if (provider.equalsIgnoreCase(mProviderFine)) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderFine);
            mLocationManager.requestLocationUpdates(mProviderFine, mLocation.getTime(), mLocation.getAccuracy(), this);
        }
    }

    private void setCurrentLocation() {
        if (mProviderCoarse != null) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderCoarse);
            mLocationManager.requestLocationUpdates(mProviderCoarse, mLocation.getTime(), mLocation.getAccuracy(), this);
        }

        if (mProviderFine != null) {
            mLocation = mLocationManager.getLastKnownLocation(mProviderFine);
            mLocationManager.requestLocationUpdates(mProviderFine, mLocation.getTime(), mLocation.getAccuracy(), this);
        }
    }
}