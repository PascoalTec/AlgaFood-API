package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import freemarker.template.Configuration;
import freemarker.template.Template;


public class ProcessadorEmailTemplate {

    @Autowired
    private Configuration freeMarkerConfig;

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freeMarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());


        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }
    
}
