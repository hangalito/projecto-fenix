package com.fenix.projecto.converter;

import com.fenix.projecto.repository.SchoolRepository;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "schoolConverter", managed = true)
public class SchoolConverter implements Converter<Object> {

    @Inject
    SchoolRepository repo;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) return null;
        return repo.findByName(value).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        return object.toString();
    }
}
