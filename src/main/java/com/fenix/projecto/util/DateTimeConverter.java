package com.fenix.projecto.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateTimeConverter {

    public static Date convertToDate(String date) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException("A data não pode ser nula.", new Throwable("Data inválida"));
        }
        var pattern = Pattern.compile("(19|20)[0-9]{2}-(0[1-9])?(1[1-2])?-(0[1-9])?([1-2][0-9])?(3[0-1])?");
        var matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("A data tem de ser no formato AAAA-MM-DD.", new Throwable("Data inválida"));
        }

        String[] fields = date.split("-");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(fields[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(fields[1]) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fields[2]));
        return cal.getTime();
    }
}
