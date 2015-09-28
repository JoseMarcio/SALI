package br.com.sali.regras;

import br.com.sali.dao.UsuarioDao;
import br.com.sali.modelo.Usuario;
import br.com.sali.util.CriptografiaUtil;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author SALI
 */
public class UsuarioRN {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    public Usuario getUsuarioByEmail(String email) {
        return (Usuario) usuarioDao.getObjectByEmail(Usuario.class, email);
    }

    public void alterarSenha(Usuario usuario) throws NoSuchAlgorithmException {
        
        usuario.setSenha(CriptografiaUtil.criptografaSenha(usuario.getSenha()));
        usuarioDao.atualizar(usuario);
    }
}
