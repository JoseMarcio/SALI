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
}
