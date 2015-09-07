package br.com.sali.regras;

import br.com.sali.dao.ProfessorDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.util.CriptografiaUtil;
import java.util.List;

/**
 *
 * @author SALI
 */
public class ProfessorRN {
    
    private ProfessorDAO professorDAO;
    private CriptografiaUtil criptografiaUtil;
    
    public ProfessorRN(){
        professorDAO = new ProfessorDAO();
        criptografiaUtil = new CriptografiaUtil();
    }
    
    /**
     * Salva um professor.
     * @param professor 
     */
    public void registrarProfessor(Professor professor){
        professor.setSenha(criptografiaUtil.criptografaSenha(professor.getSenha()));
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
        
    /**
     * Verifica se o e-mail informado já existe no banco de dados.
     *
     * @param email
     * @return
     */
    public boolean isExistenteEmail(String email) {
        return professorDAO.isExistenteEmail(Professor.class, email);
    }
    
     /**
     * Verifica se a matrícula informada já existe no banco de dados.
     *
     * @param matricula
     * @return
     */
    public boolean isExisteEssaMatricula(String matricula) {
        return professorDAO.isExisteEssaMatricula(Professor.class, matricula);
    }
}
