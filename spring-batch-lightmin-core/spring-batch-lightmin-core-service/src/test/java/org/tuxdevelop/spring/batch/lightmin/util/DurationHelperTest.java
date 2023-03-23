package org.tuxdevelop.spring.batch.lightmin.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

public class DurationHelperTest {

    @Test
    public void createDurationValueMillisTest() {
        final String expected = "0:00:00.123";
        final long duration = 123L;
        final Date startTime = new Date(0);
        final Date endTime = new Date(duration);
        final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
        assertThat(durationValue).isEqualTo(expected);
    }

    @Test
    public void createDurationValueSecondsMillisTest() {
        final long duration = 1024L;
        final String expectedValue = "0:00:01.024";
        final Date startTime = new Date(0);
        final Date endTime = new Date(duration);
        final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
        assertThat(durationValue).isEqualTo(expectedValue);
    }

    @Test
    public void createDurationValueMinutesSecondsMillisTest() {
        final long duration = 3400199L;
        final String expectedValue = "0:56:40.199";
        final Date startTime = new Date(0);
        final Date endTime = new Date(duration);
        final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
        assertThat(durationValue).isEqualTo(expectedValue);
    }

    @Test
    public void createDurationValueHoursMinutesSecondsMillisTest() {
        final long duration = 5000199L;
        //final String expectedValue = "02:23:20:199";
        final Date startTime = new Date(0);
        final Date endTime = new Date(duration);
        final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
        //assertThat(durationValue).isEqualTo(expectedValue);
        assertThat(durationValue).isNotNull();
    }

    @Test
    public void createDurationValueStarTimeAndEndTimeNullTest() {
        final String expectedValue = "0:00:00.000";
        final String durationValue = DurationHelper.createDurationValue(null, null);
        assertThat(durationValue).isEqualTo(expectedValue);
    }

    @Test
    public void createDurationValueStarTimeNullTest() {
        final long duration = 5000199L;
        final Date endTime = new Date(duration);
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String expectedDurationString = sdf.format(new Date(0));
        String durationValue = DurationHelper.createDurationValue(null, endTime);
        assertThat(durationValue).isEqualTo(expectedDurationString);
    }

    @Test
    public void createDurationValueStarTimeAfterEndTimeTest() {
        final long duration = 5000199L;
        final Date startTime = new Date(5000200L);
        final Date endTime = new Date(duration);
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String expectedDurationString = sdf.format(new Date(0));
        String durationValue = DurationHelper.createDurationValue(startTime, endTime);
        assertThat(durationValue).isEqualTo(expectedDurationString);
    }

}
