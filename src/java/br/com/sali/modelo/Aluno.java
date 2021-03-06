package br.com.sali.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Representa o Aluno.
 *
 * @author SALI
 */
@Entity
@Table
public class Aluno implements Serializable {

    // Atributos.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_aluno")
    private Long id;
    @Column
    private String nome;
    @Column
    private int matricula;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Usuario usuario;
    
    
    @OneToMany(mappedBy = "alunnoQueRealizouQuiz")
    @Cascade(CascadeType.ALL)
    private List<QuizRealizado> quizesRealizados;
    
    // Construtor.
    public Aluno() {

    }

    //=============================== Gets e Sets ==============================

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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<QuizRealizado> getQuizesRealizados() {
        return quizesRealizados;
    }

    public void setQuizesRealizados(List<QuizRealizado> quizesRealizados) {
        this.quizesRealizados = quizesRealizados;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + this.matricula;
        hash = 53 * hash + Objects.hashCode(this.turma);
        hash = 53 * hash + Objects.hashCode(this.usuario);
        hash = 53 * hash + Objects.hashCode(this.quizesRealizados);
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
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.matricula != other.matricula) {
            return false;
        }
        if (!Objects.equals(this.turma, other.turma)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.quizesRealizados, other.quizesRealizados)) {
            return false;
        }
        return true;
    }

    

   

    
    
    
}
