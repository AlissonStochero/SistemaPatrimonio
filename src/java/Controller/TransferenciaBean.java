
package Controller;

import Model.Material;
import Model.Transferencia;
import Model.TransferenciaDAO;
import java.io.Serializable;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alisson
 */
@ManagedBean
@SessionScoped
public class TransferenciaBean implements Serializable{
    private Transferencia transf = new Transferencia();
    private TransferenciaDAO transfDAO = new TransferenciaDAO();
    private Material material = new Material();
    private MaterialBean matBean = new MaterialBean();
    private List<Transferencia> list;

    
    public void getHistoricoMaterialbyId(Material mat){
        this.transf=new Transferencia();
        this.transf.setMaterial(mat);
        this.setList(this.transfDAO.getListByMaterial(this.transf,mat.getId_material()));
        
    }
    
    public void efetuaTransferencia(){
        try {
            matBean.setMaterial(material);
            matBean.atualizaMaterial();
            this.pegaDataAtual();
            this.material.setDepto(this.transf.getDepto_destino());
            this.transf.setMaterial(material);
            transfDAO.create(this.transf);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Material "+material.getNome()+" transferido"));
        } catch (Exception e) {
        }
    }
    
    public void inicialTranfMat(Material material){
        this.pegaDataAtual();
        this.transf.setMaterial(material);
        transfDAO.create(this.transf);
    }
    
    public void pegaMaterialParaTransferencia(int id){
        matBean.editMat(id);
        this.material = matBean.buscaMaterial(id);
        this.transf.setDepto_origem(this.material.getDepto());
    }
    
    public void pegaDataAtual(){
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataA = formatador.format(data);
        this.transf.setData_transferencia(dataA);
    }
    
    public Transferencia getTransf() {
        return transf;
    }

    public void setTransf(Transferencia transf) {
        this.transf = transf;
    }

    public TransferenciaDAO getTransfDAO() {
        return transfDAO;
    }

    public void setTransfDAO(TransferenciaDAO transfDAO) {
        this.transfDAO = transfDAO;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Transferencia> getList() {
        return list;
    }

    public void setList(List<Transferencia> list) {
        this.list = list;
    }
    
    
    public void TransferenciaBean(){}
}
