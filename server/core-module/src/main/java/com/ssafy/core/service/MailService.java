package com.ssafy.core.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
// 메일 전송 서비스
public class MailService {
    private final JavaMailSender mailSender;

    private final Configuration configuration;

    // application yml 설정파일에 설정한 값 사용
    @Value("${spring.mail.username}")
    private String senderEmailAddr;

    // application yml 설정파일에 설정한 값 사용
    @Value("${spring.mail.to-name}")
    private String toEmailAddr;



    /**
     * Email 전송 (받는 주소, 제목, 내용)
     * 보내는 주소는 설정값으로 고정
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content) {
        if ( to.indexOf("@test.com") >= 0 ) return ;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmailAddr);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("sendSimpleMail done!");
        } catch (Exception e) {
            log.error("sendSimpleMail exception！", e);
        }
    }


    /**
     * Email 전송 (보내는 주소, 제목, 내용)
     * 받는 주소는 설정값으로 고정
     * @param from
     * @param subject
     * @param content
     * @return
     */
    public String sendSimpleMailFrom(String from, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmailAddr);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("sendSimpleMail done!");
            return "success";
        } catch (Exception e) {
            log.error("sendSimpleMail exception！", e);
            return "failed";
        }
    }

    /**
     * Email HTML 전송
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content) {
        if ( to.indexOf("@test.com") >= 0 ) return ;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmailAddr);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("sendHtmlMail done!");
        } catch (MessagingException e) {
            log.error("sendHtmlMail exception！", e);
        }
    }

    /**
     * Email 첨부파일 추가 전송
     * @param to
     * @param subject
     * @param content
     * @param filename
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filename) {
        if ( to.indexOf("@test.com") >= 0 ) return ;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmailAddr);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            ClassLoader classLoader = getClass().getClassLoader();
            URL url = classLoader.getResource(filename);
            FileSystemResource file = new FileSystemResource(new File(url.getFile()));
            helper.addAttachment(filename, file);

            mailSender.send(message);
            log.info("sendAttachmentsMail done!");
        } catch (MessagingException e) {
            log.error("sendAttachmentsMail exception！", e);
        }
    }

    /**
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        if ( to.indexOf("@test.com") >= 0 ) return ;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmailAddr);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            log.info("sendInlineResourceMail done!");
        } catch (MessagingException e) {
            log.error("sendInlineResourceMail exception！", e);
        }
    }

    /**
     *
     * @param to
     * @param subject
     * @param templateName
     * @param model
     */
    public void sendFreeMarkerTemplateMail(String to, String subject, String templateName, @SuppressWarnings("rawtypes") Map model) {
        if ( to.indexOf("@test.com") >= 0 ) return ;
        Template t;
        try {
            t = configuration.getTemplate(templateName); // freeMarker template
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            this.sendHtmlMail(to, subject, content);
            log.info("sendFreeMarkerTemplateMail done!");
        } catch (IOException | TemplateException e) {
            log.info("sendFreeMarkerTemplateMail exception!");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param to
     * @param subject
     * @param templateName
     * @param model
     */
    /*public void sendThymeleafTemplateMail(String to, String subject, String templateName, @SuppressWarnings("rawtypes") Map model){
        if ( to.indexOf("@test.com") >= 0 ) return ;
        try {
			Context context = new Context();
            context.setVariables(model);
			String emailContent = templateEngine.process(templateName, context);
			this.sendHtmlMail(to, subject, emailContent);
            log.info("sendThymeleafTemplateMail done!");
        } catch (Exception e) {
            log.info("sendThymeleafTemplateMail exception!");
            e.printStackTrace();
        }
    }*/
}
