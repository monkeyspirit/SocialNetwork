package main.model.notifications;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class NotificationsBuilderTest {

    @Test
    public void shouldBuildReminder() {
        String eventTitle = "titolo";
        LocalDate eventDate = LocalDate.of(2019, 6, 25);
        LocalTime eventTime = LocalTime.of(12,0);
        String eventPlace = "luogo";
        Float eventTee = Float.valueOf(10);
        float eventExtras[] = {0, 0, 0};
        String reminder = NotificationsBuilder.buildReminder(
                eventTitle,
                eventDate,
                eventTime,
                eventPlace,
                eventTee,
                eventExtras).getNotificationMessage();

        String expectedReminder = "Ricordati che hai l'evento: " + eventTitle
                + " che si terra' " + eventDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY)
                + " " + eventDate.getDayOfMonth() + " " + eventDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALY)
                + " del "+eventDate.getYear()+" alle ore "+eventTime.getHour()+":"
                + eventTime.getMinute()+".\n"
                + "Il luogo di ritrovo e': " + eventPlace + ".\n"
                + "Ricordati di pagare: " + eventTee + " €.\n";

        assertEquals(expectedReminder, reminder);
    }
}