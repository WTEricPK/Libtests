import java.util.UUID;

import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.junit.Test;

public class Log4j2Tests
{

//    @Test
//    public void givenLoggerWithDefaultConfig()
//    {
//
//        Logger LOGGER = LogManager.getLogger(Log4j2Tests.class.getName());
//
//        //Add context information
//        ThreadContext.put("clientId", UUID.randomUUID().toString());
//        ThreadContext.put("namespace", "namespace");
//        ThreadContext.put("postId", UUID.randomUUID().toString());
//        ThreadContext.put("padoqName", "padoq_name");
//        ThreadContext.put("authorId", UUID.randomUUID().toString());
//        ThreadContext.put("alias", "ben");
//        ThreadContext.put("action", "click");
//
//        LOGGER.info("Event click");
//        //Clear the map
//        ThreadContext.clearMap();
//
//    }


    @Test
    public void givenLoggerWithDefaultConfig1()
    {

        Logger LOGGER = LoggerFactory.getLogger(Log4j2Tests.class);
        //MDC mdc =;

        for(int i = 0; i < 10; i++ )
        {
            //Add context information
            MDC.put("clientId", UUID.randomUUID().toString());
            MDC.put("namespace", i%2 == 1 ? "padoq":"watford-fc");
            MDC.put("postId", UUID.randomUUID().toString());
            MDC.put("padoqName", "padoq_name");
            MDC.put("authorId", UUID.randomUUID().toString());
            MDC.put("alias", "ben");
            MDC.put("action", "click");


            LOGGER.info("");
            //Clear the map
            MDC.clear();
        }

    }


}
