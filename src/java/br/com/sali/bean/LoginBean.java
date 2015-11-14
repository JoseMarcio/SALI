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

    /**
     * Carrega a instituição. Se ainda não tiver nenhuma cadastrada A
     * InstituicaoRN trata de criar uma nova.
     *
     * Se um usuário solicitar a página de login e o mesmo tiver autenticado
     * será direcionado para a sua página inicial.
     */
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

    /**
     * Recupera o acesso do usuário ao sistema. Criando uma nova senha para o
     * mesmo e enviando para o seu e-mail.
     *
     */
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

                try {
                    String novaSenha = GeraSenha.novaSenha();
                    usuario.setSenha(CriptografiaUtil.criptografaSenha(novaSenha));
                    EmailUtil.enviarEmailRecuperacaoSenha(emailInformado, novaSenha);
                    usuarioDao.atualizar(usuario);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    FacesMessage mensagemSucesso = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados enviados com sucesso!", "");
                    fc.addMessage(null, mensagemSucesso);
                    limpar();
                } catch (EmailException ex) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", "Contatar o administrador do sistema.");
                    rc.showMessageInDialog(message);
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", "Contatar o administrador do sistema.");
                    rc.showMessageInDialog(message);
                    ex.printStackTrace();
                }

            }
        }

    }

    /**
     * Limpa o e-mail informado.
     */
    public void limpar() {
        emailInformado = "";
    }

}
