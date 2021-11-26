package com.jijie.crontaskforlife.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Description: CronTaskService
 *
 * @author jijie
 * @date 2021/11/26 @time 16:52
 */
@EnableScheduling
public class CronTaskService {

    @Scheduled(cron = "0 0 9 29 * ?")
    public void cronTaskOne() throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        //smtp.163.com
        //smtp.qq.com
        props.put("mail.smtp.host","smtp.163.com");// smtp服务器地址
        Session session = Session.getInstance(props);
        session.setDebug(true);
        Message msg = new MimeMessage(session);
        msg.setSubject("定时任务");
        msg.setText(" 积分要过期啦！！！");
        msg.setFrom(new InternetAddress("18935114910@163.com"));//发件人邮箱(我的163邮箱)
        msg.setRecipient(Message.RecipientType.TO,
                new InternetAddress("867346987@qq.com")); //收件人邮箱(我的163邮箱)
        msg.saveChanges();
        Transport transport = session.getTransport();
        transport.connect("18935114910@163.com","jj789632145");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("邮件发送成功...");
        transport.close();
    }

}
