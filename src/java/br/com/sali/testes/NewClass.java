package br.com.sali.testes;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.dao.GenericoDAO;
import br.com.sali.dao.ProfessorDAO;
import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Aluno;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.ProfessorRN;

/**
 *
 * @author SALI
 */
public class NewClass {

    public static void main(String[] args) {

        AlunoDAO al = new AlunoDAO();
        
        Aluno a = new Aluno();
        
        a.setNome("João");
        
        al.salvar(a);
        
    }
}
