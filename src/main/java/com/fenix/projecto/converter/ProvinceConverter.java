package com.fenix.projecto.converter;

import com.fenix.projecto.repository.ProvinciaRepsoitory;
import com.fenix.projecto.util.Logger;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "provinceConverter", managed = true)
public class ProvinceConverter implements Converter<Object> {

    @Inject
    private ProvinciaRepsoitory repo;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Logger.info("getAsObject: " + value);
        if (value == null || value.isBlank()) return null;
        return repo.findByNome(value).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Logger.info("getAsString: " + value);
        return value.toString();
    }
}
