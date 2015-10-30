package br.com.sali.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa a Turma.
 *
 * @author José
 */
@Entity
@Table
public class Turma implements Serializable {

    // Atributos.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_turma")
    private Long id;
    @Column
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Aluno> alunos;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Quiz> quizesDaTurma = new ArrayList<>();
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Licao> licoesDaTurma = new ArrayList<>();    
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Topico> listaTopicos = new ArrayList<>();
            

    // Construtor.
    public Turma() {
    }

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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Quiz> getQuizesDaTurma() {
        return quizesDaTurma;
    }

    public void setQuizesDaTurma(List<Quiz> quizesDaTurma) {
        this.quizesDaTurma = quizesDaTurma;
    }

    public List<Licao> getLicoesDaTurma() {
        return licoesDaTurma;
    }

    public void setLicoesDaTurma(List<Licao> licoesDaTurma) {
        this.licoesDaTurma = licoesDaTurma;
    }

    public List<Topico> getListaTopicos() {
        return listaTopicos;
    }

    public void setListaTopicos(List<Topico> listaTopicos) {
        this.listaTopicos = listaTopicos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.professor);
        hash = 79 * hash + Objects.hashCode(this.alunos);
        hash = 79 * hash + Objects.hashCode(this.quizesDaTurma);
        hash = 79 * hash + Objects.hashCode(this.licoesDaTurma);
        hash = 79 * hash + Objects.hashCode(this.listaTopicos);
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
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        if (!Objects.equals(this.alunos, other.alunos)) {
            return false;
        }
        if (!Objects.equals(this.quizesDaTurma, other.quizesDaTurma)) {
            return false;
        }
        if (!Objects.equals(this.licoesDaTurma, other.licoesDaTurma)) {
            return false;
        }
        if (!Objects.equals(this.listaTopicos, other.listaTopicos)) {
            return false;
        }
        return true;
    }

    
}
