import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class HttpClient
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


    @Test
    public void getClientGet()
    {

        // http://localhost:8080/RESTfulExample/json/product/get

        try
        {

            final URL url = new URL("https://app.dev.padoq.com/concourse/home/padoq/status/");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Authorization", "Bearer d8db187d-7ee1-11ea-8845-7427ea56e821");

            if(conn.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed : HTTP error code : "
                                             + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
//                System.out.println("Output from Server .... \n");
//                while ((output = br.readLine()) != null) {
//                    System.out.println(output);
//                }

            JsonObject jj = new JsonObject(br.lines().collect(Collectors.joining()));
            System.out.println(jj.encodePrettily());


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


    @Test
    public void testPadoqUpdate()
    {

        allowMethods("PATCH");

        // "the-castle"  "The-Garage"  "the-way-ground-rent" "The-Shed"

//        final List<String> names = Arrays
//                                     .asList("meadow-view", "icon-25-smithfield", "baltic-triangle", "britannia-house",
//                                             "ingenta", "kensington-place", "piccadilly-village");

//
//        final List<String> names = Arrays.asList("whitworth-house", "the-point-canal-point-a", "wharf-close-apartments",
//                                                 "wentwood", "burlington-square", "springfield-court",
//                                                 "the-point-estate", "market-buildings-smithfield",
//                                                 "the-point-avro-house-c", "the-heart-media-city");

//        final List<String> names = Arrays.asList("timekeepers-square", "chapeltown-wharf", "city-point-2", "peel-court",
//                                                 "steepleview", "the-way-zone-1", "the-lightbox-commercial",
//                                                 "carpino-place", "chimney-pot-park", "everglades", "paradise-wharf",
//                                                 "melrose-apartments");

//        final List<String> names = Arrays.asList("ringley-lock", "southside", "hart-road--parkside",
//                                                 "one-smithfield-square", "albert-house",
//                                                 "arrivato-plaza", "briarfield-court", "clarence-mill",
//                                                 "didsbury-point-4", "ducie-wharf");

//        final List<String> names = Arrays.asList("heatley-manor", "springbank-gardens", "norfolk-house",
//                                                 "the-design-house-smithfield", "the-lightbox", "the-sorting-house",
//                                                 "martin-house", "norfolk-house-2",
//                                                 "vintage-quay", "victoria-house", "nuovo-apartments");

//        final List<String> names = Arrays.asList("vimto-gardens", "victoria-mills", "piccadilly-lofts", "pall-mall-house",
//                                             "urban-bubble", "light-buildings", "regents-court", "royal-court-drive",
//                                             "smithfield-estate", "the-point-burgess-house-b", "Test-Group", "The-Shed",
//                                             "freshfields-apartments");





        final List<String> names = Arrays.asList("no-1-media-city");
        final JsonObject request = new JsonObject();
        request
          .put("personaDetailsSchema", new JsonObject().put("forceRequiredInfoUpdate", "2020-04-14T20:27:17.793Z"));
        //final String padoqName = "e-test1";
        final String tokenId = "b607b3cc-7ff3-11ea-861c-005056b62c78";

        for(String padoqName : names)
        {

            long waitime = Math.round(Math.random() * 2000) + Math.round(Math.random() * 2000);

            try
            {
                Thread.sleep(waitime);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println("Done padoq: " + padoqName);
            makeUpdatePadoqDetailsRequest(padoqName, tokenId, request);
        }


        System.out.println("Finished");
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
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + tokenId);

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


    private JsonObject createInfoItem(final String label, final Integer questionNumber, final String pattern)
    {
        final JsonObject out = new JsonObject().put(TYPE, INFO_ITEM).put(LABEL, label); //
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
                                               .put(QUESTION, questionNumber).put(OPTIONS, new JsonArray(options));

        if(null != questionNumber)
        {
            out.put(QUESTION, questionNumber);
        }

        return out;
    }


    private JsonObject createChoicesInfoItem(final String label, final List<String> options,
                                             final Integer questionNumber)
    {
        final JsonObject out = new JsonObject().put(TYPE, CHOICES_INFO_ITEM)
                                               .put(LABEL, label).put(QUESTION, questionNumber)
                                               .put(OPTIONS, new JsonArray(options));

        if(null != questionNumber)
        {
            out.put(QUESTION, questionNumber);
        }

        return out;
    }
}
