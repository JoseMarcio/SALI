package br.com.sali.regras;

import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Turma;
import java.util.List;

/**
 *
 * @author SALI
 */
public class TurmaRN {
    
    private final TurmaDAO turmaDAO;

    public TurmaRN() {
        turmaDAO = new TurmaDAO();
    }
    
    
    /**
     * Salva uma turma.
     * @param turma 
     */
    public void registrarTurma(Turma turma){
            turmaDAO.salvar(turma);
    }
    
    /**
     * Atualiza uma Turma do banco de dados.
     * @param turma
     */
    public void atualizarTurma(Turma turma){
        turmaDAO.atualizar(turma);
    }
            
    /**
     * Lista as turmas solicitadas.
     * @param filtro
     * @return 
     */
    public List<Turma> listarTurmas(String filtro){
        return turmaDAO.listar(Turma.class, filtro);
    }
    
    /**
     * Exclui um professor se o mesmo não tiver registrado em nenhuma turma.
     * @param turma 
     */
    public void excluirTurma(Turma turma){
        turma.getProfessor().getTurmas().remove(turma);
        this.turmaDAO.excluir(turma);
    }
    
    
     /**
     * Verfica se o nome informado já existe no banco de dados.
     *
     * @param nome
     * @return
     */
    public boolean isExistenteNome(String nome) {
        return this.turmaDAO.isExistenteNomeTurma(nome);
    }
}
