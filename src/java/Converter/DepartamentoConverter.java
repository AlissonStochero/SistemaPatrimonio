/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;


import Model.Departamento;
import Model.DepartamentoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "departamentoConverter", forClass = Departamento.class)
public class DepartamentoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            Integer codigo = Integer.valueOf(value);
            DepartamentoDAO deptoDAO = new DepartamentoDAO();
            return deptoDAO.getById(codigo);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Departamento depto = (Departamento) value;
            return depto.getId_departamento().toString();
        }
        return null;
    }
    
}
