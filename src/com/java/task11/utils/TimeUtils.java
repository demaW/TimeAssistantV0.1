package com.java.task11.utils;

import java.sql.Time;
import java.util.Calendar;

public class TimeUtils {

	public static Time addTimes(Time time1, Time time2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time1);

		int secondsSumm = calendar.get(Calendar.SECOND);
		int minutesSumm = calendar.get(Calendar.MINUTE);
		int hoursSumm = calendar.get(Calendar.HOUR);

		calendar.setTime(time2);

		secondsSumm += calendar.get(Calendar.SECOND);
		minutesSumm += calendar.get(Calendar.MINUTE);
		hoursSumm += calendar.get(Calendar.HOUR);

		calendar.set(0, 0, 0, hoursSumm, minutesSumm, secondsSumm);
		Time time = new Time(calendar.getTimeInMillis());
		
		return time;
	}

}
