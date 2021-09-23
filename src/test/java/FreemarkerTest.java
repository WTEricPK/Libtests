import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import freemarker.template.Configuration;
//import freemarker.template.Template;
import org.junit.Test;

public class FreemarkerTest
{

    @Test
    public void testTemplate() throws Exception
    {

//        //Configuration config = new Configuration();
//        //config.setDirectoryForTemplateLoading(new File("C:/Users/eric pitkeathly/IdeaProjects/Libtests/src/test/java/"));
//
//
//        Template template = new Configuration().getTemplate("/src/test/java/testtemplate.ftl");
//        Map<String, Object> root = new HashMap<>();
//
//// Put string "user" into the root
//        root.put("user", "Big Joe");
//        root.put("boozer", "Big Joe2");
//        root.put("crewser", "Big Joe3");
//
//        User user1 = new User("Jhon", "Doe", "jon@gmail.com", "1");
//        User user2 = new User("Ben", "Dover", "bendover@gmail.com", "7");
//        User user3 = new User("Eric", "Iscool", "ericiscool@gmail.com", "199");
//
//        root.put("lll", Arrays.asList(user1, user2, user3));
//
//        File file = new File("file.html");
//        Writer out = new OutputStreamWriter(new FileOutputStream(file));
//        template.process(root, out);
//        out.close();
//        Desktop.getDesktop().browse(file.toURI());
    }


    public class User
    {

        public User(String firstName, String secondName, String email, String attempts)
        {
            this.firstName = firstName;
            this.secondName = secondName;
            this.email = email;
            this.attempts = attempts;
        }

        public String getEmail(){return email;}
        public String getFirstName(){return firstName;}
        public String getSecondName(){return secondName;}
        public String getAttempts(){return attempts;}

        final String firstName, secondName, email, attempts;
    }

}
