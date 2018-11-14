package de.anves.view.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public Date stringToDate(String dateAsString){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;
        try {
            date = sdf.parse(dateAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
