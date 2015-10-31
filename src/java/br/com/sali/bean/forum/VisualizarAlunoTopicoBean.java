package br.com.sali.bean.forum;

import br.com.sali.dao.MenssagemDao;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.MenssagemTopico;
import br.com.sali.modelo.Topico;
import br.com.sali.modelo.Usuario;
import br.com.sali.regras.AlunoRN;
import br.com.sali.regras.TopicoRN;
import br.com.sali.regras.UsuarioRN;
import br.com.sali.util.ValidacoesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;



/**
 *
 * @author SALI
 */
@ManagedBean
@ViewScoped
public class VisualizarAlunoTopicoBean implements Serializable {

    private Long idTopico;
    private Topico topico;
    private TopicoRN topicoRN;
    private List<MenssagemTopico> menssagensDoTopico;
    private MenssagemTopico menssagemTopico;

    //Construtor
    public VisualizarAlunoTopicoBean() {
        this.menssagensDoTopico = new ArrayList<>();
        this.topicoRN = new TopicoRN();
        this.topico = new Topico();
        this.menssagemTopico = new MenssagemTopico();
    }
//========================================================================== 

    /**
     * Carregar o tópico pelo parametro que foi passado pela url.
     */
    public void carregarTopico() {
        Topico topicoCarregar = this.topicoRN.pegarTopicoPorId(this.idTopico);
        MenssagemDao menssagemDao = new MenssagemDao();
        List<MenssagemTopico> ms = menssagemDao.listarMenssagensPorTopico(topicoCarregar);
        this.topico = topicoCarregar;
        this.menssagensDoTopico = ms;
    }
    
    public void teste(){
         MenssagemDao menssagemDao = new MenssagemDao();
        List<MenssagemTopico> ms = menssagemDao.listarMenssagensPorTopico(topico);
    }

    /**
     * Limpa a bean.
     */
    public void limpar() {
        this.menssagemTopico = new MenssagemTopico();
    }

    /**
     * Envia uma mensagem no topico atual.
     *
     */
    public void enviarMensagem() {
        if (this.menssagemTopico.getTexto() == null || ValidacoesUtil.soTemEspaco(this.menssagemTopico.getTexto())) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "É interessante inserir texto nas suas mensagens!");
            RequestContext.getCurrentInstance().showMessageInDialog(m);
        } else {
            this.menssagemTopico.setAutor(getAlunoConectado().getNome());
            this.menssagemTopico.setTopico(this.topico);
            MenssagemDao menssagemTopicoDao = new MenssagemDao();
            menssagemTopicoDao.salvar(this.menssagemTopico);
            limpar();
            carregarTopico();
        }

    }

    /**
     * Retorna o aluno autenticado no momento.
     *
     * @return
     */
    public Aluno getAlunoConectado() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();

        String emailUsuario = external.getRemoteUser();

        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario usuario = usuarioRN.getUsuarioByEmail(emailUsuario);

        AlunoRN alunoRN = new AlunoRN();

        Aluno aluno = alunoRN.getAlunoByUsuario(usuario);

        return aluno;
    }

    //==========================================================================
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

    public MenssagemTopico getMenssagemTopico() {
        return menssagemTopico;
    }

    public void setMenssagemTopico(MenssagemTopico menssagemTopico) {
        this.menssagemTopico = menssagemTopico;
    }

}
