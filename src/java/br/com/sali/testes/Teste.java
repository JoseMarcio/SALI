package br.com.sali.testes;

import br.com.sali.modelo.Usuario;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.HibernateUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author SALI
 */
@ManagedBean
public class Teste {

    public void salvarUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        Usuario u = new Usuario();

        u.setEmail("jose");
        u.setSenha(CriptografiaUtil.criptografaSenha("jose"));
        u.setAtivo(true);
        u.setPermissao("ROLE_INSTITUICAO");
        
        s.save(u);
        t.commit();
        s.close();
       
    }
}
