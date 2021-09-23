package util;

public class StringUtils
{



    /**
     * Determines if the String is a number.
     *
     * @param str the candidate String.
     *
     * @return true if the str is a number represented as a string. false otherwise.
     */
    public static boolean isNumber(final String str)
    {
        if( str==null || str == "")//
        {
            return false;
        }

        try
        {
            Double num = Double.parseDouble(str);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

}
