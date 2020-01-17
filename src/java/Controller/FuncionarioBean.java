/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Funcionario;
import Model.FuncionarioDAO;
import Model.Usuario;
import Model.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class FuncionarioBean implements Serializable{
    
    private Funcionario funcionario = new Funcionario();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Usuario user = new Usuario();
    private UsuarioDAO userDAO = new UsuarioDAO();
    private List<Funcionario> list;
    
    public void salvaFuncionarioUsuario(){
        
        if((!user.getNome_usuario().isEmpty()) && (user.getNome_usuario()!=null)){
            funcionario.setUser(user);
        }
        funcionarioDAO.create(funcionario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Colaborador "+funcionario.getNome()+" cadastrado"));
        this.funcionario=new Funcionario();
        this.user=new Usuario();
    }
    
    public List getAllListFunc(){
        this.setList(funcionarioDAO.getList());
        return this.list;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public void editFunc(int id_func){
        //System.out.println("aaaaaa"+id_func);
        this.funcionario=new Funcionario();
        this.funcionario=funcionarioDAO.getById(id_func);
        //return "indexx";
    }
    
    public void atualizaFuncionario(){
        funcionarioDAO.updateFuncionario(funcionario);
        mensagem("Funcionário "+funcionario.getNome()+" atualizado com sucesso!", "");
        //funcionario = new Funcionario();
    }
    
    public void resetFuncionario(){
        this.funcionario = new Funcionario();
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public UsuarioDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UsuarioDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<Funcionario> getList() {
        return list;
    }

    public void setList(List<Funcionario> list) {
        this.list = list;
    }
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
 
}
