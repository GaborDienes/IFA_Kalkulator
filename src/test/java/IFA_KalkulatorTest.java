import model.IFA_Kalkulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IFA_KalkulatorTest {
    IFA_Kalkulator undertest;

    @BeforeEach
    void setUp() {

        undertest = new IFA_Kalkulator();
    }

    @Test
    void testAltalanosDatum() throws IOException {
        assertEquals("4",  undertest.countDays(12,16));
    }

    @Test
    void testFelcsereltDatum() throws IOException {
        assertEquals("0",  undertest.countDays(16,1));
    }

    @Test
    void testNormalIfa() throws IOException {
        assertEquals(500,  undertest.szamolIFAosszeg(1,2,2,0,1,0));
    }

    @Test
    void testIfaSokFiatal() throws IOException {
        assertEquals(3000,  undertest.szamolIFAosszeg(5,7,10,7,0,0));
    }

    @Test
    void testNormalSzallasDij() throws IOException{
        assertEquals(36000,undertest.szamolSzallasDij(1,3,3,0,6000));
    }

    @Test
    void testMenekultSzallasDij() throws IOException{
        assertEquals(0,undertest.szamolSzallasDij(1,3,3,3,6000));
    }



}
