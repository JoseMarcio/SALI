package br.com.sali.bean;

import br.com.sali.modelo.Instituicao;
import br.com.sali.regras.InstituicaoRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Managed Bean Alterar Instituição.
 *
 * @author SALI
 */
@ManagedBean(name = "instituicaoAlterarBean")
@ViewScoped
public class InstituicaoAlterarBean implements Serializable {

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
        setEmailAtualInstituicao(instituicaoCadastrada.getUsuario().getEmail());
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
     * Atualiza os dados da Instituição.
     */
    public void atualizar() {
        instituicaoCadastrada.getUsuario().setEmail(instituicaoCadastrada.getUsuario().getEmail().toLowerCase());

        if (ValidacoesUtil.soTemEspaco(instituicaoCadastrada.getNome())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O nome não pode ser vazio.", ""));

        } else if (ValidacoesUtil.isExistenteEmail(instituicaoCadastrada.getUsuario().getEmail())
                && (!emailAtualInstituicao.equals(instituicaoCadastrada.getUsuario().getEmail()))) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado.", ""));

        } else if (ValidacoesUtil.soTemEspaco(instituicaoCadastrada.getEndereco().getRua())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não informe espaços para a rua.", ""));

        } else if (ValidacoesUtil.soTemEspaco(instituicaoCadastrada.getEndereco().getNumero())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não informe espaços para o número.", ""));

        } else if (ValidacoesUtil.soTemEspaco(instituicaoCadastrada.getEndereco().getBairro())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não informe espaços para o bairro.", ""));

        } else if (ValidacoesUtil.soTemEspaco(instituicaoCadastrada.getEndereco().getCidade())) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não informe espaços para a cidade.", ""));

        } else {
            try {
                instituicaoRN.atualizarInstituicao(instituicaoCadastrada);
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Atualização concluída com sucesso."));
            } catch (NoSuchAlgorithmException ex) {
                RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Exceção!", ex.getMessage()));
            }

        }
    }
}
