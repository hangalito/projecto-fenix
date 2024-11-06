package com.fenix.projecto.converter;

import com.fenix.projecto.model.Teacher;
import com.fenix.projecto.repository.TeacherRepository;
import com.fenix.projecto.util.Logger;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "teacherConverter", managed = true)
public class TeacherConverter implements Converter<Teacher> {

    @Inject
    private TeacherRepository repo;

    @Override
    public Teacher getAsObject(FacesContext context, UIComponent component, String value) {
        Logger.info("Get the Teacher instance for " + value);
        if (value == null) {
            return null;
        }
        return repo.findByNameAndSurname(value)
                .orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Teacher value) {
        Logger.info("Get the String value of " + value);
        if (value == null) {
            return "";
        }
        return getFullName(value);
    }

    private static String getFullName(Teacher teacher) {
        StringBuilder fullName = new StringBuilder();
        fullName.append(teacher.getName()).append(" ");
        fullName.append(teacher.getSurname());
        return fullName.toString();
    }
}
