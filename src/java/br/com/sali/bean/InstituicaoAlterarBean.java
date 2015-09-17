package br.com.sali.bean;

import br.com.sali.modelo.Instituicao;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.util.ValidacoesUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Alterar Instituição.
 *
 * @author SALI
 */
@ManagedBean(name = "instituicaoAlterarBean")
@ViewScoped
public class InstituicaoAlterarBean {

    // Atributos.
    private Instituicao instituicaoCadastrada;
    private InstituicaoRN instituicaoRN;
    private String emailAtualInstituicao;
    private String confirmaSenha;

    //Construtor.
    @PostConstruct
    public void init() {
        instituicaoRN = new InstituicaoRN();
        instituicaoCadastrada = instituicaoRN.getInstituicoCadastrada();
        emailAtualInstituicao = instituicaoCadastrada.getEmail();
        confirmaSenha = "";
    }

    //======================= Gets e Sets ======================================
    public Instituicao getInstituicaoCadastrada() {
        return instituicaoCadastrada;
    }

    public void setInstituicaoCadastrada(Instituicao instituicaoCadastrada) {
        this.instituicaoCadastrada = instituicaoCadastrada;
    }

    public String getEmailAtualInstituicao() {
        return emailAtualInstituicao;
    }

    public void setEmailAtualInstituicao(String emailAtualInstituicao) {
        this.emailAtualInstituicao = emailAtualInstituicao;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    //======================= Métodos===========================================
    /**
     * Verifica se a senha e a confirmação de senha são iguais. Se forem iguais
     * é retornado "true", senão é retornado "false".
     *
     * @return
     */
    public boolean isSenhasIguais() {
            return instituicaoCadastrada.getSenha().equals(confirmaSenha);
    }
    
    
    
    /**
     * Atualiza os dados da Instituição.
     */
    public void atualizar() {
        if (ValidacoesUtil.isExistenteEmail(instituicaoCadastrada.getEmail())
                && (!emailAtualInstituicao.equals(instituicaoCadastrada.getEmail()))) {

            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "E-mail já cadastrado."));
        } else if (!isSenhasIguais()) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "As senhas não conferem."));
        } else {
            instituicaoRN.atualizarInstituicao(instituicaoCadastrada);
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Atualização conluída com sucesso."));
        }
    }
}
