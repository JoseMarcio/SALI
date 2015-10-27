package br.com.sali.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author SALI
 */
@Entity
@Table
public class Questao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String pergunta;
    @Column
    private String[] alternativas;
    @Column
    private int alternativaCorreta;

    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private Quiz quiz;
    
    public Questao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public int getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(int alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.pergunta);
        hash = 89 * hash + Arrays.deepHashCode(this.alternativas);
        hash = 89 * hash + this.alternativaCorreta;
        hash = 89 * hash + Objects.hashCode(this.quiz);
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
        final Questao other = (Questao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.pergunta, other.pergunta)) {
            return false;
        }
        if (!Arrays.deepEquals(this.alternativas, other.alternativas)) {
            return false;
        }
        if (this.alternativaCorreta != other.alternativaCorreta) {
            return false;
        }
        if (!Objects.equals(this.quiz, other.quiz)) {
            return false;
        }
        return true;
    }
    
    
    

    
    
}
