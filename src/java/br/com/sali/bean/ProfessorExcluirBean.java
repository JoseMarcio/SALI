package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "excluirProfessorBean")
public class ProfessorExcluirBean {

    private Professor professor = new Professor();

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void professorSelecionado(SelectEvent event) {
        Professor pf = (Professor) event.getObject();
        setProfessor(pf);
    }

    

}
