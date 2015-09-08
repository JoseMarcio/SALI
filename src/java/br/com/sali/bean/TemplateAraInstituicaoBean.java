package br.com.sali.bean;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "beanTemplateInstituicao")
public class TemplateAraInstituicaoBean {
    
    /**
     * Direciona página registrar professor.
     * @return 
     */
    public String irRegistrarProfessor(){
        return "registrar-professor";
    }
}
