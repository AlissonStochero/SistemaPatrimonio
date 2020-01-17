/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Converter.TipoDepreciacaoConverter;
import Model.Departamento;
import Model.LevantamentoUnidade;
import Model.Material;
import Model.MaterialDAO;
import Model.Patrimonio;
import Model.Relatorio;
import Model.TipoDepreciacao;
import Model.Unidade;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
public class MaterialBean implements Serializable{
    private Material material = new Material();
    private MaterialDAO materialDAO = new MaterialDAO();
    private Patrimonio patrimonio = new Patrimonio();
    private List<Material> list;
    private List listMaterial;
    private Unidade unidade;
    private Departamento depto;
    private DepartamentoBean dBean=new DepartamentoBean();
    private List<LevantamentoUnidade> levantamentoUnidade;
    private TransferenciaBean transBean;
    private TipoDepreciacao tipoDep  = new TipoDepreciacao();
    private TipoDepreciacaoBean tipoBean = new TipoDepreciacaoBean();
    private Double valorTotal = 0.0;
    private Double valorTotalGeral = 0.0;

    public Material buscaMaterial(int id){
        return materialDAO.getById(id);
    }
    /**
     * metodo que busca o material baseado no patrimonio
     * (Usado no web service)
     * @param cod
     * @return 
     */
    public List getMaterialByPat(Long cod){
        return this.materialDAO.getMaterialByPat(cod);
    }
    
    public void realizaDepreciacao(){
        boolean a = materialDAO.realizaDepreciacaoMateriais();
        System.out.println(a);
    }
    
    public void atualizaMaterial(){
        materialDAO.updateMaterial(material);
        mensagem("Material "+material.getNome()+" atualizado com sucesso!", "");
    }
    
    public void editMat(int id_func){
        //System.out.println("aaaaaa"+id_func);
        this.material=new Material();
        this.material=materialDAO.getById(id_func);
    }
    
    /***
     * busca por todos os materiais pertencentes a unidade selecionada
     * @return List<Material>
     */
    public void getMaterialByUnidade(Unidade un){
        //this.setDepto(this.dBean.getDepto());
        this.setUnidade(this.unidade);
        this.setLevantamentoUnidade(materialDAO.getMaterialByUnidade(this.unidade.getId_unidade()));
        this.retornaTotalUnidade(this.unidade.getId_unidade());
    }
    
    public void materialByPatrimonio(){
        Long pat = this.patrimonio.getCod_patrimonio();
        this.setListMaterial(materialDAO.getMaterialByPat(pat));

    }
    
    public void retornaTotalUnidade(int id){
        Double valor = materialDAO.getTotalValorUnidade(id);
        this.valorTotal=valor;
        this.setValorTotal(valorTotal);
    }
    
    public double retornaTotal(){
        Double valor = materialDAO.getTotalValorGeral();
        this.setValorTotalGeral(valor);
        if(this.getValorTotalGeral() != null)
            return this.getValorTotalGeral();
        else
            return 0.0;
    }
    
    public List retornaLevantamento(){
        return this.getLevantamentoUnidade();
    }

    public void salvaMaterial(){
        if((this.patrimonio.getCod_patrimonio()!=0)||(this.patrimonio.getCod_patrimonio()!=null)){
            material.setPatrimonio(patrimonio);
        }
        
            //System.out.println("------------------------------------------------------------------- material"+tipoDep.getId_tipo());
        if((this.tipoDep.getId_tipo()!=0)||(this.tipoDep.getId_tipo()!=null)){
            
            material.setTipoDepreciacao(this.tipoBean.retornaTipoDep(this.tipoDep.getId_tipo()));
        }
        try {
            //this.tipoDep = this.tipoBean.retornaTipoDep();
            
            materialDAO.create(material);
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Material "+material.getNome()+" cadastrado"));
            this.material = new Material();
            this.patrimonio = new Patrimonio();
        } catch (Exception e) {
            this.material = new Material();
            material=new Material();
            System.out.println(e);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Erro ao cadastrar material"));
        }finally{
            this.material = new Material();
            System.out.println("finally"+material.getNome());
        }
        
    }
    
    public List getAllMateriais(){
        this.retornaTotal();
        this.setList(materialDAO.getList());
        return this.list;
    }
    
    public List pegaMaterialByPatrimonio(){
        
        return this.getListMaterial();
    }

    public TransferenciaBean getTransBean() {
        return transBean;
    }

    public void setTransBean(TransferenciaBean transBean) {
        this.transBean = transBean;
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public MaterialDAO getMaterialDAO() {
        return materialDAO;
    }

    public void setMaterialDAO(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    public List<Material> getList() {
        return list;
    }

    public void setList(List<Material> list) {
        this.list = list;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }
    
    public void MaterialBean(){}

    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

        
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Departamento getDepto() {
        return depto;
    }

    public void setDepto(Departamento depto) {
        this.depto = depto;
    }

    public DepartamentoBean getdBean() {
        return dBean;
    }

    public void setdBean(DepartamentoBean dBean) {
        this.dBean = dBean;
    }

    public List getListMaterial() {
        return listMaterial;
    }

    public void setListMaterial(List listMaterial) {
        this.listMaterial = listMaterial;
    }

    public List<LevantamentoUnidade> getLevantamentoUnidade() {
        return levantamentoUnidade;
    }

    public void setLevantamentoUnidade(List<LevantamentoUnidade> levantamentoUnidade) {
        this.levantamentoUnidade = levantamentoUnidade;
    }

    public TipoDepreciacao getTipoDep() {
        return tipoDep;
    }

    public void setTipoDep(TipoDepreciacao tipoDep) {
        this.tipoDep = tipoDep;
    }

    public TipoDepreciacaoBean getTipoBean() {
        return tipoBean;
    }

    public void setTipoBean(TipoDepreciacaoBean tipoBean) {
        this.tipoBean = tipoBean;
    }

    public Double getValorTotalGeral() {
        return valorTotalGeral;
    }

    public void setValorTotalGeral(Double valorTotalGeral) {
        this.valorTotalGeral = valorTotalGeral;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.tipoDep);
        hash = 83 * hash + Objects.hashCode(this.list);
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
        final TipoDepreciacaoBean other = (TipoDepreciacaoBean) obj;
        if (!Objects.equals(this.tipoDep, other.tipoDep)) {
            return false;
        }
        return true;
    }
    
}
