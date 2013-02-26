package cn.sh.ae.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import cn.sh.ae.vo.MyDate;

/**
 * 
 */
public class MyTime implements Serializable {

	static Logger logger = Logger.getLogger(MyTime.class.getName());

	private static final long serialVersionUID = 7230956639307491350L;

	public static MyDate getDate(String date, String formatString) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(formatString);
		GregorianCalendar calendarBegin = new GregorianCalendar();
		try {
			Date yearMotnth = timeFormat.parse(date);
			calendarBegin.setTime(new Date(yearMotnth.getTime()));
			MyDate myDate = new MyDate();
			myDate.setYear(calendarBegin.get(Calendar.YEAR));
			myDate.setMonth(calendarBegin.get(Calendar.MONTH) + 1);
			myDate.setDay(calendarBegin.get(Calendar.DAY_OF_MONTH));
			myDate.setWeek(calendarBegin.get(Calendar.WEEK_OF_YEAR));
			return myDate;
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	public static String getFormatDate(String date, String beforeFormat,
			String afterFormat) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(beforeFormat);
		GregorianCalendar calendarBegin = new GregorianCalendar();
		try {
			Date yearMotnth = timeFormat.parse(date);
			calendarBegin.setTime(new Date(yearMotnth.getTime()));

			SimpleDateFormat formatter = new SimpleDateFormat(afterFormat);

			return formatter.format(calendarBegin.getTime());
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	public static String getFormatTime(long time, String format) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(format);
		Date dt = new Date(time);
		try {
			String sDateTime = timeFormat.format(dt);
			return sDateTime;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	public static MyDate getDate() {
		return getDateTime(0);
	}

	public static MyDate getYestoday() {
		return getDateTime(-1);
	}

	public static MyDate getTomorrow() {
		return getDateTime(1);
	}

	private static MyDate getDateTime(int i) {
		MyDate myDate = new MyDate();
		Calendar ca = Calendar.getInstance();
		if (i == -1)
			ca.add(Calendar.DAY_OF_MONTH, -1);
		else if (i == 1)
			ca.add(Calendar.DAY_OF_MONTH, +1);
		myDate.setYear(ca.get(Calendar.YEAR));
		myDate.setMonth(ca.get(Calendar.MONTH) + 1);
		myDate.setWeek(ca.get(Calendar.WEEK_OF_YEAR));
		myDate.setDay(ca.get(Calendar.DAY_OF_MONTH));
		myDate.setHour(ca.get(Calendar.HOUR_OF_DAY));
		return myDate;
	}

	public static String getPreDate() {
		return getPreDate("yyyy-MM-dd");
	}

	public static String getDate(String formatString) {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat(formatString);
		return matter1.format(dt);

	}

	public static String getPreDate(String formatString) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		return formatter.format(ca.getTime());
	}
	
	public static String getOneDate(String formatString,int s) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		ca.add(Calendar.DAY_OF_MONTH, s);
		return formatter.format(ca.getTime());
	}

	public static Date getHourOfDay(int hour) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		/** * 定制每日hour：00：00执行方法 ** */
		calendar.set(year, month, day, hour, 00, 00);
		return calendar.getTime();
	}

	public static String getTime(String formatStr) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat d = new SimpleDateFormat(formatStr);
		return d.format(ca.getTime());
	}

	// 时间间隔
	public static int subTime(String inValStart, String invalEnd) {
		return subTime(inValStart, invalEnd, "yyyy-MM-dd");
	}

	// 时间间隔
	public static String subTimeByTime(String inValStart, long time,
			String fromatString) {
		SimpleDateFormat d = new SimpleDateFormat(fromatString);// 格式化时间
		try {
			long result = (d.parse(inValStart).getTime() - time);// 当前时间减去测试时间
			// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
			return d.format(new Date(result));
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	// public static void main(String[] args) {
	// System.out.println(subTime("20101217160000", "20101217170000",
	// "yyyyMMddHHmmss"));
	// }

	// 时间间隔
	public static int subTime(String inValStart, String invalEnd,
			String fromatString) {
		int day = 0;
		SimpleDateFormat d = new SimpleDateFormat(fromatString);// 格式化时间
		try {
			long result = (d.parse(invalEnd).getTime() - d.parse(inValStart)
					.getTime()) / 3600000;// 当前时间减去测试时间
			// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
			day =  (int)result;
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			day = 0;
		}
		return day;
	}

	// 时间间隔
	public static int subTimeM(String inValStart, String invalEnd,
			String fromatString) {
		int day = 0;
		SimpleDateFormat d = new SimpleDateFormat(fromatString);// 格式化时间
		try {
			long result = (d.parse(invalEnd).getTime() - d.parse(inValStart)
					.getTime()) / 60000;// 当前时间减去测试时间
			// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
			day = (int) result;
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			day = 0;
		}
		return day;
	}

	public static int subTimeSecond(String inValStart, String invalEnd,
			String fromatString) {
		int day = 0;
		SimpleDateFormat d = new SimpleDateFormat(fromatString);// 格式化时间
		try {
			long result = (d.parse(invalEnd).getTime() - d.parse(inValStart)
					.getTime()) / 1000;// 当前时间减去测试时间
			// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
			day = (int) result;
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			day = 0;
		}
		return day;
	}

	// 获取系统当前时间相对时间
	public static String getDesTime(String time, int y, int m, int d) {
		Calendar cal = Calendar.getInstance();
		// 当前年
		int year = cal.get(Calendar.YEAR) + y;
		// 当前月
		int month = (cal.get(Calendar.MONTH)) + 1 + m;
		// 当前月的第几天：即当前日
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH) + d;
		if (time != null) {
			String[] times = time.split("-");
			year = Integer.parseInt(times[0]) + y;
			month = Integer.parseInt(times[1]) - 1 + m;
			day_of_month = Integer.parseInt(times[2]) + d;
		}

		cal.set(year, month, day_of_month);
		SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		return simpleDateTimeFormat.format(cal.getTime());
	}

	public static int monthCount(String start, String end) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar calendarBegin = null, calendarEnd = null;
		Date dateStart, dateEnd;
		int months = 0;
		try {
			dateStart = timeFormat.parse(start);
			dateEnd = timeFormat.parse(end);
			dateStart = new Date(dateStart.getTime());
			dateEnd = new Date(dateEnd.getTime());
			calendarBegin = new GregorianCalendar();
			calendarEnd = new GregorianCalendar();
			calendarBegin.setTime(dateStart);
			calendarEnd.setTime(dateEnd);

			months = calendarEnd.get(Calendar.MONTH)
					- calendarBegin.get(Calendar.MONTH)
					+ 12
					* (calendarEnd.get(Calendar.YEAR) - calendarBegin
							.get(Calendar.YEAR))
					+ (calendarEnd.get(Calendar.DATE)
							- calendarBegin.get(Calendar.DATE) + 1)
					/ calendarEnd.get(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			return 0;
		}

		return months;
	}

	public static String getNextDay(String date, String formatString) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(formatString);
		GregorianCalendar calendarBegin = new GregorianCalendar();
		try {
			Date yearMotnth = timeFormat.parse(date);
			calendarBegin.setTime(new Date(yearMotnth.getTime()));
			int day = calendarBegin.get(Calendar.DAY_OF_MONTH);
			calendarBegin.set(Calendar.DAY_OF_MONTH, day + 1);
			return timeFormat.format(calendarBegin.getTime());
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
}