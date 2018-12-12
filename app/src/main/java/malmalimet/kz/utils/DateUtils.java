package malmalimet.kz.utils;

import android.support.annotation.IntRange;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Utility methods for working with dates.
 */
public class DateUtils {
    // Date pattern that we've agreed with the backend team on.
//    public static final String API_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String API_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";
    public static final SimpleDateFormat API_DATE_FORMAT = new SimpleDateFormat(API_DATE_PATTERN, Locale.getDefault());

    // The date format that we display in the UI.
    private static final SimpleDateFormat HUMAN_DATE_FORMAT = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
    // The date format when we want to display only the time part.
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
    // Date format used in bills.
    private static final SimpleDateFormat BILL_DATE_FORMAT = new SimpleDateFormat("d/MM HH:mm", Locale.getDefault());

    // Array of short day names (like Mon, Tue, etc.).
    private static final List<String> sShortDayNames;

    static {
        sShortDayNames = new ArrayList<>();

        String[] shortWeekdays = DateFormatSymbols.getInstance().getShortWeekdays();

        // Since the list index is 1-based, and the first item is Sunday, we from with the
        // second index, and append the first item afterwards.
//        for (int i = 2; i < shortWeekdays.length; i++) {
//            sShortDayNames.add(StringUtils.capitalize(shortWeekdays[i]));
//        }
//        sShortDayNames.add(StringUtils.capitalize(shortWeekdays[1]));
    }

    /**
     * Determine if the two dates are the same day.
     *
     * @param date1 - the first date.
     * @param date2 - the second date.
     * @return True if the two dates are the same day (even if different times).
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isBetweenDates(Date dateInQuestion, Date startDate, Date endDate) {
        Date earlierDate;
        Date laterDate;
        if (startDate.compareTo(endDate) <= 0) {
            earlierDate = startDate;
            laterDate = endDate;
        } else {
            earlierDate = endDate;
            laterDate = startDate;
        }

        return earlierDate.compareTo(dateInQuestion) * dateInQuestion.compareTo(laterDate) >= 0;
    }

    /**
     * Format the given date object into a date string.
     *
     * @param date The date to format.
     * @return Formatted string.
     */
    public static String dateStringFromDate(Date date) {
        return HUMAN_DATE_FORMAT.format(date);
    }

    public static String formatDateForBill(Date date) {
        return BILL_DATE_FORMAT.format(date);
    }

    public static String formatDateForApi(Date date) {
        return API_DATE_FORMAT.format(date);
    }

    public static Date parseStringFromApi(String date) {
        try {
            return API_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Return a short day name for the given day position, where day == 1 means Monday.
     */
    public static String getDayShortName(int day) {
        return sShortDayNames.get(day - 1);
    }

    /**
     * Given a time string, return a corresponding date object.
     *
     * @param timeString - the time string in HH:mm format.
     * @return A Date object.
     */
    public static Date timeToDate(String timeString) {
        try {
            Date date = TIME_FORMAT.parse(timeString);
            Calendar time = Calendar.getInstance();
            time.setTime(date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            return calendar.getTime();
        } catch (ParseException e) {
            return null;
        }
    }

    public static long getDayDifference(Date first, Date second) {
        Calendar firstCalendar = Calendar.getInstance();
        Calendar secondCalendar = Calendar.getInstance();

        firstCalendar.setTime(first);
        secondCalendar.setTime(second);

        setToBeginningOfDay(firstCalendar);
        setToBeginningOfDay(secondCalendar);

        secondCalendar.add(Calendar.DAY_OF_YEAR, 1);
        secondCalendar.add(Calendar.MILLISECOND, -1);

        long diff = secondCalendar.getTime().getTime() - firstCalendar.getTime().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long getMinuteDifference(Date first, Date second) {
        long diff = second.getTime() - first.getTime();
        return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long getSecondDifference(Date first, Date second) {
        long diff = second.getTime() - first.getTime();
        return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date getBeginningOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setToBeginningOfDay(calendar);
        return calendar.getTime();
    }

    private static void setToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    @IntRange(from = 1, to = 7)
    public static int getDayNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            return 7;
        } else {
            return dayOfWeek - 1;
        }
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
