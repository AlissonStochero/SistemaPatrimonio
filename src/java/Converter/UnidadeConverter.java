/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Model.Unidade;
import Model.UnidadeDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "unidadeConverter", forClass = Unidade.class)
public class UnidadeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            Integer codigo = Integer.valueOf(value);
            UnidadeDAO unidadeDAO = new UnidadeDAO();
            return unidadeDAO.getById(codigo);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Unidade un = (Unidade) value;
            return un.getId_unidade().toString();
        }
        return null;
    }
    
}
