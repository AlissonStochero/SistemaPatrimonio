
package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tipodepreciacao")
public class TipoDepreciacao implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id_tipo;
    
    @Column(nullable = true)
    private String desc_tipo;
    
    @Column(nullable = false)
    private Float percentualDepreciacao;

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getDesc_tipo() {
        return desc_tipo;
    }

    public void setDesc_tipo(String desc_tipo) {
        this.desc_tipo = desc_tipo;
    }

    public Float getPercentualDepreciacao() {
        return percentualDepreciacao;
    }

    public void setPercentualDepreciacao(Float percentualDepreciacao) {
        this.percentualDepreciacao = percentualDepreciacao;
    }

      
    
}
