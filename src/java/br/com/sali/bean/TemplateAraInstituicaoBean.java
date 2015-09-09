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
}
