package br.com.sali.bean;

import br.com.sali.modelo.Professor;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean
public class ProfessorExcluirBean {

    public void selecionar(Professor professor) {
        abrirDialogo();
    }

    public String limpar() {

        return "pesquisarProfessor";
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 470);

        RequestContext.getCurrentInstance().openDialog("SelecaoCliente", opcoes, null);
    }

}
