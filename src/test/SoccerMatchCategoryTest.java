package test;

import org.junit.Test;
import versione1.Field;
import versione1.SoccerMatchCategory;
import versione1.SoccerMatchEvent;

import static org.junit.Assert.*;

public class SoccerMatchCategoryTest {

    @Test
    public void shouldGetEvents() {
        assertNotNull(new SoccerMatchCategory().getEvents());
    }

    @Test
    public void shouldFindEventByName() {
        SoccerMatchEvent e = new SoccerMatchEvent();
        e.setTitle("titolo");
        SoccerMatchCategory s = new SoccerMatchCategory();
        s.addEvent(e);
        assertEquals("titolo", s.findEventByName("titolo").getTitle().getValue());
    }


}