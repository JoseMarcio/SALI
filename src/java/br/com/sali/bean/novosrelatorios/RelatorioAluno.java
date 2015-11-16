package br.com.sali.bean.novosrelatorios;

import br.com.sali.util.SessionUtil;
import br.com.sali.util.relatorio.ExecutorRelatorio;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SALI
 */
@ManagedBean
@RequestScoped
public class RelatorioAluno implements Serializable {

    private FacesContext facesContext;
    private HttpServletResponse response;

    public RelatorioAluno() {
        this.facesContext = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

    /**
     * Gera o relatório da turma.
     */
    public void gerarRelatorio() {
        Long idAluno = (Long) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("idAluno");

        // Criando a data de emissão.
        GregorianCalendar cal = new GregorianCalendar();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat formate = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' ás 'HH':'mm' hrs'", local);
        String dataEmissao = formate.format(cal.getTime());

        // Adicionando os parametros do relatório.
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_aluno", idAluno);
        parametros.put("data", dataEmissao);

        // Inserindo o caminho do relatório.
        String caminhoRelatorio = this.facesContext.getExternalContext().getRealPath("/resources/relatorios");
        String caminhoArquivoJasper = caminhoRelatorio + File.separator + "relatorioAluno" + ".jasper";

        SessionUtil sessionUtil = new SessionUtil();
        Session session = sessionUtil.getSessao();
        ExecutorRelatorio executorRelatorio = new ExecutorRelatorio(caminhoArquivoJasper, this.response, parametros);
        session.doWork(executorRelatorio);

        if (executorRelatorio.isReatorioGerado()) {
            facesContext.responseComplete();
        } else {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informação", "O relatório não retornou dados.");
            RequestContext.getCurrentInstance().showMessageInDialog(m);
        }
    }
}
