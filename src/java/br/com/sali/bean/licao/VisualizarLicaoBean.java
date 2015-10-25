package br.com.sali.bean.licao;

import br.com.sali.modelo.Licao;
import br.com.sali.regras.LicaoRN;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DeflaterInputStream;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class VisualizarLicaoBean implements Serializable {

    private String urlDoArquivo;
    private StreamedContent file;
    private String nomeLicao;
    private Long licao;
    private LicaoRN licaoRN;
    private Licao myLicao;

    
    public VisualizarLicaoBean(){
        this.licaoRN = new LicaoRN();
    }
    

    /**
     * Configura o arquivo para exibi-lo.
     *
     */
    public void prepararLicao() {

        myLicao = licaoRN.pegarLicaoPorId(licao);

        InputStream relatorio;
        try {
            relatorio = new FileInputStream(myLicao.getArquivo());
            this.file = new DefaultStreamedContent(relatorio, "application/pdf");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(VisualizarLicaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public byte[] getBytes(File file) throws IOException {
        int len = (int) file.length();
        byte[] sendBuf = new byte[len];
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(file);
            inFile.read(sendBuf, 0, len);

        } catch (FileNotFoundException fnfex) {

        } catch (IOException ioex) {

        }
        return sendBuf;
    }
    
    
    public void t(){
        String c = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/professor");
        c = c+File.separator+"visualizar-licao.jsf";
        
        
    }
    
    public void valor(Long l){
        this.licao = l;
    }

    public String url(Long l){
        return "http://localhost:8080/SALI/professor/visualizar-licao.jsf?id="+l;
    }
    
    public void ver() {
        try {
            
            
          
            Licao l = licaoRN.pegarLicaoPorId(this.licao);
            
            File f = new File(l.getArquivo());
            
            byte[] b = getBytes(f);

            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            //Código abaixo gerar o relatório e disponibiliza diretamente na página   
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar   
            //res.setHeader("Content-disposition", "attachment;filename=arquivo.pdf");  
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("saiu do visualizar relatorio");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Direciona para a página de visualização de lições. E também, atribui o
     * caminho do arquivo ao caminho atual.
     *
     * @return
     */
    public String visualizarLicao() {
        return "/professor/visualizar-licao.jsf";
    }

    public String getUrlDoArquivo() {
        return urlDoArquivo;
    }

    public void setUrlDoArquivo(String urlDoArquivo) {
        this.urlDoArquivo = urlDoArquivo;
    }

    public StreamedContent getFile() {
        if (FacesContext.getCurrentInstance().getRenderResponse()) {
            return new DefaultStreamedContent();
        } else {
            return file;
        }
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

    public Long getLicao() {
        return licao;
    }

    public void setLicao(Long licao) {
        this.licao = licao;
    }

    public LicaoRN getLicaoRN() {
        return licaoRN;
    }

    public void setLicaoRN(LicaoRN licaoRN) {
        this.licaoRN = licaoRN;
    }

    public Licao getMyLicao() {
        return myLicao;
    }

    public void setMyLicao(Licao myLicao) {
        this.myLicao = myLicao;
    }

}
