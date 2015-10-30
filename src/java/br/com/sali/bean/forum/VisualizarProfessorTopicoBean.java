package br.com.sali.bean.forum;

import br.com.sali.modelo.MenssagemTopico;
import br.com.sali.modelo.Topico;
import br.com.sali.regras.TopicoRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class VisualizarProfessorTopicoBean implements Serializable {

    private Long idTopico;
    private Topico topico;
    private TopicoRN topicoRN;
    private List<MenssagemTopico> menssagensDoTopico;

    public VisualizarProfessorTopicoBean() {
        this.menssagensDoTopico = new ArrayList<>();
        this.topicoRN = new TopicoRN();
        this.topico = new Topico();
    }

    public void carregarTopico() {
        this.topico = this.topicoRN.pegarTopicoPorId(this.idTopico);
    }

    public List<MenssagemTopico> getMenssagensDoTopico() {
        return menssagensDoTopico;
    }

    public void setMenssagensDoTopico(List<MenssagemTopico> menssagensDoTopico) {
        this.menssagensDoTopico = menssagensDoTopico;
    }
    
    
    
    

    public Long getIdTopico() {
        return idTopico;
    }
    
    

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public TopicoRN getTopicoRN() {
        return topicoRN;
    }

    public void setTopicoRN(TopicoRN topicoRN) {
        this.topicoRN = topicoRN;
    }

    /**
     * Direciona para a página de visualização de tópicos.
     *
     * @param idLicao
     * @return
     */
    public String urlVisualizarArquivoLicao(Long idLicao) {
        return "http://localhost:8080/SALI/professor/visualizar-topico.jsf?id=" + idLicao;
    }
}
