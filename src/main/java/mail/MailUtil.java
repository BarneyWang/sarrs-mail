package mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author bojack
 */
public class MailUtil {

    public static void sendSimpleMail(String from, String[] to, String subject, String content) {
        try {

            SimpleMailMessage simpleMailMessage = getSimpleMailMessage(from, to, subject, content);
            sendSimpleMail(simpleMailMessage);
        } catch (Exception e) {
            String info = "from:" + from + ",to:" + Arrays.toString(to) + ",subject:" + subject + ",content:" + content;
            throw new RuntimeException(info, e);
        }
    }

    public static void sendSimpleMail(SimpleMailMessage simpleMailMessage) {
        try {
            JavaMailSenderImpl mailSender = getJavaMailSender(simpleMailMessage.getFrom());
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setFrom(simpleMailMessage.getFrom());
            messageHelper.setTo(simpleMailMessage.getTo());
            messageHelper.setSubject(simpleMailMessage.getSubject());
            messageHelper.setText(simpleMailMessage.getText(), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            String info = "mimeMessage:" + simpleMailMessage.toString();
            throw new RuntimeException(info, e);
        }
    }

    public static SimpleMailMessage getSimpleMailMessage(String from, String[] to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        return simpleMailMessage;
    }

    public static JavaMailSenderImpl getJavaMailSender(String from) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // 设置邮件服务主机
        mailSender.setHost("mail.letv.com");
        // 发送者邮箱的用户名
        mailSender.setUsername(from);
        // 发送者邮箱的密码
        mailSender.setPassword("");

        mailSender.setDefaultEncoding("UTF-8");

        Properties pro = new Properties();

        pro.put("mail.smtp.auth", true);

        pro.put("mail.smtp.timeout", 25000);

        mailSender.setJavaMailProperties(pro);
        return mailSender;
    }

    public static void main(String[] args) {
        sendSimpleMail("wangdi5@letv.com", new String[]{"wangdi5@letv.com"}, "测试", "测试");
    }

}