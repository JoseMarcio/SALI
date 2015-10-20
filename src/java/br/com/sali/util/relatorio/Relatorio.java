package br.com.sali.util.relatorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
public class Relatorio {

    private String nomeRelatorioJasper;
    private String nomeSaidaRelatorio;
    private Map<String, Object> parametros;

    public Relatorio(String nomeRelatorioJasper, String nomeSaidaRelatorio, Map<String, Object> parametros) {
        this.nomeRelatorioJasper = nomeRelatorioJasper;
        this.nomeSaidaRelatorio = nomeSaidaRelatorio;
        this.parametros = parametros;
    }

    /**
     * Gera o relatório de acordo com os parâmetros informados.
     *
     * @return
     * @throws FileNotFoundException
     * @throws Exception
     */
    public StreamedContent gerarRelatorio() throws FileNotFoundException, Exception {

        FacesContext context = FacesContext.getCurrentInstance();
        String caminhoRelatorio = context.getExternalContext().getRealPath("/resources/relatorios");
        String caminhoArquivoJasper = caminhoRelatorio + File.separator + this.nomeRelatorioJasper + ".jasper";

        InputStream relatorioStream = null;
        StreamedContent arquivo = null;

        try {
            relatorioStream = new FileInputStream(caminhoArquivoJasper);
            JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, this.getConexao());
            JRExporter exportador = new JRPdfExporter();

            String caminhoArquivoPdfRelatorio = context.getExternalContext().getRealPath("/resources/relatorios") + File.separator + this.nomeSaidaRelatorio;
            File relatorioGerado = new File(caminhoArquivoPdfRelatorio);
            exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exportador.setParameter(JRExporterParameter.OUTPUT_FILE, relatorioGerado);
            exportador.exportReport();
            relatorioGerado.deleteOnExit();

            InputStream relatorio = new FileInputStream(relatorioGerado);
            arquivo = new DefaultStreamedContent(relatorio, "application/pdf", this.nomeSaidaRelatorio + ".pdf");

        } catch (Exception e) {
            throw new Exception("Erro ao gerar o relatorio." + caminhoArquivoJasper, e);
        }
        return arquivo;
    }

    /**
     * Pega a conexão com o banco de dados.
     *
     * @return
     * @throws Exception
     */
    private Connection getConexao() throws Exception {
        Connection minhaConexao = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            DataSource ds = (DataSource) envContext.lookup("jdbc/SaliDB");
            minhaConexao = (Connection) ds.getConnection();
        } catch (NamingException e) {
            throw new Exception("Erro ao buscar nome da conexão com banco.", e);
        } catch (SQLException e) {
            throw new Exception("Erro SQL.", e);
        }
        return minhaConexao;
    }

    //==========================================================================
    public String getNomeRelatorioJasper() {
        return nomeRelatorioJasper;
    }

    public void setNomeRelatorioJasper(String nomeRelatorioJasper) {
        this.nomeRelatorioJasper = nomeRelatorioJasper;
    }

    public String getNomeSaidaRelatorio() {
        return nomeSaidaRelatorio;
    }

    public void setNomeSaidaRelatorio(String nomeSaidaRelatorio) {
        this.nomeSaidaRelatorio = nomeSaidaRelatorio;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

}
