package br.com.sali.bean;

import br.com.sali.dao.UsuarioDao;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.CriptografiaUtil;
import br.com.sali.util.EmailUtil;
import br.com.sali.util.GeraSenha;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
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

    @PostConstruct
    public void init() {
        emailInformado = "";
    }

    public String getEmailInformado() {
        return emailInformado;
    }

    public void setEmailInformado(String emailInformado) {
        this.emailInformado = emailInformado;
    }

    public void carregarInstituicao() {
        InstituicaoRN instituicaoRN = new InstituicaoRN();
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();

        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        if (request.isUserInRole("ROLE_INSTITUICAO")) {
            nav.performNavigation("/instituicao/inicio-instituicao?faces-redirect=true");
        }
        if (request.isUserInRole("ROLE_PROFESSOR")) {
            nav.performNavigation("/professor/inicio-professor?faces-redirect=true");
        }
        if (request.isUserInRole("ROLE_ALUNO")) {
            nav.performNavigation("/aluno/inicio-aluno?faces-redirect=true");
        }
    }

    public void recuperarEmail() {
        if (emailInformado.equalsIgnoreCase("")) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe seu e-mail", ""));
        } else {

            UsuarioDao usuarioDao = new UsuarioDao();
            UsuarioRN usuarioRN = new UsuarioRN();
            Usuario usuario = usuarioRN.getUsuarioByEmail(emailInformado);

            if (usuario == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "E-mail não encontrado", ""));

            } else {
                RequestContext rc = RequestContext.getCurrentInstance();
                
                String novaSenha = GeraSenha.novaSenha();

                try {
                    EmailUtil.enviarEmailRecuperacaoSenha(emailInformado, novaSenha);
                } catch (EmailException ex) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Exceção", ex.getMessage());
                    rc.showMessageInDialog(message);
                }
                try {
                    usuario.setSenha(CriptografiaUtil.criptografaSenha(novaSenha));
                } catch (NoSuchAlgorithmException ex) {
                    RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Exceção!", ex.getMessage()));
                }

                usuarioDao.atualizar(usuario);
                FacesContext fc = FacesContext.getCurrentInstance();
                FacesMessage mensagemSucesso = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados enviados com sucesso!", "");
                fc.addMessage(null, mensagemSucesso);
                limpar();

            }
        }

    }

    public void limpar() {
        emailInformado = "";
    }

}
