import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class RequiredInfoRedo
{

    public static final String INFO_ITEM = "InfoItem";
    public static final String TEXT_ITEM = "TextItem";
    public static final String CHOICE_INFO_ITEM = "ChoiceInfoItem";
    public static final String CHOICES_INFO_ITEM = "ChoicesInfoItem";

    public static final String ID = "id";
    public static final String QUESTION = "question";
    public static final String NEXT_QUESTION = "nextQuestion";
    public static final String SKIP_TO_QUESTION = "skipToQuestion";
    public static final String TYPE = "type";
    public static final String LABEL = "label";
    public static final String ANSWER = "answer";
    public static final String OPTIONS = "options";


    public static final String ADMIN_ONLY = "adminOnly";


    public static void main(String[] args)
    {

        RequiredInfoRedo r = new RequiredInfoRedo();
        r.run();
    }


    public RequiredInfoRedo()
    {

    }


    public void run()
    {

        allowMethods("PATCH");

//         "burlington-square" - bad request
//
//
//         , norfolk-house, norfolk-house-2, , urban-bubble,  could not find the specified file
//
//
//
//
//        final List<String> names = Arrays.asList("meadow-view", "icon-25-smithfield");
//
//                final List<String> names = Arrays.asList("baltic-triangle", "britannia-house",
//                                             "ingenta", "kensington-place", "piccadilly-village");
//
//
//        final List<String> names = Arrays.asList("whitworth-house", "the-point-canal-point-a", "wharf-close-apartments",
//                                                 "wentwood", "springfield-court",
//                                                 "the-point-estate", "market-buildings-smithfield",
//                                                 "the-point-avro-house-c", "the-heart-media-city");
//
//         getting bad requests from these
//        final List<String> names = Arrays.asList("timekeepers-square", "chapeltown-wharf", "city-point-2", "peel-court",
//                                                 "steepleview", "the-way-zone-1", "the-lightbox-commercial",
//                                                 "carpino-place", "chimney-pot-park", "everglades", "paradise-wharf",
//                                                 "melrose-apartments");
//
//        final List<String> names = Arrays.asList("ringley-lock", "southside", "hart-road--parkside",
//                                                 "one-smithfield-square", "albert-house",
//                                                  "briarfield-court", "clarence-mill",
//                                                 "didsbury-point-4", "ducie-wharf");
//
//
//        final List<String> names = Arrays.asList("heatley-manor", "springbank-gardens",
//                                                 "the-design-house-smithfield", "the-lightbox", "the-sorting-house",
//                                                 "martin-house",
//                                                 "vintage-quay", "victoria-house", "nuovo-apartments");
//
//
//        final List<String> names = Arrays.asList("vimto-gardens", "victoria-mills", "piccadilly-lofts", "pall-mall-house",
//                                              "light-buildings", "regents-court", "royal-court-drive",
//                                             "smithfield-estate", "the-point-burgess-house-b",
//                                             "freshfields-apartments");
//
//        final List<String> names = Arrays.asList("timekeepers-square", "chapeltown-wharf", "city-point-2", "steepleview", "the-way-zone-1", "carpino-place", "chimney-pot-park", "everglades", "paradise-wharf", "melrose-apartments", "arrivato-plaza");

        // these have no building information.
//        final List<String> names = Arrays.asList("the-way-zone-1", "burlington-square", "arrivato-plaza", "norfolk-house", "norfolk-house-2","urban-bubble");


      //  final List<String> names = Arrays.asList("briarfield-court");



       // final List<String> names = Arrays.asList( "meadow-view",  "melrose-apartments",  "nuovo-apartments",  "one-smithfield-square",  "paradise-wharf",  "chimney-pot-park",  "springfield-court",  "the-point-avro-house-c",  "britannia-house",  "carpino-place",  "chapeltown-wharf",  "clarence-mill",  "didsbury-point-4",  "ducie-wharf");

        ////final List<String> names = Arrays.asList("steepleview",  "smithfield-estate",  "the-heart-media-city",  "the-lightbox",  "the-point-burgess-house-b",  "victoria-house",  "victoria-mills",  "vimto-gardens",  "wentwood",  "wharf-close-apartments",  "everglades",  "freshfields-apartments",  "hart-road--parkside",  "heatley-manor",  "icon-25-smithfield");

        //// final List<String> names = Arrays.asList("ingenta",  "kensington-place",  "martin-house",  "peel-court",  "pall-mall-house",  "the-point-canal-point-a",  "the-point-estate",  "the-sorting-house",  "the-way-zone-1",  "advent-2-and-3",  "My-Building",  "baltic-triangle",  "city-point-2",  "regents-court",  "ringley-lock",  "royal-court-drive",  "southside");

        ////final List<String> names = Arrays.asList("springbank-gardens",  "timekeepers-square",  "no-1-media-city",  "whitworth-house",  "piccadilly-lofts",  "piccadilly-village",  "vintage-quay",  "albert-house",  "arrivato-plaza",  "light-buildings",  "market-buildings-smithfield",  "the-design-house-smithfield");

        final List<String> names = Arrays.asList("briarfield-court", "meadow-view", "melrose-apartments", "nuovo-apartments", "one-smithfield-square", "paradise-wharf", "chimney-pot-park", "springfield-court", "the-point-avro-house-c", "britannia-house", "carpino-place", "chapeltown-wharf", "clarence-mill", "didsbury-point-4", "ducie-wharf", "steepleview", "smithfield-estate", "the-heart-media-city", "the-lightbox", "the-point-burgess-house-b", "victoria-house", "victoria-mills", "vimto-gardens", "wentwood", "wharf-close-apartments", "everglades", "freshfields-apartments", "hart-road--parkside", "heatley-manor", "icon-25-smithfield", "ingenta", "kensington-place", "martin-house", "peel-court", "pall-mall-house", "the-point-canal-point-a", "the-point-estate", "the-sorting-house", "the-way-zone-1", "advent-2-and-3", "My-Building", "baltic-triangle", "city-point-2", "regents-court", "ringley-lock", "royal-court-drive", "southside", "springbank-gardens", "timekeepers-square", "no-1-media-city", "whitworth-house", "piccadilly-lofts", "piccadilly-village", "vintage-quay", "albert-house", "arrivato-plaza", "light-buildings", "market-buildings-smithfield", "the-design-house-smithfield", "urban-bubble");


        //final List<String> names = Arrays.asList("albert-house");

        final String folder = "C:\\Users\\eric pitkeathly\\Documents\\reqInfo\\buildingnames\\";

        final String tokenId = "1d78cc1a-80ab-11ea-861c-005056b62c78";

        for(String padoqName:names)
        {

            long waitime = 100;//5000 + Math.round((Math.random() - 0.5d) * 2000d);

            try
            {
                Thread.sleep(waitime);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            run1(folder, padoqName, tokenId);

            //System.out.println("Done padoq: " + padoqName);
        }

    }


    private void run1(final String path, final String padoqName, final String tokenId)
    {


        final List<String> appartMentNames = getOptions(path+padoqName+".txt");

        if(null == appartMentNames)
        {
            System.out.println(padoqName);
        }

//        final JsonObject requestBody = new JsonObject();
//
//        final JsonArray questions = questions(appartMentNames);
//
//        requestBody.put("personaDetailsSchema", new JsonObject().put("requiredInfo", questions).put("forceRequiredInfoUpdate", "2020-04-16T23:59:59Z"));//
//
//        makeUpdatePadoqDetailsRequest(padoqName, tokenId, requestBody);
    }


    private JsonArray questions(final List<String> options)
    {

        final JsonObject question0 = createInfoItem("Full name", 0, null);

        final JsonObject question1 = createInfoItem("Email address", 1,
                                                    "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

        final JsonObject question2 = createInfoItem("Phone number", 2, null);

        final JsonObject question4 = createChoiceInfoItem("Type of Customer",
                                                          Arrays.asList("Owner living in the property",
                                                                        "Owner not living in the property",
                                                                        "Rental tenant", "Letting agent", "Business"),
                                                          4).put(NEXT_QUESTION, new JsonArray(Arrays.asList(5, 5, 5, 5, 5)));
        final JsonObject question5;
        if(null == options)
        {
            question5 = createInfoItem("Apartment/house number",5, null).put(SKIP_TO_QUESTION, -1);
        }
        else
        {
            question5 = createChoiceInfoItem("Apartment/House number", options, 5).put(SKIP_TO_QUESTION, -1);
        }


//        final JsonObject question5 = createChoiceInfoItem("Apartment/House number", options, 5)
//                                       .put(SKIP_TO_QUESTION, -1);

        final JsonObject question6 = createInfoItem("Tenancy end date (dd/mm/yyyy)", 6, "^\\d{1,2}/\\d{1,2}/\\d{4}$")
                                       .put(SKIP_TO_QUESTION, 5).put("disabled", Boolean.TRUE);


        return new JsonArray().add(question0).add(question1).add(question2).add(question4).add(question5)
                              .add(question6);
    }


    private List<String> getOptions(final String filename)
    {
        List<String> out = null;
        InputStream is = null;
        try
        {
            is = new FileInputStream(filename);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            final List<String> opts = buf.lines().collect(Collectors.toList());

            out = opts;
        }
        catch(final FileNotFoundException e)
        {
            //e.printStackTrace();
        }

        return out;
    }


    private JsonObject createInfoItem(final String label, final Integer questionNumber, final String pattern)
    {
        final JsonObject out = new JsonObject().put(TYPE, INFO_ITEM).put(LABEL, label).put(ADMIN_ONLY, Boolean.TRUE);
        ; //
        if(pattern != null)
        {
            out.put("format", pattern);
        }

        if(null != questionNumber)
        {
            out.put(QUESTION, questionNumber);
        }

        return out;
    }


    private JsonObject createChoiceInfoItem(final String label, final List<String> options,
                                            final Integer questionNumber)
    {
        final JsonObject out = new JsonObject().put(TYPE, CHOICE_INFO_ITEM).put(LABEL, label)
                                               .put(QUESTION, questionNumber).put(OPTIONS, new JsonArray(options))
                                               .put(ADMIN_ONLY, Boolean.TRUE);

        if(null != questionNumber)
        {
            out.put(QUESTION, questionNumber);
        }

        return out;
    }

    private void makeUpdatePadoqDetailsRequest(final String padoqName, final String tokenId, final JsonObject request)
    {
        try
        {


            //final URL url = new URL("https://app.dev.padoq.com/resrc/padoq/" + padoqName);
            final URL url = new URL("https://api.padoq.com/resrc/padoq/" + padoqName);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + tokenId);

            conn.setRequestProperty("charset", "utf-8");

            String input = request.encode();


            final byte[] bytes = input.getBytes();

            conn.setRequestProperty("Content-Length", "" + bytes.length);

            OutputStream os = conn.getOutputStream();
            os.write(bytes);
            os.flush();


            if(conn.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT)
            {
                System.out.println("Issue: " + conn.getResponseCode() + " for padoq " + padoqName);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
              (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while((output = br.readLine()) != null)
            {
                System.out.println(output);
            }
//
//            JsonObject jj = new JsonObject(br.lines().collect(Collectors.joining()));
//            System.out.println(jj.encodePrettily());

            conn.disconnect();
        }
        catch(MalformedURLException e)
        {

            e.printStackTrace();
        }
        catch(IOException e)
        {

            e.printStackTrace();
        }
    }


    private static void allowMethods(String... methods)
    {
        try
        {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[])methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/*static field*/, newMethods);
        }
        catch(NoSuchFieldException | IllegalAccessException e)
        {
            throw new IllegalStateException(e);
        }
    }

//    private JsonObject createChoicesInfoItem(final String label, final List<String> options, final Integer questionNumber)
//    {
//        final JsonObject out = new JsonObject().put(TYPE, CHOICES_INFO_ITEM)
//                                               .put(LABEL, label).put(QUESTION, questionNumber).put(OPTIONS, new JsonArray(options)).put(ADMIN_ONLY, Boolean.FALSE);;
//
//        if(null != questionNumber)
//        {
//            out.put(QUESTION, questionNumber);
//        }
//
//        return out;
//    }
}
