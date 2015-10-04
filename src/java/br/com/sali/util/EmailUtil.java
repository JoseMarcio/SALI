package br.com.sali.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author SALI
 */
public class EmailUtil {

    /**
     * Envia um e-mail para o usuário desejado, com uma nova senha de acesso
     * para que o usuário possa acessar novamente a sua conta mesmo depois de
     * perder a senha.
     * @param emailUsuario
     * @param novaSenha
     * @throws org.apache.commons.mail.EmailException
     */
    public static void enviarEmailRecuperacaoSenha(String emailUsuario, String novaSenha) throws EmailException {

        Email email = new SimpleEmail();
        email.setHostName(ConfMail.getHOST_NAME());
        email.setSmtpPort(ConfMail.getSMTP_PORT());
        email.setAuthentication(ConfMail.getMY_MAIL(), ConfMail.getMY_PASSWORD());
        email.setSSL(ConfMail.isSSL_CONNECT());
        
        email.setFrom(ConfMail.getFROM(), ConfMail.getNAME_FROM());
        email.setSubject("SALI - Recuperação de Senha de Acesso");
        email.setMsg("Você solicitou a recuperação de senha de acesso ao SALI - Sistema de Auxílio no Aprendizado "
                     + "da Língua Ínglesa.\nSeus dados de acesso agora são:\n\nEmail: "+emailUsuario+"\nSenha: "+novaSenha);
        email.addTo(emailUsuario);
        email.send();

    }
}


