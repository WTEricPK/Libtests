import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.vertx.core.json.JsonArray;
import org.junit.Test;

public class IOproblems
{

    @Test
    public void testReadString() throws Exception
    {
        InputStream is = new FileInputStream("C:\\Users\\eric pitkeathly\\Desktop\\readnames.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();


        while(line != null)
        {
            sb.append(line).append("\n");
            line = buf.readLine();

        }

        String fileAsString = sb.toString();
        System.out.println("Contents : " + fileAsString);
    }


    @Test
    public void testFileToJsonArray()
    {

        final String path = "C:\\Users\\eric pitkeathly\\Desktop\\readnames - Copy.txt";

        final JsonArray ops = getOptions(path);

        System.out.println(ops);
    }


    private JsonArray getOptions(final String filename)
    {
        JsonArray out = null;
        InputStream is = null;
        try
        {
            is = new FileInputStream(filename);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            final List<String> opts = buf.lines().map( s -> {
                String sOut = s;
                if(s.endsWith(","))
                {
                    sOut = sOut.substring(0, sOut.length()-1);
                }
                sOut = sOut.replace("\"", "");
                return sOut;
            }).collect(Collectors.toList());

            out = new JsonArray(opts);

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return out;
    }


}
