package br.com.sali.testes;

import br.com.sali.dao.AlunoDAO;
import br.com.sali.dao.GenericoDAO;
import br.com.sali.dao.ProfessorDAO;
import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.ProfessorRN;

/**
 *
 * @author SALI
 */
public class NewClass {

    public static void main(String[] args) {

        ProfessorRN p = new ProfessorRN();

        Professor teste = p.listarTodosProfessores().get(0);
        System.out.println(teste.getTurmas().size());
        
    }
}
