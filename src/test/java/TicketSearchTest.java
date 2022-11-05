import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketSearchTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    TicketByTimeAscComparator faster = new TicketByTimeAscComparator();

    Ticket ticket1 = new Ticket(1, 100, "SIP", "AAQ", 150);
    Ticket ticket2 = new Ticket(2, 200, "SIP", "SVO", 180);
    Ticket ticket3 = new Ticket(3, 300, "SVO", "KHV", 450);
    Ticket ticket4 = new Ticket(4, 400, "OVB", "VVO", 240);
    Ticket ticket5 = new Ticket(5, 100, "SVO", "OVB", 240);


    Ticket ticket01 = new Ticket(10, 100000, "SIP", "VVO", 150);
    Ticket ticket02 = new Ticket(20, 20000, "SIP", "VVO", 180);
    Ticket ticket03 = new Ticket(30, 3000, "SIP", "VVO", 450);
    Ticket ticket04 = new Ticket(40, 10, "SIP", "VVO", 240);
    Ticket ticket05 = new Ticket(50, 10, "SIP", "VVO", 240);

    @Test
    void saveTest() {
        repo.save(ticket1);

        Ticket[] expected = {ticket1};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void remove1Test() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        repo.removeById(3);

        Ticket[] expected = {ticket1, ticket2, ticket4, ticket5};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void remove2Test() {
        repo.save(ticket1);
        repo.removeById(1);

        Ticket[] expected = {};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void findTicket1Test() {
        repo.save(ticket01);
        repo.save(ticket05);
        repo.save(ticket02);
        repo.save(ticket04);
        repo.save(ticket03);

        Ticket[] expected = {ticket05, ticket04, ticket03, ticket02, ticket01};
        Ticket[] actual = manager.findAll("SIP", "VVO");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findTicket2Test() {
        repo.save(ticket01);

        Ticket[] expected = {ticket01};
        Ticket[] actual = manager.findAll("SIP", "VVO");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    void findTicketTimeTest() {
        repo.save(ticket01);

        Ticket[] expected = {ticket01};
        Ticket[] actual = manager.findAll("SIP", "VVO", faster);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findTicketTime2Test() {
        repo.save(ticket01);
        repo.save(ticket05);
        repo.save(ticket02);
        repo.save(ticket04);
        repo.save(ticket03);

        Ticket[] expected = {ticket01, ticket02, ticket05, ticket04, ticket03};
        Ticket[] actual = manager.findAll("SIP", "VVO", faster);

        Assertions.assertArrayEquals(expected, actual);
    }


}
