/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "unidade")
@XmlRootElement
public class Unidade implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id_unidade;
    @Column(nullable = false)
    private String nome;
    private String local;

    public Integer getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(Integer id_unidade) {
        this.id_unidade = id_unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id_unidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unidade unidade = (Unidade) obj;
        if (!Objects.equals(this.id_unidade, unidade.id_unidade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unidade{" + "id=" + id_unidade + ", nome=" + nome + ", local=" + local + '}';
    }
}
