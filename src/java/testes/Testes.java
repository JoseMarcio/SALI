package testes;

import br.com.sali.util.EmailUtil;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author SALI
 */
public class Testes {
    
    public static void main(String[] args) {
        
        try {
            EmailUtil.enviarEmailRecuperacaoSenha("josemacyo2013@gmail.com", "SENHA TESTE");
            System.out.println("Email Enviado com sucesso!");
        } catch (EmailException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
