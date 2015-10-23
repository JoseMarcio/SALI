package br.com.sali.bean.licao;

import br.com.sali.modelo.Licao;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.LicaoRN;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class AnexarLicaoBean implements Serializable {

    private Licao licao;
    private boolean disabledBtnSalvar;
    private String nomeArquivoSelecionado;
    private boolean anexouArquivo;
    private InputStream inputStream;
    private LicaoRN licaoRN;

    public AnexarLicaoBean() {
    }

    @PostConstruct
    public void init() {
        this.licao = new Licao();
        this.disabledBtnSalvar = true;
        this.nomeArquivoSelecionado = "";
        this.anexouArquivo = false;
        this.licaoRN = new LicaoRN();
    }

    public void upload(FileUploadEvent fileUploadEvent) throws IOException {
        this.inputStream = fileUploadEvent.getFile().getInputstream();
        this.nomeArquivoSelecionado = fileUploadEvent.getFile().getFileName();
        setAnexouArquivo(true);
    }

    public void salvarLicao() throws IOException {
        if (ValidacoesUtil.soTemEspaco(this.licao.getTituloLicao())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Título inválido.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (!isAnexouArquivo()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Anexe um arquivo.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            String pastaPadrao = FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/resources/licoes") + File.separator;

            String nomeArquivo = new java.util.Date().getTime() + this.nomeArquivoSelecionado + this.licao.getTituloLicao() + ".pdf";

            String urlAquivo = pastaPadrao + nomeArquivo;

            OutputStream outputStream = new FileOutputStream(urlAquivo);
            byte[] buffer = new byte[1024];

            while (this.inputStream.read(buffer) > 0) {
                outputStream.write(buffer);
            }

            outputStream.close();
            this.inputStream.close();

            this.licao.setArquivo(urlAquivo);
            this.licao.setTurma(getProfessorConectado().getTurmaAtual());
            this.licaoRN.salvarLicao(this.licao);

            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro efetuado com sucesso.", "");
            FacesContext.getCurrentInstance().addMessage(null, m);
            
            limparBean();
        }
    }

    /**
     * Retorna o professor autenticado no momento.
     *
     * @return
     */
    public Professor getProfessorConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        ProfessorRN professorRN = new ProfessorRN();

        Professor professor = professorRN.getProfessorByUsuario(usuario);

        return professor;
    }

    public void limparBean() {
        init();
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

    public boolean isAnexouArquivo() {
        return anexouArquivo;
    }

    public void setAnexouArquivo(boolean anexouArquivo) {
        this.anexouArquivo = anexouArquivo;
    }

}
