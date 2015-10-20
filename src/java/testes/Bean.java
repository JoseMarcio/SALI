package testes;

import br.com.sali.util.relatorio.Relatorio;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class Bean {
    
    
    StreamedContent a;

    public StreamedContent getA() {
        
        String nomeR = "relatorioTurma";
        String nomeS = "slkflkjflkf";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_turma", Integer.toUnsignedLong(1));
        parametros.put("nome_turma", "Vandeberg");
        
        
        Relatorio r = new Relatorio(nomeR, nomeS, parametros);
        
        try {
            a = r.gerarRelatorio();
        } catch (Exception ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

  
    
}
