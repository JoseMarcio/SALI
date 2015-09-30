package br.com.sali.bean;

import br.com.sali.modelo.Instituicao;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.regras.UsuarioRN;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Managed Bean Links Área Instituição.
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateInstituicao")
public class TemplateAraInstituicaoBean implements Serializable {

    /**
     * Direciona página Inicial.
     *
     * @return
     */
    public String irInicioInstituicao() {
        return "/instituicao/inicio-instituicao?faces-redirect=true";
    }

    /**
     * Direciona página alterar professor.
     *
     * @return
     */
    public String irAlterarProfessor() {
        return "/instituicao/alterar-professor?faces-redirect=true";
    }

    /**
     * Direciona página excluir professor.
     *
     * @return
     */
    public String irExcluirProfessor() {
        return "/instituicao/excluir-professor?faces-redirect=true";
    }

    /**
     * Direciona página excluir professor.
     *
     * @return
     */
    public String irRegistrarProfessor() {
        return "/instituicao/registrar-professor?faces-redirect=true";
    }

    /**
     * Direciona página registrar turma.
     *
     * @return
     */
    public String irRegistrarTurma() {
        return "/instituicao/registrar-turma?faces-redirect=true";
    }

    /**
     * Direciona página alterar turma.
     *
     * @return
     */
    public String irAlterarTurma() {
        return "/instituicao/alterar-turma?faces-redirect=true";
    }

    /**
     * Direciona página excluir turma.
     *
     * @return
     */
    public String irExcluirTurma() {
        return "/instituicao/excluir-turma?faces-redirect=true";
    }

    /**
     * Direciona página registrar aluno.
     *
     * @return
     */
    public String irRegistrarAluno() {
        return "/instituicao/registrar-aluno?faces-redirect=true";
    }

    /**
     * Direciona página alterar aluno.
     *
     * @return
     */
    public String irAlterarAluno() {
        return "/instituicao/alterar-aluno?faces-redirect=true";
    }

    /**
     * Direciona página excluir aluno.
     *
     * @return
     */
    public String irExcluirAluno() {
        return "/instituicao/excluir-aluno?faces-redirect=true";
    }

    /**
     * Direciona página alterar instituição.
     *
     * @return
     */
    public String irAlterarInstituicao() {
        return "/instituicao/alterar-instituicao?faces-redirect=true";
    }

    /**
     * Direciona página login.
     *
     * @return
     */
    public String irLogin() {
        return "/publico/login?faces-redirect=true";
    }

    /**
     * Retorna a instituição autenticada no momento.
     *
     * @return
     */
    public Instituicao getInstituicaoConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        InstituicaoRN instituicaoRN = new InstituicaoRN();

        Instituicao instituicao = instituicaoRN.getInstituicaoByUsuario(usuario);

        return instituicao;
    }

}
