package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class formateDate {

    public static String getDate(String inputDate) {
        String outputDate = "";
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            outputDate = date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date");
        }
        return outputDate;
    }

    public static String getReverseDate(String inputDate) {
        String outputDate = "";
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(inputDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            outputDate = date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date");
        }

        return outputDate;
    }

}
