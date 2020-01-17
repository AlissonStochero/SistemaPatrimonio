
package Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id_transferencia;
    @Column(nullable = false)
    private String data_transferencia;
    
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    private Material material;
    
    @ManyToOne(cascade = CascadeType.MERGE,optional = false)
    private Departamento depto_origem;
    
    @ManyToOne(cascade = CascadeType.MERGE,optional = false)
    private Departamento depto_destino;

    public Integer getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(Integer id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    public String getData_transferencia() {
        return data_transferencia;
    }

    public void setData_transferencia(String data_transferencia) {
        this.data_transferencia = data_transferencia;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Departamento getDepto_origem() {
        return depto_origem;
    }

    public void setDepto_origem(Departamento depto_origem) {
        this.depto_origem = depto_origem;
    }

    public Departamento getDepto_destino() {
        return depto_destino;
    }

    public void setDepto_destino(Departamento depto_destino) {
        this.depto_destino = depto_destino;
    }
    
    
    
}
