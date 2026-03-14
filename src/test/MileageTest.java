// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 14/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: Junit testing for readRecords, sortRecords and computeEfficiencies methods 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MileageTest {

    @Test
    public void readRecords_readsFourRecordsFromTinyFile() {
        List<MileageRecord> records = Mileage.readRecords("data/mileage_tiny.txt");

        assertEquals(4, records.size());
    }

    @Test
    public void sortRecords_sortsByDateAscending() {
        List<MileageRecord> records = Mileage.readRecords("data/mileage_tiny.txt");
        Mileage.sortRecords(records);

        assertEquals("2025-01-02", records.get(0).getDate().toString());
        assertEquals("2025-01-09", records.get(1).getDate().toString());
        assertEquals("2025-01-16", records.get(2).getDate().toString());
        assertEquals("2025-01-23", records.get(3).getDate().toString());
    }

    @Test
    public void computeEfficiencies_calculatesExpectedValues() {
        List<MileageRecord> records = Mileage.readRecords("data/mileage_tiny.txt");
        Mileage.sortRecords(records);
        Mileage.computeEfficiencies(records);

        assertTrue(Double.isNaN(records.get(0).getEfficiency()));
        assertEquals(6.824480369515011, records.get(1).getEfficiency(), 1e-9);
        assertEquals(6.883610451306413, records.get(2).getEfficiency(), 1e-9);
        assertEquals(6.765586034912719, records.get(3).getEfficiency(), 1e-9);
    }
}
