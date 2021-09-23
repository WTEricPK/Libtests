import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Set;

import static java.time.temporal.ChronoField.*;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import org.junit.Test;

public class DatesAndTimes
{

    // event one
    private static final String startDateEvent1 = "2020-05-03";
    private static final String endDateEvent1 = "2020-05-03";

    // event two
    private static final String startDateEvent2 = "2020-06-04T09:00:00Z";
    private static final String endDateEvent2 = "2020-06-06T12:00:00Z";

    @Test
    public void dateToDateTime()
    {


        LocalDate offsetDate = LocalDate.parse(endDateEvent1, DateTimeFormatter.ISO_DATE);
        LocalDateTime.of(LocalDate.parse(endDateEvent1, DateTimeFormatter.ISO_DATE), LocalTime.MIN);
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(startDateEvent2, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        OffsetDateTime odf = OffsetDateTime.of(LocalDate.parse(endDateEvent1, DateTimeFormatter.ISO_DATE), LocalTime.MIN, OffsetDateTime.now().getOffset());

        System.out.println(odf);
    }

    @Test
    public void duration()
    {
        final Duration duration = Duration.parse("T1.1000S");

        System.out.println(duration.toMillis());
    }


    @Test
    public void orAndExOr()
    {
        final Integer a1 = 1;
        final Integer b1 = 1;

        System.out.println("Or    " + (a1 == null || b1 == null));
        System.out.println("Ex or " + (a1 == null | b1 == null));

    }



    @Test
    public void compareDateTimes()
    {

        OffsetDateTime dateTime1 = OffsetDateTime.parse("2021-05-07T17:00:51Z", ISO_OFFSET_DATE_TIME_PARSER);
        OffsetDateTime dateTime2 = OffsetDateTime.parse("2021-05-24T07:41:18.869+01:00", ISO_OFFSET_DATE_TIME_PARSER);

        OffsetDateTime dateTime1utc = dateTime1.withOffsetSameInstant(ZoneOffset.UTC);
        OffsetDateTime dateTime2utc = dateTime2.withOffsetSameInstant(ZoneOffset.UTC);

        System.out.println("As is : " + dateTime1.isEqual(dateTime2));
        System.out.println("Both UTC: " + dateTime1utc.equals(dateTime2utc));


    }


    @Test
    public void addTimeToDate()
    {

        final OffsetDateTime today = OffsetDateTime.now();


        final OffsetDateTime p1 = today.plusDays(100);
        final OffsetDateTime p2 = today.plusDays(200);
        final OffsetDateTime p3 = today.plusDays(300);

        System.out.println("today: " + today);
        System.out.println("   p1: " + p1);
        System.out.println("   p2: " + p2);
        System.out.println("   p3: " + p3);

    }

    @Test
    public void equalsBeforeAfter()
    {
        final OffsetDateTime today = OffsetDateTime.now();
        final OffsetDateTime today2 = today.minusDays(1);

        System.out.println("before " + isBeforeOrEqual(today, today2)) ;
        System.out.println("after " + isEqualOrAfter(today, today2)) ;
        System.out.println("equals " + today.equals(today2)) ;
    }


    private boolean isBeforeOrEqual(final OffsetDateTime reference, final OffsetDateTime candindate)
    {
        if(null == reference || null == candindate)
        {
            return false;
        }
        return candindate.isBefore(reference) || candindate.equals(reference);
    }


    private boolean isEqualOrAfter(final OffsetDateTime reference, final OffsetDateTime candindate)
    {
        if(null == reference || null == candindate)
        {
            return false;
        }
        return candindate.isAfter(reference) || candindate.equals(reference);
    }


    @Test
    public void reformateDateTime()
    {
        String startDateStr = "2019-06-29T12:00:59Z";

        final OffsetDateTime date = OffsetDateTime.parse(startDateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        System.out.println(Duration.between(OffsetDateTime.now(),date));
    }


    @Test
    public void timeBetween()
    {
        final OffsetDateTime t1 = OffsetDateTime.now();
        final OffsetDateTime t2 = t1.plusMinutes(60);

        final Duration d = Duration.between(t1, t2);

        System.out.println("Long time " + d.toMinutes());
    }

    @Test
    public void equals()
    {
        final String ss = "poo";

        System.out.println(ss.equals(null));
    }


    @Test
    public void zoneId()
    {

        LocalDateTime ldt = LocalDateTime.now().minusMonths(5);

        System.out.println(ldt);

        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        //zoneIds.forEach(z -> System.out.println(z));

        ZoneId zoneId = ZoneId.of("Pacific/Guam");

        System.out.println(Instant.now());

    }


    @Test
    public void durationMaths()
    {
        Duration d1 = Duration.ofDays(364*100);

        OffsetDateTime t1 = OffsetDateTime.now();


        System.out.println("t1 " + t1 + " t2 " + t1.plus(d1) + " " +  DateTimeFormatter.ISO_INSTANT.format(OffsetDateTime.now()) + " secs " + d1.getSeconds());
    }


    @Test
    public void getZeroOffsetTime()
    {

        final String startDateEvent1 = "2020-06-04T09:00:00Z";
        final String startDateEvent2 = "2020-06-04T10:00:00+01:00";

        final OffsetDateTime time = OffsetDateTime.parse(startDateEvent1);

        final OffsetDateTime time2 = OffsetDateTime.parse(startDateEvent2);

        System.out.println(time);
        System.out.println(time2);

        final String s1 = DATETIME_Z_FORMATTER.format(time.withOffsetSameInstant(ZoneOffset.UTC));
        final String s2 = DATETIME_Z_FORMATTER.format(time2.withOffsetSameInstant(ZoneOffset.UTC));

        System.out.println(s1);
        System.out.println(s2);


    }


    @Test
    public void getDate()
    {
        System.out.println(LocalDate.now().toString());
    }


    private static DateTimeFormatter DATETIME_Z_FORMATTER =
      new DateTimeFormatterBuilder().appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD).
                                     appendValue(MONTH_OF_YEAR, 2).appendValue(DAY_OF_MONTH, 2).
                                     appendLiteral('T').appendValue(HOUR_OF_DAY, 2).
                                     appendValue(MINUTE_OF_HOUR, 2).appendValue(SECOND_OF_MINUTE, 2).appendLiteral('Z').toFormatter().withResolverStyle(ResolverStyle.STRICT);



    // Use only for parsing; if used for output, all the optional sections would be output giving multiple offsets.
    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME_PARSER = new DateTimeFormatterBuilder().
                                                                           // date/time
                                                                             append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).
                                                                           // offset (hh:mm - "+00:00" when it's zero)
                                                                             optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd().
                                                                           // offset (hhmm - "+0000" when it's zero)
                                                                             optionalStart().appendOffset("+HHMM", "+0000").optionalEnd().
                                                                           // offset (hh - "Z" when it's zero)
                                                                             optionalStart().appendOffset("+HH", "Z").optionalEnd().
                                                                           // create formatter
                                                                             toFormatter();


}
