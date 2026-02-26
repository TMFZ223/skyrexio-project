package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PeriodGeneratorText {
    private static final Locale ru = new Locale("ru");
    private static final DateTimeFormatter FULL_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, YY", ru);
    private static final LocalDate CURRENT_DATE = LocalDate.now();
    private static final String PARTIAL_TEXT = "Дата\n";

    public static String generateTextFromPeriod1Week() {
        LocalDate inputDate = CURRENT_DATE.minusWeeks(1);
        String fullText = PARTIAL_TEXT.concat("%s - %s").formatted(FULL_DATE_FORMATTER.format(inputDate), FULL_DATE_FORMATTER.format(CURRENT_DATE));
        return fullText;
    }

    public static String generateTextFromPeriod1Month() {
        LocalDate inputDate = CURRENT_DATE.minusMonths(1);
        String fullText = PARTIAL_TEXT.concat("%s - %s").formatted(FULL_DATE_FORMATTER.format(inputDate), FULL_DATE_FORMATTER.format(CURRENT_DATE));
        return fullText;
    }

    public static String generateTextFromPeriod3Month() {
        LocalDate inputDate = CURRENT_DATE.minusMonths(3);
        String fullText = PARTIAL_TEXT.concat("%s - %s").formatted(FULL_DATE_FORMATTER.format(inputDate), FULL_DATE_FORMATTER.format(CURRENT_DATE));
        return fullText;
    }
}
