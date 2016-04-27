package main;

import mail.CreateMail;
import mail.MailUtil;
import model.MailContent;
import org.apache.commons.cli.*;

/**
 * Created by bojack on 16/4/26.
 */
public class MailMain {


    /**
     * 读取文件
     * @param args
     * @return
     */
    private static CommandLine parseMainArg(String[] args) {
        Options options = new Options();
        options.addOption("f", true, "must have file");

        Parser parser = new GnuParser();
        try {
            CommandLine line = parser.parse(options, args);
            return line;
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            throw new RuntimeException(exp);
        }
    }


    public static void main(String[] args) {
        CommandLine line = parseMainArg(args);
        String filePath = line.getOptionValue("f");
        MailContent content = CreateMail.transfrom(filePath);
        MailUtil.sendSimpleMail(content.getFrom(),content.getTo().split(","), content.getSubject(),content.getContent());
    }



//    public static void main(String[] args) {
//        MailContent content = CreateMail.transfrom("NewFile");
//        MailUtil.sendSimpleMail(content.getFrom(),content.getTo().split(","), content.getSubject(),content.getContent());
//
//    }
}
