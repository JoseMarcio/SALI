package br.com.sali.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Representa o Professor.
 *
 * @author SALI
 */
@Entity
@Table
public class Professor implements Serializable {

    // Atributos.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_professor")
    private Long id;
    private String nome;
    private int matricula;
    
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Turma turmaAtual;
    
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Turma> turmas = new ArrayList<>();

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Usuario usuario;
    
    // Construtor.
    public Professor() {
    }

    //========================= Gets e Sets ====================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Turma getTurmaAtual() {
        return turmaAtual;
    }

    public void setTurmaAtual(Turma turmaAtual) {
        this.turmaAtual = turmaAtual;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + this.matricula;
        hash = 89 * hash + Objects.hashCode(this.turmas);
        hash = 89 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.matricula != other.matricula) {
            return false;
        }
        if (!Objects.equals(this.turmas, other.turmas)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
    

}
