/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Model.TipoDepreciacao;
import Model.TipoDepreciacaoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "depreciacaoConverter", forClass = TipoDepreciacao.class)
public class TipoDepreciacaoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && equals(value)){
            Integer codigo = Integer.valueOf(value);
            TipoDepreciacaoDAO tDAO = new TipoDepreciacaoDAO();
            System.out.println(codigo);
            return tDAO.getById(codigo);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && equals(value)){
            TipoDepreciacao un = (TipoDepreciacao) value;
            return un.getDesc_tipo();
        }
        return null;
    }
    
}
