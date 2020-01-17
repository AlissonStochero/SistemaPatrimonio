/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alisson
 */
public class TransferenciaDAO implements Serializable{
    private Session sessao;
    private Transaction trans;
    private List<Transferencia> list;
    
    public void create(Transferencia transf) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(transf);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public Transferencia getById(int id){
        Transferencia transf = new Transferencia();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            return transf = (Transferencia) sessao.get(Transferencia.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return transf;
    }
    
    public List<Transferencia> getList() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Transferencia.class);
            this.list = cri.list();
            return list;

        } finally {

            sessao.close();
        }

    }
    
    public List<Transferencia> getListByMaterial(Transferencia transferencia,int id) {
        Transferencia transf;
        System.out.println(id);
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            SQLQuery qry = sessao.createSQLQuery
                ("SELECT patrimonio.cod_patrimonio, material.nome as material_nome, transferencia.data_transferencia, "
                        + "unidade.nome as unidade_nomeDest, departamento.nome as departamento_nomeDest "
                        + "from transferencia "
                        + "JOIN departamento "
                        + "ON departamento.id_departamento = transferencia.depto_destino_id_departamento "
                        + "JOIN unidade "
                        + "ON unidade.id_unidade = departamento.unidade_id_unidade "
                        + "JOIN material "
                        + "ON material.id_material = transferencia.material_id_material "
                        + "JOIN patrimonio ON material.patrimonio_id_patrimonio = patrimonio.id_patrimonio "
                        + "AND transferencia.material_id_material = :id "
                        + "ORDER BY id_transferencia DESC"); //cria o sql 
            qry.setParameter("id", id);
            
            this.list = qry.list();
            return list;

        } finally {

            sessao.close();
        }
        //SELECT patrimonio.cod_patrimonio, material.nome as material_nome, transferencia.data_transferencia, unidade.nome as unidade_nome, departamento.nome as departamento_nome from transferencia JOIN departamento ON departamento.id_departamento = transferencia.depto_destino_id_departamento JOIN unidade ON unidade.id_unidade = departamento.unidade_id_unidade JOIN material ON material.id_material = transferencia.material_id_material JOIN patrimonio ON material.patrimonio_id_patrimonio = patrimonio.id_patrimonio AND transferencia.material_id_material = 1
    }
    
    public void updateMaterial(Transferencia transf) {
        
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(transf);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
}
