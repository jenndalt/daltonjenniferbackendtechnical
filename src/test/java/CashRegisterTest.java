import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashRegisterTest {
    CashRegister cashRegister;

    @Before
    public void setUp() {
        cashRegister = new CashRegister();
    }

    @Test
    public void showReturnsEmpty_whenCashRegisterStarts() {
        String expected = "$0 0 0 0 0 0 ";
        String actual = cashRegister.show();
        assertEquals(expected, actual);
    }

    @Test
    public void showReturnsValues_afterPutIsCalled() {
        String expected = "$68 1 2 3 4 5 ";
        cashRegister.put("put 1 2 3 4 5");
        String actual = cashRegister.show();
        assertEquals(expected, actual);
    }

    @Test
    public void showIsUpdated_afterTake() {
        String expected = "$0 0 0 0 0 0 ";
        cashRegister.put("put 1 2 3 4 5");
        cashRegister.take("take 1 2 3 4 5");
        String actual = cashRegister.show();
        assertEquals(expected, actual);
    }

    @Test
    public void correctChangeIsMadeAndBalanceUpdated() {
        cashRegister.put("put 1 2 3 4 5");
        cashRegister.change("change 19");
        String expected = "$49 1 1 2 2 5 ";
        String actual = cashRegister.show();
        assertEquals(expected, actual);
    }

    @Test
    public void changeNotAvailable_showError() {
        cashRegister.put("put 1 1 0 0 3" );
        String actual =  cashRegister.change("change 8");
        String expected = "sorry";
        assertEquals(expected, actual);
    }


    @Test
    public void changeAvailable_adjustAccordingly() {
        cashRegister.put("put 0 0 2 0 3" );
        String actual =  cashRegister.change("change 8");
        String expected = "$5 0 0 1 0 0 ";
        assertEquals(expected, actual);
    }
}