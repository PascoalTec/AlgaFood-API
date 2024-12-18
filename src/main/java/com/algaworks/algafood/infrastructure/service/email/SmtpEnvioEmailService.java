package com.algaworks.algafood.infrastructure.service.email;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new EmailException("Não foi possivel enviar e-mail", e);
        }
    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freeMarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());


        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }

    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        String corpo = processarTemplate(mensagem);
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getRemetente());
        helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
        helper.setSubject(mensagem.getAssunto());
        helper.setText(corpo, true);
        
        return mimeMessage;
    }
}