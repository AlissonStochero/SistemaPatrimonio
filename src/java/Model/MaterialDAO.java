
package Model;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.PersistenceException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
/*
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
*/
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.procedure.ProcedureCall;

/**
 *
 * @author alisson
 */
public class MaterialDAO implements Serializable{
    private Session sessao;
    private Transaction trans;
    private List<Material> list;
    
    public void create(Material material) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            
            
            sessao.save(material);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public Material getById(int id){
        System.out.println(id);
        Material mat = new Material();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            return mat = (Material) sessao.get(Material.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return mat;
    }
    //SELECT * FROM `material` JOIN departamento JOIN unidade on departamento.unidade_id_unidade = unidade.id_unidade;
    /**
     * retorna lista de materiais por unidade
     * @param id
     * @return List<Material>
     */
    public List getMaterialByUnidade(Integer id){
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        List listaMaterial = null, li;
        try {
            SQLQuery qry = sessao.createSQLQuery
                ("SELECT patrimonio.cod_patrimonio, material.nome as material_nome, material.valor_atual, material.valor_compra, departamento.nome as depto_nome, unidade.nome as unidade_nome, unidade.id_unidade "
                        + "FROM `material` JOIN patrimonio ON material.patrimonio_id_patrimonio=patrimonio.id_patrimonio "
                        + "JOIN departamento ON material.depto_id_departamento=departamento.id_departamento "
                        + "JOIN unidade "
                        + "ON departamento.unidade_id_unidade=unidade.id_unidade and unidade.id_unidade = :id order by patrimonio.cod_patrimonio asc ;"); //cria o sql 
            qry.setParameter("id", id); // insere o as informações no sql ( :usuario e :senha )
        
            //System.out.println(qry);

            //qry.setResultTransformer(Transformers.aliasToBean(LevantamentoUnidade.class)); // pega o resultado da query tranformando em um obj Usuario
            
            listaMaterial =  qry.list();// pega o objeto na posição 0 da lista
            return listaMaterial;
        } catch (Exception e) {
            e.printStackTrace();
            return listaMaterial;
        }
    }
    
    public Double getTotalValorUnidade(Integer id){
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        List listaMaterial = null;
        Double valor = null;
        try {
            SQLQuery qry = sessao.createSQLQuery
                ("SELECT sum(material.valor_atual) "
                        + "FROM `material` JOIN patrimonio ON material.patrimonio_id_patrimonio=patrimonio.id_patrimonio "
                        + "JOIN departamento ON material.depto_id_departamento=departamento.id_departamento "
                        + "JOIN unidade "
                        + "ON departamento.unidade_id_unidade=unidade.id_unidade and unidade.id_unidade = :id ;"); //cria o sql 
            qry.setParameter("id", id); // insere o as informações no sql ( :usuario e :senha )
        
            //System.out.println(qry);

            //qry.setResultTransformer(Transformers.aliasToBean(LevantamentoUnidade.class)); // pega o resultado da query tranformando em um obj Usuario
            
            listaMaterial =  qry.list();// pega o objeto na posição 0 da lista
            
            valor = (Double) listaMaterial.get(0);
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return valor;
        }
    }
    
    public Double getTotalValorGeral(){
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        List listaMaterial = null;
        Double valor = null;
        try {
            SQLQuery qry = sessao.createSQLQuery
                ("SELECT sum(material.valor_atual) "
                        + "FROM `material` ;"); //cria o sql 
            // insere o as informações no sql ( :usuario e :senha )
        
            //System.out.println(qry);

            //qry.setResultTransformer(Transformers.aliasToBean(LevantamentoUnidade.class)); // pega o resultado da query tranformando em um obj Usuario
            
            listaMaterial =  qry.list();// pega o objeto na posição 0 da lista
            
            valor = (Double) listaMaterial.get(0);
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return valor;
        }
    }
    
    public List<Material> getList() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Material.class);
            this.list = cri.list();
            return list;

        } finally {

            sessao.close();
        }

    }
    
    public void updateMaterial(Material mat) {
        System.out.println(mat.getPatrimonio());
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(mat);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public List getMaterialByPat(Long cod) {
        List material = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            SQLQuery qry = sessao.createSQLQuery
                ("SELECT patrimonio.cod_patrimonio, material.nome AS materialNome, material.valor_atual,material.valor_compra, material.data_aquisicao, unidade.nome as unidadeNome, departamento.nome as deptoNome, "
                        + "material.id_material FROM material JOIN patrimonio "
                        + "ON material.patrimonio_id_patrimonio = patrimonio.id_patrimonio "
                        + "join departamento "
                        + "ON material.depto_id_departamento = departamento.id_departamento"
                        + " JOIN unidade "
                        + "ON departamento.unidade_id_unidade = unidade.id_unidade "
                        + "AND patrimonio.cod_patrimonio = :cod"); //cria o sql 
            qry.setParameter("cod", cod); // insere o as informações no sql ( :usuario e :senha )
        
            
            
            return material = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return material;

    }
    
 public boolean realizaDepreciacaoMateriais() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Query qry = sessao.createSQLQuery("UPDATE material INNER JOIN tipodepreciacao ON material.tipoDepreciacao_id_tipo = tipodepreciacao.id_tipo SET material.valor_atual = material.valor_atual - ((material.valor_atual * tipodepreciacao.percentualDepreciacao) / 100) WHERE material.id_material >= 1"); // insere o as informações no sql ( :usuario e :senha )
            
            System.out.println(qry.executeUpdate());
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessao.close();
        } finally{
            sessao.close();
        }
        return false;

    }
    
    public boolean criaRelatorio(){
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        Map<String,Object> params= new HashMap<String,Object>();
        System.out.println("DAO");
        try {
            SQLQuery qry = sessao.createSQLQuery
                ("SELECT material.`nome`,material.`valor_atual`,material.`valor_compra`,patrimonio.`cod_patrimonio`,material.`data_aquisicao` FROM `patrimonio` patrimonio INNER JOIN `material` material ON patrimonio.`id_patrimonio` = material.`patrimonio_id_patrimonio`");

            List<JRBeanCollectionDataSource> dados = qry.list();
            InputStream caminhoRelatorio = this.getClass().getClassLoader().getResourceAsStream("../Relatorios/report1.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio,params, new JRBeanCollectionDataSource(dados));
            File file = new File("/home/alisson/NetBeansProjects/tcc/src/java/Relatorios/relteste.pdf");
            JasperViewer.viewReport(jasperPrint,false);
            try {
                
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
                return false;
        }
    }
    
}
