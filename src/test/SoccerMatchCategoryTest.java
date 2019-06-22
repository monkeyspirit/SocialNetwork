 import main.model.soccerMatchCategory.SoccerMatchEventBuilder;
import org.junit.*;
import main.model.soccerMatchCategory.SoccerMatchCategory;
import main.model.soccerMatchCategory.SoccerMatchEvent;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class SoccerMatchCategoryTest {

    @Test
    public void shouldGetEvents() {
        assertNotNull(new SoccerMatchCategory().getEvents());
    }


    @Test
    public void shouldFindEventByName() {
        SoccerMatchEvent e = (SoccerMatchEvent) new SoccerMatchEventBuilder()
                .title("titolo")
                .date(LocalDate.of(2025,7,20))
                .time(LocalTime.of(12,0))
                .place("")
                .individualTee(Float.valueOf(0))
                .numOfParticipants(1)
                .registrationDeadline(LocalDate.of(2025,7,10))
                .build();

        SoccerMatchCategory s = new SoccerMatchCategory();
        s.addEvent(e);
        assertEquals("titolo", s.findEventByName("titolo").getTitle().getValue());
    }


}