package testing.order;

import org.junit.jupiter.api.Test;
import testing.order.Order;
import testing.order.OrderBackUp;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingAFileFirstShouldThrowException() throws IOException {
        //given
        OrderBackUp orderBackUp = new OrderBackUp();
        //then
        assertThrows(IOException.class, ()->orderBackUp.backupOrder(new Order() ));
        //then

    }
}
