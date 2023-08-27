
import org.junit.Assert;
import org.junit.Test;

public class PrimAlgorithmTest {

    PrimAlgorithm pm = new PrimAlgorithm();

    @Test(expected = IllegalArgumentException.class)
    public void pathIsNull() {
        String result = pm.findMST(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pathIsEmpty() {
        String result = pm.findMST("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileDoesntExist() {
        String result = pm.findMST("src/test/java/pl/edu/pw/ee/file.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphNotConnected() {
        String result = pm.findMST("src/files/not_connected.txt");
    }
    
    @Test
    public void OneConnection() {
        String result = pm.findMST("src/files/one_con.txt");
        Assert.assertEquals("A_3_B", result);
    }

    @Test
    public void TwoConnections() {
        String result = pm.findMST("src/files/two_con.txt");
        Assert.assertEquals("A_3_B|A_5_C", result);
    }

    @Test
    public void CompleteGraph() {
        String result = pm.findMST("src/files/graph_complete.txt");
        Assert.assertEquals("A_3_B|B_5_C", result);
    }

    @Test
    public void OneStartingLetter() {
        String result = pm.findMST("src/files/one_starting_letter.txt");
        //Assert.assertEquals("A_3_B|A_5_C", result);
        System.out.println(result);
    }

    @Test
    public void Star() {
        String result = pm.findMST("src/files/star.txt");
        //Assert.assertEquals("A_3_B|A_5_C", result);
        System.out.println(result);
    }

    @Test
    public void Circle() {
        String result = pm.findMST("src/files/circle.txt");
        //Assert.assertEquals("A_3_B|A_5_C", result);
        System.out.println(result);
    }

    @Test
    public void equalWeights() {
        String result = pm.findMST("src/files/equal_weight.txt");
        //Assert.assertEquals("A_3_B|A_5_C", result);
        System.out.println(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void LineTooLong() {
        String result = pm.findMST("src/files/line_too_much_el.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void LineTooShort() {
        String result = pm.findMST("src/files/line_not_complete.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void LineWrongPattern() {
        String result = pm.findMST("src/files/line_wrong_occurrence.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphIsEmpty() {
        String result = pm.findMST("src/files/empty.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void withLoops() {
        String result = pm.findMST("src/files/with_loops.txt");
    }

    @Test
    public void Test() {
        String result = pm.findMST("src/files/correct_small_data.txt");
        Assert.assertEquals("A_3_B|B_1_C|C_1_D|D_7_E", result);
        System.out.println(result);
    }

}
