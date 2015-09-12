package br.com.sali.bean;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateInstituicao")
public class TemplateAraInstituicaoBean {
    
    /**
     * Direciona página Inicial.
     * @return 
     */
    public String irInicioInstituicao(){
        return "inicio-instituicao";
    }
    
    
    /**
     * Direciona página alterar professor.
     * @return 
     */
    public String irAlterarProfessor(){
        return "alterar-professor";
    }
    
    
    
    /**
     * Direciona página excluir professor.
     * @return 
     */
    public String irExcluirProfessor(){
        return "excluir-professor";
    }
    
    /**
     * Direciona página excluir professor.
     * @return 
     */
    public String irRegistrarProfessor(){
        return "registrar-professor";
    }
    
     /**
     * Direciona página registrar turma.
     * @return 
     */
    public String irRegistrarTurma(){
        return "registrar-turma";
    }
    
    /**
     * Direciona página alterar turma.
     * @return 
     */
    public String irAlterarTurma(){
        return "alterar-turma";
    }
    
    /**
     * Direciona página excluir turma.
     * @return 
     */
    public String irExcluirTurma(){
        return "excluir-turma";
    }
    
    
     /**
     * Direciona página registrar aluno.
     * @return 
     */
    public String irRegistrarAluno(){
        return "registrar-aluno";
    }
    
    /**
     * Direciona página alterar aluno.
     * @return 
     */
    public String irAlterarAluno(){
        return "alterar-aluno";
    }
    
    /**
     * Direciona página excluir aluno.
     * @return 
     */
    public String irExcluirAluno(){
        return "excluir-aluno";
    }
}
