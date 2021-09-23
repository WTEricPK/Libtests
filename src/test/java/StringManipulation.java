import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.common.base.Splitter;
import io.vertx.core.json.JsonObject;
import org.apache.commons.text.WordUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringManipulation
{


    @Test
    public void encode()
    {

        final String publicKey = "tjqrv7393l1xij8boq4276d33xs8vcm5p4ap1vkyvmq6sv8q62halb9ycxii61vy";
        final String privateKey = "hwbkytqnywngfkp3stnbt4vzofstdnm9a8886o52kparid2gncwcmmrg1ggp9e3a";

        final String combined = publicKey + ':' + privateKey;

        String encodedString =
          Base64.getEncoder().withoutPadding().encodeToString(combined.getBytes());

        final String apiKey = "your_api_key";


        //System.out.println(encodedString);

        System.out.println(Base64.getEncoder().withoutPadding().encodeToString(new JsonObject().put("key", apiKey).put("exp", 1437523200).encode().getBytes()));
    }


    @Test
    public void makeUriRegex()
    {

        String newUri = "https://www.padoq-2.com/dest-padoq/00001/";
        String base = "http://www.padoq.com/source-padoq/00001/";

        String match = "http://www\\.padoq\\.com/source-padoq/00001/(\\w+)$";


        if(match.startsWith(base.replace(".", "\\.")))
        {
            String newMatch = match.replace(base.replace(".", "\\."), newUri.replace(".", "\\."));

            System.out.println(newMatch);
        }

        System.out.println(Pattern.compile(base));

    }


    @Test
    public void endsWithUuid()

    {
        String uri = "ttp://www.padoq.com/doc/" + UUID.randomUUID();

        final Pattern pattern = Pattern.compile("/doc/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");
        final Matcher matcher = pattern.matcher(uri);
        matcher.find();
        System.out.println(matcher.start() == (uri.length() - 36 - 5));

        //System.out.println(matcher.find() + " " +  matcher.start() + " end " + matcher.end());

        System.out.println(uri.substring(uri.length() - 36));



    }


    @Test
    public void regex()
    {
        //final String pattern = "^\\w{3,64}$";
        final String pattern = "^[a-z0-9_]{3,64}$";


        System.out.println(Pattern.matches(pattern, "hello-1"));
        System.out.println(Pattern.matches(pattern, "hello_"));
        System.out.println(Pattern.matches(pattern, "hello"));
        System.out.println(Pattern.matches(pattern, "hello"));
        System.out.println(Pattern.matches(pattern, "Hello"));


    }


    @Test
    public void regex2()
    {
        final String pattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";


        System.out.println(Pattern.matches(pattern, "+447925542274"));
        System.out.println(Pattern.matches(pattern, "07925542274"));
    }



    @Test
    public void reformatDate()
    {

        String date = "2018-05-01";

        String[] split = date.split("-");
        String mergeDate = split[0] + split[1] + split[2];

        System.out.println("Org: " + date);
        System.out.println("End: " + mergeDate);

    }


    @Test
    public void stringTrim()
    {

        final String withSpaces = "  1243  hello  ";

        System.out.println("\"" + withSpaces + "\" \"" + withSpaces.trim() + "\"");

    }


    @Test
    public void printClassName()
    {

        final String name = OffsetDateTime.class.getName();

        System.out.println(name);

    }



    @Test
    public void reformatDate2()
    {

        //String date = "2017-05-03T18:30:00Z";
        String date = "2018-05-01";

        String[] split = date.split("-|:");
        final StringBuilder builder = new StringBuilder();
        Arrays.asList(split).stream().forEach(e -> builder.append(e));
        if(builder.length()==16)
        {
            builder.deleteCharAt(15);
        }

        System.out.println("Org: " + date);
        System.out.println("End: " + builder.toString());
    }


    @Test
    public void replaceString()
    {
        final String theStringWithName = "www.padoq.com/my-mums-padoq/worse";
        final String theStringWithout = "www.padoq.com/somethingelse/worse";

        final String target = "my-mums-padoq";
        final String newWord = "my-dads-padoq";


        System.out.println(theStringWithName.replace(target, newWord));
        System.out.println(theStringWithout.replace(target, newWord));

    }

    @Test
    public void replaceHiphensWithUnderscores()
    {

        Pattern pattern = Pattern.compile("-");

        String name = "then-name-with-hiphens";

        System.out.println(pattern.matcher(name).replaceAll("_"));

    }


    @Test
    public void urlENcode()
    {


    }


    @Test
    public void replaceTemplated()
    {

        Pattern pp = Pattern.compile("\\$\\{sequence.(?<sequenceName>[a-z0-9_]+)}");

        String ss = "sec_one${sequence.suba_ccount}";

        Matcher matcher = pp.matcher(ss);

        final StringBuffer result = new StringBuffer();
        while(matcher.find())
        {
            final String sequenceName = matcher.group("sequenceName");
            System.out.println(sequenceName);
            if(sequenceName != null)
            {
                matcher.appendReplacement(result, "THE_SEQUENCE_01");
            }
        }

        System.out.println(matcher.appendTail(result).toString());

    }


    @Test
    public void reformatDate3()
    {

        String date = "2017-05-03T18:30:00Z";

        DateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat formatOut = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        try
        {
            Date d = formatIn.parse(date);
            System.out.println(formatOut.format(formatIn.parse(date)));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


        System.out.println();
    }

    @Test
    public void reformatDate4()
    {
        //String date = "2019-04-12 07:55:03.550000";

        OffsetDateTime fdt = OffsetDateTime.now();


        ZoneOffset ff1 = fdt.getOffset();
        String zone = ff1.getId();
        System.out.println(zone);

        System.out.println(fdt);
        System.out.println(ISO_OFFSET_DATE_TIME_PARSER.format(fdt));
    }



    @Test
    public void textwrap()
    {

        //String massiveLine = "Hello my name is eric and I want to wrap the the text lines of a string by a fixed number of characters for my current task making an iCalendar for padoq event display views.";
        String massiveLine = "HellomynameisericandIwanttowrapthetextlinesofastringbyafixednumberofcharactersformycurrenttaskmakinganiCalendarforpadoqeventdisplayviews.";



        System.out.println(wrapped(massiveLine,20));

    }

    @Test
    public void insert()
    {
        String k = "hellohellohello";

        StringBuilder bb = new StringBuilder();
        bb.append(k);
        bb.insert(0,"HELLO");
        System.out.println(bb.toString());
    }

    private String wrapped( String longString, int charLength )
    {

        String firstPart = longString.substring(0,charLength);
        String lastPart = longString.substring(charLength);

        Iterable<String> split = Splitter.fixedLength(charLength-1).split(lastPart);
        StringBuilder builder = new StringBuilder();
        builder.append(firstPart);
        builder.append("\n");
        split.forEach( s ->
                       {builder.append(" ");
                        builder.append(s);
                        builder.append("\n");});

        return builder.toString();
    }

    @Test
    public void testSumElementLength()
    {

        List<String> entries = new ArrayList<>();
        entries.add("1");
        entries.add("12");
        entries.add("123");
        int nElements  = entries.stream().mapToInt( String::length ).sum();

        Assert.assertEquals(6, nElements);
        final StringBuilder strBuild = new StringBuilder(nElements);
        entries.forEach( s -> strBuild.append(s) );

        System.out.println( nElements + " elements in string " + strBuild.toString());
    }


    @Test
    public void testShowJoin()
    {
        final List<String> entries = new ArrayList<>();
        entries.add("1");
        entries.add("12");
        entries.add("123");

        System.out.println("Joined " + String.join(",",entries));
    }


    @Test
    public void multiSpace()
    {

        final String string = "hello   world. ";

        final String[] split = string.split(" ");

        final List<String> splitFiltered = new ArrayList<>();
        for(final String s:split)
        {
            if(s.length() == 0)
            {
                continue;
            }
            splitFiltered.add(s);
        }

        for(final String s:splitFiltered)
        {
            System.out.println(s);
        }

    }



    @Test
    public void join()
    {
        int nStrings = 1000;
        List<String> list = new ArrayList<>(nStrings);

        for(int i = 0; i < nStrings; i++)
        {
            list.add("This is a longish string that we will be concatinating");
        }

        int nTests = 100;
        for(int i = 0; i < nTests; i++)
        {
            listToSingleString(list);
        }
        for(int i = 0; i < nTests; i++)
        {
             joined(list);
        }

        long start2, stop2;
        start2 = System.currentTimeMillis();
        for(int i = 0; i < nTests; i++)
        {
            listToSingleString(list);
        }
        stop2 = System.currentTimeMillis();

        // time each one
        long start1, stop1;
        start1 = System.currentTimeMillis();
        for(int i = 0; i < nTests; i++)
        {
            joined(list);
        }
        stop1 = System.currentTimeMillis();

        long time1, time2;
        time1 = (stop1-start1);
        time2 = (stop2-start2);

        System.out.println("Java join " + time1 + " list concat " + time2 + " ratio " + ((double)time1/(double)time2));

    }

    private String joined( List<String> strings )
    {
        return String.join("",strings);
    }

    private String joined( String ... strings )
    {
        return String.join("",strings);
    }

    private String listToSingleString( final List<String> entries )
    {
        int nElements  = entries.stream().mapToInt( String::length ).sum();
        final StringBuilder strBuild = new StringBuilder(nElements);//
        entries.forEach( s -> strBuild.append(s) );
        return strBuild.toString();
    }

    private String listToSingleString2( final List<String> entries )
    {
        int nElements  = entries.stream().mapToInt( String::length ).sum();
        final StringBuffer strBuild = new StringBuffer(nElements);
        entries.forEach( s -> strBuild.append(s) );
        return strBuild.toString();
    }

    @Test
    public void parseIntTest()
    {

        System.out.println((int)Long.parseLong("2462561185"));

    }

    @Test
    public void setIsoOffsetDateTimeParser()
    {

        System.out.println(OffsetDateTime.parse("2019-05-31T13:40:22.790+01:00"));
        //System.out.println(OffsetDateTime.parse("2019-05-31T13:58:25.927+01:00"));

    }

    @Test
    public void testLocale()
    {

        final String localeStr = "en-GB";


        System.out.println("'" +  Locale.forLanguageTag("en_GB") + "' '" + Locale.forLanguageTag("en-GB").toString() + "'");

    }

    @Test
    public void randomIntegerFixedDecimals()
    {
        final String email = "eric@padoq.com";

        Random random = new Random(email.hashCode());
        String strDouble = "+447" + String.format("%09d", (int)Math.round(100000000d*random.nextDouble()));


        System.out.println(strDouble + " length " + strDouble.length());
    }


    @Test
    public void cronDuration()
    {



        System.out.println( OffsetDateTime.now().minus(java.time.Duration.parse("P14D")) );



    }

    // Use only for parsing; if used for output, all the optional sections would be output giving multiple offsets.
    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME_PARSER = new DateTimeFormatterBuilder().
                                                                           // date/time
                                                                             append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).
                                                                           // offset (hh:mm - "+00:00" when it's zero)
                                                                           //  optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd().
                                                                           // offset (hhmm - "+0000" when it's zero)
                                                                            // optionalStart().appendOffset("+HHMM", "+0000").optionalEnd().
                                                                           // offset (hh - "Z" when it's zero)
                                                                           //  optionalStart().appendOffset("+HH", "Z").optionalEnd().
                                                                           // create formatter
                                                                             toFormatter();

    @Test
    public void testParseDateTime()
    {
        String dateNoTime = "2019-05-01";

        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ff2 = new SimpleDateFormat("");

        OffsetDateTime odt = OffsetDateTime.from(ISO_OFFSET_DATE_TIME_PARSER.parse(dateNoTime));

        System.out.println(odt);
    }

    @Test
    public void cheetDateFormate()
    {
        String s = "2020-04-02T15:00:12.433+01:00";



        s = s.replace('T', ' ');

        System.out.println(s.split("\\.")[0]);

    }



}
