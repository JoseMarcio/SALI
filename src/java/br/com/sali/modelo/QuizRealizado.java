package br.com.sali.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author SALI
 */
@Entity
@Table
public class QuizRealizado implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idQuizRealizado;
    private double aproveitamento;
    private String questoesCorretas;
    @OneToOne
    private Aluno alunnoQueRealizouQuiz;

    public QuizRealizado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdQuizRealizado() {
        return idQuizRealizado;
    }

    public void setIdQuizRealizado(Long idQuizRealizado) {
        this.idQuizRealizado = idQuizRealizado;
    }

    public double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public String getQuestoesCorretas() {
        return questoesCorretas;
    }

    public void setQuestoesCorretas(String questoesCorretas) {
        this.questoesCorretas = questoesCorretas;
    }

    public Aluno getAlunnoQueRealizouQuiz() {
        return alunnoQueRealizouQuiz;
    }

    public void setAlunnoQueRealizouQuiz(Aluno alunnoQueRealizouQuiz) {
        this.alunnoQueRealizouQuiz = alunnoQueRealizouQuiz;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.idQuizRealizado);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.aproveitamento) ^ (Double.doubleToLongBits(this.aproveitamento) >>> 32));
        hash = 31 * hash + Objects.hashCode(this.questoesCorretas);
        hash = 31 * hash + Objects.hashCode(this.alunnoQueRealizouQuiz);
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
        final QuizRealizado other = (QuizRealizado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idQuizRealizado, other.idQuizRealizado)) {
            return false;
        }
        if (Double.doubleToLongBits(this.aproveitamento) != Double.doubleToLongBits(other.aproveitamento)) {
            return false;
        }
        if (!Objects.equals(this.questoesCorretas, other.questoesCorretas)) {
            return false;
        }
        if (!Objects.equals(this.alunnoQueRealizouQuiz, other.alunnoQueRealizouQuiz)) {
            return false;
        }
        return true;
    }
    

    
    
    
}
