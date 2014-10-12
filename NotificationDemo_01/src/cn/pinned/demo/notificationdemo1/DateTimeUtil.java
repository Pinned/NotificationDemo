package cn.pinned.demo.notificationdemo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.text.format.DateFormat;
import android.util.Log;

/**
 * Created by luozc on Jul 23, 2014
 */

public final class DateTimeUtil {
	private static final String TAG = DateTimeUtil.class.getSimpleName();
	public static final String TEMPLATE = "yyyy-MM-dd kk:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String HH_MM_A = "hh:mm a";
	public static final String HH_MM = "hh:mm";

	public static String formatDateDefault(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TEMPLATE, Locale.CHINA);
		return sdf.format(date);
	}

	public static String formatDateByTemplate(String template, Date date) {
		return formatDateByTemplate(template, date, Locale.CHINA);

	}
	public static String formatDateByTemplate(String template, Date date,Locale locale) {
	    SimpleDateFormat sdf = new SimpleDateFormat(template, locale);
	    return sdf.format(date);
	    
	}



	public static String formatMsgDateTime(String dateStr){
		return formatMsgDateTime(stringToDate(TEMPLATE, dateStr));
	}
	
	public static String formatMsgDateTime(Date date) {
		Calendar des = Calendar.getInstance();
		des.setTime(date);
		Calendar now = Calendar.getInstance();
		// 如果是今天发的，则显示为HH:MM
		SimpleDateFormat todaySdf = new SimpleDateFormat(HH_MM, Locale.CHINA);
		if (des.get(Calendar.YEAR) == now.get(Calendar.YEAR) && des.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& des.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {

			return todaySdf.format(date);
		}
		// 如果是昨天发的，则显示为“昨天”
		now.add(Calendar.DAY_OF_MONTH, -1);
		if (des.get(Calendar.YEAR) == now.get(Calendar.YEAR) && des.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& des.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
			return "昨天";
		}
		// 如果是在本周内发的
		now = Calendar.getInstance();
		int n = now.get(Calendar.DAY_OF_WEEK);
		if (now.getTime().getTime() - date.getTime() > n * 24 * 3600 * 1000) {
			// 这个是超过一周,显示为YYYY-MM-DD
			SimpleDateFormat weekSdf = new SimpleDateFormat(YYYY_MM_DD, Locale.CHINA);
			return weekSdf.format(date);

		} else {
			SimpleDateFormat weekSdf = new SimpleDateFormat("EEEE", Locale.CHINA);
			return weekSdf.format(date);
		}
	}

	public static Date stringToDate(String template, String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return Calendar.getInstance().getTime();
		}
	}
	
	public static Date stringToDateByToday(String template, String dateStr) {
		Date date = stringToDate(template, dateStr);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	private DateTimeUtil() {
	}

    public static String getRefreshTime(String timeStr) {
        return timeStr;
    }

    public static Date parseDate(String date, String partern) {
        SimpleDateFormat sdf = new SimpleDateFormat(partern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance().getTime();
        }
    };

}
