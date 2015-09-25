package br.com.sali.bean;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "redirecionaBean")
public class RedirecionaBean {

    /**
     * Identifica o usuário da sessão e direciona para a sua página.
     */
    public void indentificaUsuario() {
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
}
