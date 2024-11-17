import com.example.models.Ticket;
import com.example.repository.TicketRepository;
import com.example.services.TicketService;
import models.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({"0, 0.000", "100, 3.500", "500, 4.200", "700, 5.300"})
    public void testMaxWeightAccordingEventCode(short eventCode, float expectedWeight) {
        assertEquals(expectedWeight, ticketService.maxWeightAccordingEventCode(eventCode));
    }

    @Test
    public void testDateEvent() {
        assertEquals(LocalDateTime.now().getDayOfYear(), ticketService.dateEvent((short) 0).getDayOfYear());
        assertEquals(LocalDateTime.of(2024, 3, 15, 18, 20), ticketService.dateEvent((short) 100));
        assertEquals(LocalDateTime.of(2024, 9, 27, 16, 30), ticketService.dateEvent((short) 500));
        assertEquals(LocalDateTime.of(2024, 12, 5, 20, 30), ticketService.dateEvent((short) 700));
    }

    @Test
    public void testAddTicket() {
        Ticket ticket = new Ticket();
        ticket.setId((long) 2);
        ticket.setTicketType(TicketType.DAY);
        ticketService.addTicket(ticket);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testGetTicketById() {
        Ticket ticket = new Ticket();
        ticket.setId((long) 2);
        when(ticketRepository.findTicketById(2)).thenReturn(ticket);
        Ticket found = ticketService.getTicketById(2);
        assertNotNull(found);
        assertEquals(2, found.getId());
        when(ticketRepository.findTicketById(1)).thenReturn(null);
        assertThrows(NullPointerException.class, () -> ticketService.getTicketById(1));
    }
}
