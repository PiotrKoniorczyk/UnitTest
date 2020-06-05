package testing;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderBackUpTest {

    private static OrderBackUp orderBackUp;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackUp = new OrderBackUp();
        orderBackUp.createFile();
    }

    @BeforeEach
    void appendAtTheStartOfTheLine() throws IOException {
        orderBackUp.getWriter().append("New order: ");
    }

    @AfterEach
    void appendAtTheEndOfTheLine() throws IOException {
        orderBackUp.getWriter().append(" backed up");
    }

    @Test
    void BackupOrderWithOneMeal() throws IOException {
        //given
        Meal meal = new Meal(7,"Fries");
        Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackUp.backupOrder(order);

        //then
        System.out.println("Order "+ order.toString() + " backed up");
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackUp.closeFile();
    }

}