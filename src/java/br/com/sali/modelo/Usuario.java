package br.com.sali.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author SALI
 */
@Entity
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    private String email;
    private String senha;
    
    
    @ElementCollection(targetClass = String.class)
    @JoinTable(
            name = "usuario_permissao",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario","permissao"})},
            joinColumns = @JoinColumn(name = "usuario")
    )
    @Column(name = "permissao", length = 50)
    private Set<String> permissao = new HashSet<String>();
    
    
}
