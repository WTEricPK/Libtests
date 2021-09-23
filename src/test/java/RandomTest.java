import java.util.Random;

import org.junit.Test;

public class RandomTest
{

    @Test
    public void randomInt()
    {
        final Random r = new Random();

        for(int i = 0; i < 100; i++)
        {
            System.out.println(""+(r.nextInt(3)+1));
        }
    }


    @Test
    public void ifLops()
    {

        final Integer one = 1;
        final Integer two = 2;
        if(one == null)
        {
            System.out.println("IF");
        }
        else if(two.equals(2))
        {
            System.out.println("ELSE IF");
        }
        else
        {
            System.out.println("ELSE");
        }

    }

}
