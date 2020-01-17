
package Controller;

import Model.LevantamentoUnidade;
import Model.MaterialDAO;
import Model.Relatorio;
import Model.Unidade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LevantamentoUnidadeBean implements Serializable{
    private LevantamentoUnidade levantamentoUnidade = new LevantamentoUnidade();
    private MaterialBean materialBean;
    private Unidade unidade= new Unidade();
    
    public void geraRelatorioByUnidade(Unidade un){
        
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorioByUnidade(un.getId_unidade());
    }
    public void geraRel(){
        Relatorio relatorio = new Relatorio();
        relatorio.getRelatorio();
    }

    public LevantamentoUnidade getLevantamentoUnidade() {
        return levantamentoUnidade;
    }

    public void setLevantamentoUnidade(LevantamentoUnidade levantamentoUnidade) {
        this.levantamentoUnidade = levantamentoUnidade;
    }


    public MaterialBean getMaterialBean() {
        return materialBean;
    }

    public void setMaterialBean(MaterialBean materialBean) {
        this.materialBean = materialBean;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    
    
    public void LevantamentoUnidadeBean(){}
}
