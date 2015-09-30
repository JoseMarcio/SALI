package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateProfessor")
public class TemplateAreaProfessorBean {

    /**
     * Direciona página Inicial.
     *
     * @return
     */
    public String irInicioProfessor() {
        return "/professor/inicio-professor?faces-redirect=true";
    }

    /**
     * Direciona alterar senha.
     *
     * @return
     */
    public String irAlterarSenha() {
        return "/professor/alterar-senha?faces-redirect=true";
    }

    /**
     * Retorna o professor autenticada no momento.
     *
     * @return
     */
    public Professor getProfessorConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        ProfessorRN professorRN = new ProfessorRN();

        Professor professor = professorRN.getProfessorByUsuario(usuario);

        return professor;
    }

}
