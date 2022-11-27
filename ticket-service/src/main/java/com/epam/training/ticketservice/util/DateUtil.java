package com.epam.training.ticketservice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Util class for LocalDateTime format.
 *
 * @author mezeimate
 */
public final class DateUtil {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static String format(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(localDateTime);
    }

    public static LocalDateTime format(String localDateTime){
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }
}
