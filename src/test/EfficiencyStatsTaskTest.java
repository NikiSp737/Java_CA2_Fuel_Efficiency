// Name: Niki Spasov, Niki Mardari
// Student number: B00161794, B00159642
// Email Address: B00161794@mytudublin.ie, B00159642@mytudublin.ie
// Date: 14/03/2026
// Module: ELTC H3028 Java and Algorithms
// Purpose of code: Junit testing for non interrupted thread operation, min, max, average calculations 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class EfficiencyStatsTaskTest {

    @Test
    public void statsThread_computesMinMaxAvgCorrectly() throws InterruptedException {
        List<MileageRecord> records = Mileage.readRecords("data/mileage_tiny.txt");
        Mileage.sortRecords(records);
        Mileage.computeEfficiencies(records);

        EfficiencyStatsTask task = new EfficiencyStatsTask(records);
        Thread t = new Thread(task);
        t.start();
        t.join();

        assertEquals(6.765586034912719, task.getMin(), 1e-9);
        assertEquals(6.883610451306413, task.getMax(), 1e-9);
        assertEquals(6.8245589519113805, task.getAvg(), 1e-9);
    }
}
