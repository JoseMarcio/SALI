package br.com.sali.bean;

import br.com.sali.dao.UsuarioDao;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.EmailUtil;
import br.com.sali.util.GeraSenha;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    private String emailInformado;

    public String getEmailInformado() {
        return emailInformado;
    }

    public void setEmailInformado(String emailInformado) {
        this.emailInformado = emailInformado;
    }

    public void carregarInstituicao() {
        InstituicaoRN instituicaoRN = new InstituicaoRN();
    }

    public void recuperarEmail() {
        UsuarioDao usuarioDao = new UsuarioDao();
        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailInformado);
        if (usuario == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("E-mail não encontrado"));
        } else {
            String novaSenha = GeraSenha.novaSenha();
            String texto = "SALI\nE-mail: " + usuario.getEmail() + "\nNova Senha: " + novaSenha;
            EmailUtil.enviaEmail(emailInformado, texto);
            try {
                usuario.setSenha(CriptografiaUtil.criptografaSenha(novaSenha));
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exeção!", ex.getMessage()));
            }

            usuarioDao.atualizar(usuario);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados enviados com sucesso!"));
            limpar();
        }
    }

    public void limpar() {
        emailInformado = "";
    }

}
