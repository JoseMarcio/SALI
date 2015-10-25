package br.com.sali.bean;

import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.UsuarioRN;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateAluno")
public class TemplateAreaAlunoBean {

    /**
     * Direciona página Inicial.
     *
     * @return
     */
    public String irInicioAluno() {
        return "/aluno/inicio-aluno?faces-redirect=true";
    }

    /**
     * Direciona alterar senha.
     *
     * @return
     */
    public String irAlterarSenha() {
        return "/aluno/alterar-senha?faces-redirect=true";
    }

    /**
     * Direciona página quiz.
     *
     * @return
     */
    public String irQuizes() {
        return "/aluno/quiz?faces-redirect=true";
    }

    /**
     * Retorna o aluno autenticado no momento.
     *
     * @return
     */
    public Aluno getAlunoConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        AlunoRN alunoRN = new AlunoRN();

        Aluno aluno = alunoRN.getAlunoByUsuario(usuario);

        return aluno;
    }

    /**
     * Direciona a área de realização de lições.
     *
     * @return
     */
    public String irLicoes() {
        return "/aluno/licoes?faces-redirect=true";
    }
}
