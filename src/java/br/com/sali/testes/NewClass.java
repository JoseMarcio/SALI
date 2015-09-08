package br.com.sali.testes;

import br.com.sali.dao.TurmaDAO;
import br.com.sali.modelo.Professor;
import br.com.sali.modelo.Turma;
import br.com.sali.regras.ProfessorRN;
import br.com.sali.util.ValidacoesUtil;

/**
 *
 * @author SALI
 */
public class NewClass {

    public static void main(String[] args) {

        TurmaDAO td = new TurmaDAO();
        Turma t = new Turma();

        ProfessorRN p = new ProfessorRN();
        
      //  t.setNome("testando");
        //t.setProfessor(p.listarProfessores("8989").get(0));
        
        //td.salvar(t);
        
        Professor pf = p.listarProfessores("23").get(0);
        pf.getTurmas().clear();
        System.out.println(p.excluirProfessor(pf));
        
    }
}
