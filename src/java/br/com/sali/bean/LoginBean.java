package br.com.sali.bean;

import br.com.sali.modelo.Instituicao;
import br.com.sali.regras.InstituicaoRN;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Managed Bean Login.
 *
 * @author SALI
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

    // Atributos;
    InstituicaoRN instituicaoRN;
    Instituicao instituicao;

    // Construtor.
    @PostConstruct
    public void init() {
        instituicaoRN = new InstituicaoRN();
        if (instituicaoRN.getInstituicoCadastrada() == null || instituicaoRN.getInstituicoCadastrada().getId() == 0) {
            instituicaoRN.criaInstituicao();
            setInstituicao(instituicaoRN.getInstituicoCadastrada());
        } else {
            setInstituicao(instituicaoRN.getInstituicoCadastrada());
        }
    }

    //============================ Gest e Sets =================================
    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    //============================ Métodos =====================================
    /**
     * Direciona para a área institucional.
     *
     * @return
     */
    public String direcionaIniciointituicao() {
        return "inicio-instituicao";
    }
}
