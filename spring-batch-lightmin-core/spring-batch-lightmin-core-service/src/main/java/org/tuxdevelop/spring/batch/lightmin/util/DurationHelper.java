package org.tuxdevelop.spring.batch.lightmin.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Marcel Becker
 * @since 0.1
 * <p>
 * Utility class to generate human readable durations
 * </p>
 */
@Slf4j
public final class DurationHelper {

    private DurationHelper() {
    }

    /**
     * Creates a human readable String of duration between a start date and an end date
     *
     * @param startTime beginning of the duration interval
     * @param endTime   end of the duration interval
     * @return a {@link String} representation of the duration
     */
    public static String createDurationValue(Date startTime, Date endTime) {
        final Date current = new Date();
        if (startTime == null) {
            startTime = current;
            log.info("startTime was null, set to current date");
        }
        if (endTime == null) {
            endTime = current;
            log.info("endTime was null, set to current date");
        }
        final Duration duration = Duration.between(startTime.toInstant(), endTime.toInstant());

        return format(duration.isNegative() ? Duration.ZERO : duration);
    }

    private static String format(final Duration duration) {
        return String.format("%d:%02d:%02d.%03d",
                             duration.toHours(),
                             duration.toMinutes() % 60,
                             duration.getSeconds() % 60,
                             TimeUnit.NANOSECONDS.toMillis(duration.getNano()));
    }
}
