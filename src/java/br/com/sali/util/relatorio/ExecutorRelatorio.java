package br.com.sali.util.relatorio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.hibernate.jdbc.Work;

/**
 *
 * @author SALI
 */
public class ExecutorRelatorio implements Work {

    private String caminhoRelatorioJasper;
    private HttpServletResponse response;
    private Map<String, Object> paramametros;
    private boolean reatorioGerado;

    public ExecutorRelatorio(String caminhoRelatorioJasper, HttpServletResponse response, Map<String, Object> parametros) {
        this.caminhoRelatorioJasper = caminhoRelatorioJasper;
        this.response = response;
        this.paramametros = parametros;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        InputStream relatorioStream = null;
        try {
            relatorioStream = new FileInputStream(this.caminhoRelatorioJasper);
        } catch (FileNotFoundException e) {
            throw new SQLException("Erro ao buscar o relatório Jasper.", e);
        }

        try {
            JasperPrint print = JasperFillManager.fillReport(relatorioStream, paramametros, connection);
            this.reatorioGerado = print.getPages().size() > 0;

            if (isReatorioGerado()) {
                JRExporter executor = new JRPdfExporter();
                executor.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                executor.setParameter(JRExporterParameter.JASPER_PRINT, print);

                this.response.setContentType("application/pdf");

                // Força o navegador a fazer o download
                response.setHeader("Content-Disposition", "attachement; filename=Relatorio.pdf");
                executor.exportReport();
            }
        } catch (JRException | IOException ex) {
            throw new SQLException("Erro ao executar o relatório ", ex);
        }
    }

    /**
     * Verifica se o relatório foi gerado. Ou seja, se contém mais de uma
     * página.
     *
     * @return
     */
    public boolean isReatorioGerado() {
        return reatorioGerado;
    }

}
