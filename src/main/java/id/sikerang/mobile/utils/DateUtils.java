package id.sikerang.mobile.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class DateUtils {
    private static final String TAG = DateUtils.class.getSimpleName();

    public static final SimpleDateFormat SERVER_PATTERN = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    public static final SimpleDateFormat GRAPH_PATTERN = new SimpleDateFormat("dd/MM/yyyy", new Locale("id"));
    public static final SimpleDateFormat NEWS_PATTERN = new SimpleDateFormat("d MMMM yyyy", new Locale("id"));

    public static String convertDate(String pDateValue, SimpleDateFormat pSimpleDateFormat) {
        String convertedDate = "";

        try {
            Date date = SERVER_PATTERN.parse(pDateValue);
            convertedDate = pSimpleDateFormat.format(date);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return convertedDate;
    }
}