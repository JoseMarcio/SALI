package br.com.sali.bean.licao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class BaixarAlunoLicaoBean implements Serializable{

    private StreamedContent file;
    private String caminhoArquivo;
    private String nomeLicao;
    
    /**
     * Configura o arquivo para baixá-lo.
     * 
     */
    public void prepararLicao(){
        try {
            InputStream stream = new FileInputStream(this.caminhoArquivo);
            this.file = new DefaultStreamedContent(stream,"application/pdf",
                this.nomeLicao + ".pdf");
        } catch (FileNotFoundException ex) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_FATAL, ex.getMessage(), "");
            RequestContext.getCurrentInstance().showMessageInDialog(m);
        }
    }

    public StreamedContent getFile(){
        prepararLicao();
        return this.file;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getNomeLicao() {
        return nomeLicao;
    }

    public void setNomeLicao(String nomeLicao) {
        this.nomeLicao = nomeLicao;
    }

}
