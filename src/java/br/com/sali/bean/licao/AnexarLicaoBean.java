package br.com.sali.bean.licao;

import br.com.sali.modelo.Licao;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class AnexarLicaoBean implements Serializable{

    private Licao licao;
    private boolean disabledBtnSalvar;
    private String nomeArquivoSelecionado;

    public AnexarLicaoBean() {
    }

    @PostConstruct
    public void init() {
        this.licao = new Licao();
        this.disabledBtnSalvar = true;
        this.nomeArquivoSelecionado = "";
    }

    public void concluirAnexoDeLicao() {
        System.out.println("dsdkjgkljlk");
    }

    public void salvarLicao() {
        if (ValidacoesUtil.soTemEspaco(this.licao.getTituloLicao())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Título inválido.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

        }
    }

    //==========================================================================

    public Licao getLicao() {
        return licao;
    }

    public void setLicao(Licao licao) {
        this.licao = licao;
    }
    

    public boolean isDisabledBtnSalvar() {
        return disabledBtnSalvar;
    }

    public void setDisabledBtnSalvar(boolean disabledBtnSalvar) {
        this.disabledBtnSalvar = disabledBtnSalvar;
    }

    public String getNomeArquivoSelecionado() {
        return nomeArquivoSelecionado;
    }

    public void setNomeArquivoSelecionado(String nomeArquivoSelecionado) {
        this.nomeArquivoSelecionado = nomeArquivoSelecionado;
    }

}
