
package Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author alisson
 */
@Entity
@Table(name = "material")
public class Material implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id_material;
    
    @Column(nullable = false)
    private String nome; 
    private String data_aquisicao;
    private String data_att_depreciacao;
    private Float valor_compra;
    private Float valor_atual;
    
    @OneToOne(cascade = CascadeType.ALL,optional = false)
    private Patrimonio patrimonio;
    
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    private Departamento depto;
    
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    private TipoDepreciacao tipoDepreciacao;

    public Integer getId_material() {
        return id_material;
    }

    public void setId_material(Integer id_material) {
        this.id_material = id_material;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_aquisicao() {
        return data_aquisicao;
    }

    public void setData_aquisicao(String data_aquisicao) {
        this.data_aquisicao = data_aquisicao;
    }

    

    public Float getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(Float valor_compra) {
        this.valor_compra = valor_compra;
    }

    public Float getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(Float valor_atual) {
        this.valor_atual = valor_atual;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Departamento getDepto() {
        return depto;
    }

    public void setDepto(Departamento depto) {
        this.depto = depto;
    }

    public TipoDepreciacao getTipoDepreciacao() {
        return tipoDepreciacao;
    }

    public void setTipoDepreciacao(TipoDepreciacao tipoDepreciacao) {
        this.tipoDepreciacao = tipoDepreciacao;
    }

    public String getData_att_depreciacao() {
        return data_att_depreciacao;
    }

    public void setData_att_depreciacao(String data_att_depreciacao) {
        this.data_att_depreciacao = data_att_depreciacao;
    }

    
}
