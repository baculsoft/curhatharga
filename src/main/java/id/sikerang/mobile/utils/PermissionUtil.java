package id.sikerang.mobile.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import id.sikerang.mobile.SiKerang;

/**
 * Created by adrianch on 26/01/2016.
 */
public class PermissionUtil {

    public static boolean isLocationPermitted(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(SiKerang.getContext().checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isPhoneStatePermitted(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(SiKerang.getContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) ==
                    PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
