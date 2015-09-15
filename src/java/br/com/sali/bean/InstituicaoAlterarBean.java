package br.com.sali.bean;

import br.com.sali.modelo.Endereco;
import br.com.sali.modelo.Instituicao;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.util.ValidacoesUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean(name = "instituicaoAlterarBean")
@ViewScoped
public class InstituicaoAlterarBean {

    private Instituicao instituicaoCadastrada;
    private Endereco endereco;
    private InstituicaoRN instituicaoRN;
    private String emailInstituicao;

    @PostConstruct
    public void init() {
        instituicaoRN = new InstituicaoRN();
        instituicaoCadastrada = instituicaoRN.getInstituicoCadastrada();
        emailInstituicao = instituicaoCadastrada.getEmail();
    }

    public Instituicao getInstituicaoCadastrada() {
        return instituicaoCadastrada;
    }

    public void setInstituicaoCadastrada(Instituicao instituicaoCadastrada) {
        this.instituicaoCadastrada = instituicaoCadastrada;
    }

    public void atualizar() {
         if (ValidacoesUtil.isExistenteEmail(instituicaoCadastrada.getEmail()) && (!emailInstituicao.equals(instituicaoCadastrada.getEmail()))) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail já cadastrado.", ""));
        } else {
            instituicaoRN.atualizarInstituicao(instituicaoCadastrada);
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Dados Alterados com sucesso.", ""));
        }
    }
}
