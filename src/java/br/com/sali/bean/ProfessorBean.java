
package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class ProfessorBean {

    private Professor professor;
    private String confirmaSenha;
    private String matricula;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getVerificaMatricula() {
        return matricula;
    }

    public void setVerificaMatricula(String verificaMatricula) {
        this.matricula = verificaMatricula;
    }
    
    public void registrar(){
        
    }
    
    
}
