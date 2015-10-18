package testes;

import br.com.sali.dao.QuizDAO;
import br.com.sali.modelo.Questao;
import br.com.sali.modelo.Quiz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
public class Testes {

    private StreamedContent arquivo;

    private String caminhoRelatorio;

    private String nomeRelatorioSaida = "bosta.pdf";

    private String nomeR = "teste";

    private Map<String, Object> map = new HashMap<>();

    public Testes() {

    }

    public StreamedContent getArquivo() throws FileNotFoundException {
        FacesContext context = FacesContext.getCurrentInstance();

        this.caminhoRelatorio = context.getExternalContext().getRealPath("/resources/relatorios");
        String caminhoArquivoJasper = caminhoRelatorio + File.separator + nomeR + ".jasper";
        caminhoRelatorio = caminhoArquivoJasper;

        //InputStream i = getClass().getResourceAsStream(this.caminhoRelatorio);
        InputStream i = new FileInputStream(caminhoRelatorio);

    

        try {
            JasperPrint p = JasperFillManager.fillReport(i, map, getConexao());
            JRExporter e = new JRPdfExporter();
           // HttpServletResponse r = (HttpServletResponse) context.getExternalContext().getResponse();

           // e.setParameter(JRExporterParameter.OUTPUT_STREAM, r.getOutputStream());
            //  e.setParameter(JRExporterParameter.JASPER_PRINT, p);
            //  r.setContentType("application/pdf");
            String caminhoArquivoRelatorio = context.getExternalContext().getRealPath("/resources/relatorios") + File.separator + nomeRelatorioSaida;
            File arquivoGerado = new java.io.File(caminhoArquivoRelatorio);
            e.setParameter(JRExporterParameter.JASPER_PRINT, p);
            e.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
            e.exportReport();
            arquivoGerado.deleteOnExit();

            InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
            arquivo = new DefaultStreamedContent(conteudoRelatorio, "application/pdf", nomeRelatorioSaida + ".pdf");

            

            //context.responseComplete();
            //FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, , "");
            //FacesContext.getCurrentInstance().addMessage(null, m);
        } catch (Exception ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.arquivo;
    }

    private Connection getConexao() throws Exception {
        java.sql.Connection conexao = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/SaliDB");
            conexao = (java.sql.Connection) ds.getConnection();
        } catch (NamingException e) {
            throw new Exception("Não foi possível encontrar o nome da conexão do banco.", e);
        } catch (SQLException e) {
            throw new Exception("Ocorreu um erro de SQL.", e);
        }
        return conexao;
    }
}
