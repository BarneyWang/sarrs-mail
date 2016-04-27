package mail;

import model.MailContent;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by bojack on 16/4/26.
 */
public class CreateMail {


    public static MailContent transfrom(String fileName) {
        Serializer serializer = new Persister();
        File file = new File(fileName);
        if(!file.exists()){
            return null;
        }
        try {
            InputStream is = new FileInputStream(file);
            MailContent mailContent = serializer.read(MailContent.class, is);
            return mailContent;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

    }


}
