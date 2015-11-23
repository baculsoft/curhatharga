package id.sikerang.mobile.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com.
 */
public final class KeyboardUtils {

    public static void hideKeyboard(View pView, Context pContext) {
        InputMethodManager inputMethodManager = (InputMethodManager) pContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(pView.getWindowToken(), 0);
    }
}