package br.com.sali.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 * Managed Bean Links Área Instituição.
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateInstituicao")
public class TemplateAraInstituicaoBean implements Serializable{

    /**
     * Direciona página Inicial.
     *
     * @return
     */
    public String irInicioInstituicao() {
        return "inicio-instituicao?faces-redirect=true";
    }

    /**
     * Direciona página alterar professor.
     *
     * @return
     */
    public String irAlterarProfessor() {
        return "alterar-professor?faces-redirect=true";
    }

    /**
     * Direciona página excluir professor.
     *
     * @return
     */
    public String irExcluirProfessor() {
        return "excluir-professor?faces-redirect=true";
    }

    /**
     * Direciona página excluir professor.
     *
     * @return
     */
    public String irRegistrarProfessor() {
        return "registrar-professor?faces-redirect=true";
    }

    /**
     * Direciona página registrar turma.
     *
     * @return
     */
    public String irRegistrarTurma() {
        return "registrar-turma?faces-redirect=true";
    }

    /**
     * Direciona página alterar turma.
     *
     * @return
     */
    public String irAlterarTurma() {
        return "alterar-turma?faces-redirect=true";
    }

    /**
     * Direciona página excluir turma.
     *
     * @return
     */
    public String irExcluirTurma() {
        return "excluir-turma?faces-redirect=true";
    }

    /**
     * Direciona página registrar aluno.
     *
     * @return
     */
    public String irRegistrarAluno() {
        return "registrar-aluno?faces-redirect=true";
    }

    /**
     * Direciona página alterar aluno.
     *
     * @return
     */
    public String irAlterarAluno() {
        return "alterar-aluno?faces-redirect=true";
    }

    /**
     * Direciona página excluir aluno.
     *
     * @return
     */
    public String irExcluirAluno() {
        return "excluir-aluno?faces-redirect=true";
    }

     /**
     * Direciona página alterar instituição.
     *
     * @return
     */
    public String irAlterarInstituicao() {
        return "alterar-instituicao?faces-redirect=true";
    }
    
    /**
     * Direciona página login.
     *
     * @return
     */
    public String irLogin() {
        return "login?faces-redirect=true";
    }
}
