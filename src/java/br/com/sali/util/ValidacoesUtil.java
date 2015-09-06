package br.com.sali.util;

/**
 *
 * @author SALI
 */
public class ValidacoesUtil {
    
    /**
     * Verifica se só contém números.
     * @param texto
     * @return 
     */
    public static boolean soContemNumeros(String texto) {  
        if(texto == null)  
            return false;  
        for (char letra : texto.toCharArray())  
            if(letra < '0' || letra > '9')  
                return false;  
        return true;  
          
    }  
}
