
package Model;

import java.io.Serializable;

public class LevantamentoUnidade implements Serializable{
    private Integer cod_patrimonio;
    private String nome_material;
    private Float valor_atual;
    private Float valor_compra;
    private String depto_nome;
    private String unidade_nome;

    public Integer getCod_patrimonio() {
        return cod_patrimonio;
    }

    public void setCod_patrimonio(Integer cod_patrimonio) {
        this.cod_patrimonio = cod_patrimonio;
    }

    public String getNome_material() {
        return nome_material;
    }

    public void setNome_material(String nome_material) {
        this.nome_material = nome_material;
    }

    public Float getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(Float valor_atual) {
        this.valor_atual = valor_atual;
    }

    public Float getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(Float valor_compra) {
        this.valor_compra = valor_compra;
    }

    public String getDepto_nome() {
        return depto_nome;
    }

    public void setDepto_nome(String depto_nome) {
        this.depto_nome = depto_nome;
    }

    public String getUnidade_nome() {
        return unidade_nome;
    }

    public void setUnidade_nome(String unidade_nome) {
        this.unidade_nome = unidade_nome;
    }
    
    
    
}  
