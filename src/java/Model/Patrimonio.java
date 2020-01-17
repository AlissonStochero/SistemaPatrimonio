
package Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "patrimonio")
public class Patrimonio implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id_patrimonio;
    @Column(nullable = false,unique = true)
    private Long cod_patrimonio;

    public Integer getId_patrimonio() {
        return id_patrimonio;
    }

    public void setId_patrimonio(Integer id_patrimonio) {
        this.id_patrimonio = id_patrimonio;
    }

    public Long getCod_patrimonio() {
        return cod_patrimonio;
    }

    public void setCod_patrimonio(Long cod_patrimonio) {
        this.cod_patrimonio = cod_patrimonio;
    }

    
    
}
