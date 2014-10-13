/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Igor Barreto
 */
@FacesConverter(value = "Converter", forClass = EntityConverter.class)
public class Converter implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null) {
            return this.getAttributesFrom(uic).get(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && !"".equals(o)) {

            EntityConverter entity = (EntityConverter) o;

            this.addAttribute(uic, entity);

            Long codigo = entity.getId();

            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) o;
    }

    public void addAttribute(UIComponent component, EntityConverter o) {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }

    public Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

    private Object findById(Collection collection, Long idToFind) {
        for (Object obj : collection) {
            Long id = getIdByReflection(obj);
            if (id == idToFind) {
                return obj;
            }
        }

        return null;
    }

    private Long getIdByReflection(Object bean) {
        try {
            Field idField = bean.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(bean);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível obter a propriedade 'id' do item", ex);
        }
    }
}
