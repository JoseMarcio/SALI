package br.com.sali.bean.relatorios;

import br.com.sali.modelo.Turma;
import br.com.sali.regras.TurmaRN;
import br.com.sali.util.SessionUtil;
import br.com.sali.util.relatorio.ExecutorRelatorio;
import br.com.sali.util.relatorio.Relatorio;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class RelatorioTurmaBean {

    private StreamedContent relatorio;
    private Turma turmaSelecionada;
    private TurmaRN turmaRN;
    private boolean disabilitaBtnEmitir;
    
    
    private FacesContext facesContext;
    private HttpServletResponse response;

    @PostConstruct
    public void init() {
        this.turmaSelecionada = new Turma();
        this.turmaRN = new TurmaRN();
        this.disabilitaBtnEmitir = true;
        this.facesContext = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

    /**
     * É o que deve acontecer no momento em que for selecionado uma turma por
     * meio do diálodo de pesquisa de turmas.
     *
     * @param event
     */
    public void eventoSelecaoTurma(SelectEvent event) {
        Turma turma = (Turma) event.getObject();
        setTurmaSelecionada(turma);
        setDisabilitaBtnEmitir(false);

    }

    //==========================================================================
    /**
     * Pega o relatorio gerado. E retorna ele para o cliente.
     *
     * @return
     */
    public StreamedContent getRelatorio() {

        if (this.turmaRN.isPossivelGerarRelatorioTurma(this.turmaSelecionada)) {
//            setDisabilitaBtnEmitir(true);
            String nomeRelatorioJasper = "relatorioTurma";
            String nomeDoArquivoDeSaida = "SALI - Relatorio Turma";
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id_turma", this.turmaSelecionada.getId());
            
            Relatorio relatorioJasper = new Relatorio(nomeRelatorioJasper, nomeDoArquivoDeSaida, parametros);

            try {
                this.relatorio = relatorioJasper.gerarRelatorio();
                return this.relatorio;
                
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), "");
                RequestContext.getCurrentInstance().showMessageInDialog(msg);
                return null;
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum Quiz realizado nessa turma.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }
    


    /**
     * Gera o relatório da turma.
     */
    public void gerarNovoRelatorio() {
        Long idTurma = (Long) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("idTurma");

        // Criando a data de emissão.
        GregorianCalendar cal = new GregorianCalendar();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat formate = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' ás 'HH':'mm' hrs'", local);
        String dataEmissao = formate.format(cal.getTime());

        // Adicionando os parametros do relatório.
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_turma", idTurma);
        parametros.put("data", dataEmissao);

        // Inserindo o caminho do relatório.
        String caminhoRelatorio = this.facesContext.getExternalContext().getRealPath("/resources/relatorios");
        String caminhoArquivoJasper = caminhoRelatorio + File.separator + "relatorioTurma" + ".jasper";

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

    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }

    public void setTurmaSelecionada(Turma turmaSelecionada) {
        this.turmaSelecionada = turmaSelecionada;
    }

    public TurmaRN getTurmaRN() {
        return turmaRN;
    }

    public void setTurmaRN(TurmaRN turmaRN) {
        this.turmaRN = turmaRN;
    }

    public boolean isDisabilitaBtnEmitir() {
        return disabilitaBtnEmitir;
    }

    public void setDisabilitaBtnEmitir(boolean disabilitaBtnEmitir) {
        this.disabilitaBtnEmitir = disabilitaBtnEmitir;
    }

}
