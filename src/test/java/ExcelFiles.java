import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;


/**
 * Tests for reading and writing excel .xlsx files.
 */
public class ExcelFiles
{


    @Test
    public void redFile() throws Exception
    {

        final String padoqName = "test";

        final String preFix = "\"Meadow View, Apartment";

        final String pathOut = "C:\\Users\\eric pitkeathly\\Documents\\reqInfo\\buildingnames\\";



        final String filepath = "C:\\Users\\eric pitkeathly\\Documents\\reqInfo\\UrbanBubbleApparments.xlsx";

        FileInputStream excelFile = new FileInputStream(new File(filepath));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();

        //List<String> values = new ArrayList<>();

        final SetMultimap<String, String> appartments = HashMultimap.create();
        int n = 0;
        while(iterator.hasNext())
        {
            Row currentRow = iterator.next();

//            if(n == 0)
//            {
//                n++;
//                continue;
//            }

            final String appartment = currentRow.getCell(0).getStringCellValue();
            final String key = currentRow.getCell(1).getStringCellValue();

            if(key != null && appartment != null)
            {
                appartments.put(key, appartment);
            }

        }

        final Map<String, Collection<String>> map = appartments.asMap();

        for(Map.Entry<String, Collection<String>> e:map.entrySet())
        {
            final List<String> values = new ArrayList<>(e.getValue());

            Collections.sort(values);

            toFile(values, pathOut + e.getKey());
        }

        //

    }

    private void toFile(final List<String> list, final String filepath)
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter(filepath + ".txt"));
            for(String s:list)
            {
                writer.write(s);
                writer.newLine();
            }
        }
        catch ( final IOException e)
        {

        }
        finally
        {
            try
            {
                if ( writer != null)
                {
                    writer.close();
                }
            }
            catch ( IOException e)
            {
            }
        }
    }


}
