package br.com.sali.regras;

import br.com.sali.dao.ProfessorDAO;
import br.com.sali.modelo.Professor;
import java.util.List;

/**
 *
 * @author SALI
 */
public class ProfessorRN {
    
    private ProfessorDAO professorDAO;
    
    /**
     * Salva um professor.
     * @param professor 
     */
    public void registrarProfessor(Professor professor){
        professorDAO.salvar(professor);
    }
    
    /**
     * Atualiza um professor do banco de dados.
     * @param professor
     */
    public void atualizarProfessor(Professor professor){
        professorDAO.atualizar(professor);
    }
            
    /**
     * Lista os professores solicitados.
     * @param filtro
     * @return 
     */
    public List<Professor> listarProfessores(String filtro){
        return professorDAO.listar(Professor.class, filtro);
    }
    
    /**
     * Exclui um professor se o mesmo não tiver registrado em nenhuma turma.
     * @param professor
     * @return 
     */
    public boolean excluirProfessor(Professor professor){
        if(professor.getTurmas().isEmpty()){
            professorDAO.excluir(professor);
            return true;
        }
        else{
            return false;
        }
    }
            
}
