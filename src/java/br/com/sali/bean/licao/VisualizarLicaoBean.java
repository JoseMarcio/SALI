package br.com.sali.bean.licao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
@SessionScoped
public class VisualizarLicaoBean implements Serializable{
    
    private String urlDoArquivo;
    private StreamedContent file;
    private String nomeLicao;
    
    public VisualizarLicaoBean() {
    }
    
    
    /**
     * Configura o arquivo para exibi-lo.
     * 
     */
    public void prepararLicao(){
        try {
            InputStream stream = new FileInputStream(this.urlDoArquivo);
            this.file = new DefaultStreamedContent(stream,"application/pdf");
        } catch (FileNotFoundException ex) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_FATAL, ex.getMessage(), "");
            RequestContext.getCurrentInstance().showMessageInDialog(m);
        }
    }
    
    
    /**
     * Direciona para a página de visualização de lições.
     * E também, atribui o caminho do arquivo ao caminho atual.
     * 
     * @param caminho
     * @param nome
     * @return 
     */
    public String visualizarLicao(String caminho, String nome) {
        this.urlDoArquivo = caminho;
        this.nomeLicao = nome;
        return "/professor/visualizar-licao?faces-redirect=true";
    }
        
    public String getUrlDoArquivo() {
        return urlDoArquivo;
    }
    
    public void setUrlDoArquivo(String urlDoArquivo) {
        this.urlDoArquivo = urlDoArquivo;
    }

    public StreamedContent getFile() {
        prepararLicao();
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getNomeLicao() {
        return nomeLicao;
    }

    public void setNomeLicao(String nomeLicao) {
        this.nomeLicao = nomeLicao;
    }
    
    
    
}
