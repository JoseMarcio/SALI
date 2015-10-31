package br.com.sali.bean.licao;

import br.com.sali.modelo.Licao;
import br.com.sali.regras.LicaoRN;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class VisualizarLicaoBean implements Serializable {

    private Long idLicao;
    private LicaoRN licaoRN;

    public VisualizarLicaoBean() {
        this.licaoRN = new LicaoRN();
    }
    //==========================================================================

    /**
     * Direciona para a página que carrega o arquivo pdf pertencente a lição
     * selecionada.
     *
     * @return
     */
    public String urlVisualizarArquivoLicao(Long idLicao) {
        return "http://localhost:8080/SALI/professor/visualizar-licao.jsf?id=" + idLicao;
    }

    /**
     * Exibe no browser o arquivo pdf da lição selecionada.
     */
    public void exibirLicao() {
        try {
            Licao licaoSelecionada = licaoRN.pegarLicaoPorId(this.idLicao);
            File arquivoDaLicao = new File(licaoSelecionada.getArquivo());
            byte[] bytesDoArquivo = getBytes(arquivoDaLicao);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
            response.getOutputStream().write(bytesDoArquivo);
            response.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception ex) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro.", ex.getMessage());
            RequestContext.getCurrentInstance().showMessageInDialog(m);
            ex.printStackTrace();
        }
    }

    /**
     * Retorna os byte de um arquivo.
     *
     * @param arquivo
     * @return
     * @throws IOException
     */
    public byte[] getBytes(File arquivo) throws IOException {
        int tamanho = (int) arquivo.length();
        byte[] bytes = new byte[tamanho];
        FileInputStream stream = new FileInputStream(arquivo);
        stream.read(bytes, 0, tamanho);
        return bytes;
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

    //==========================================================================
    public Long getIdLicao() {
        return idLicao;
    }

    public void setIdLicao(Long idLicao) {
        this.idLicao = idLicao;
    }

    public LicaoRN getLicaoRN() {
        return licaoRN;
    }

    public void setLicaoRN(LicaoRN licaoRN) {
        this.licaoRN = licaoRN;
    }
}
