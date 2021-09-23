import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

public class Enums
{

    interface Action
    {
        public Set<String> requiredParams();


        /**
         *  States the requirements for the parameter names.
         */
        public enum ParamRequirement
        {
            /** All required params are required.*/
            ALL,
            /** A single param from a set of required params is required.*/
            ONE,
            /** A single param is required if any are specified at all.*/
            ZERO_OR_ONE,
            /** Any number of the specified params is permitted.*/
            ANY
        }

        default boolean getInt(Action action, Set<String> params)
        {
            return action.requiredParams().containsAll(params);
        }
    }

    enum TileAction implements Action
    {
        NO_ACTION,
        ACTION
          {
              @Override
              public Set<String> requiredParams()
              {
                  return ImmutableSet.of("padoqName");
              }
          };

        @Override
        public Set<String> requiredParams()
        {
            return null;
        }

    }


    @Test
    public void actiontest()
    {

        System.out.println(TileAction.ACTION);
        System.out.println(TileAction.ACTION.requiredParams());
        System.out.println(TileAction.NO_ACTION);


    }

    @Test
    public void toStrings()
    {

        Map<String, Object> map = new HashMap<>();

        map.put("fred", new Integer(1));



        System.out.println(Transp.valueOf("fuck"));

    }


//    @Test
//    public void genericEnum()
//    {
//
//        final Enum  e = TileAction;
//
//    }


    @Test
    public void enumOrder()
    {
        System.out.println(ChronoUnit.HOURS.between(OffsetDateTime.now(), OffsetDateTime.now()) + " " + ChronoUnit.DAYS.hashCode() + " " + ChronoUnit.MONTHS.hashCode() );
    }


    @Test
    public void fromString()
    {
        System.out.println(Transp.valueOf("transparen".toUpperCase()));
    }


    enum Transp
    {
        TRANSPARENT,
        OPAQUE
    }

}
