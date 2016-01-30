package id.sikerang.mobile.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * @author Adrian Chriswanto
 */
public final class Permissions {
    /**
     * Returns true if the Activity has access to all given permissions.
     * Always returns true on platforms below API 23.
     *
     * @see Activity#checkSelfPermission(String)
     */
    @TargetApi(23)
    public static boolean hasSelfPermissions(Activity activity, String[] permissions) {
        if (!isMarshmallow()) {
            return true;
        }

        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
     */
    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}