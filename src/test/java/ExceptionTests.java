import org.junit.Test;

public class ExceptionTests
{

    @Test
    public void testTryFinally()
    {

        try
        {
           final int[] arr = new int[2];
            System.out.println(arr[2]);
        }
        finally
        {
            System.out.println("FINALLY");
        }

    }

}
