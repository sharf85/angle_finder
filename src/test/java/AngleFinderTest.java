import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;


public class AngleFinderTest {

    private final double epsilon = 1e-5;

    private AngleFinder angleFinder;

    @Before
    public void init() {
        angleFinder = new AngleFinder();
    }

    @Test
    public void testFindAngle_twoTreesToFourNumbers() {
        List<Tree> trees = Arrays.asList(new Tree(Math.sqrt(3), 1), new Tree(1, Math.sqrt(3)));

        assertEquals(Math.PI * 1 / 6, angleFinder.findAngle(trees, Math.PI / 2), epsilon);
    }

    @Test
    public void testFindAngle_fourTreesTosixNumbers() {
        List<Tree> trees = Arrays.asList(
                new Tree(Math.sqrt(3), 1),
                new Tree(1, Math.sqrt(3)),
                new Tree(1, -Math.sqrt(3)),
                new Tree(Math.sqrt(3), -1));

        assertEquals(Math.PI * 5 / 3, angleFinder.findAngle(trees, Math.PI / 2), epsilon);
    }

    @Test
    public void testFindAngle_nineTreesToElevenNumbers() {
        List<Tree> trees = Arrays.asList(
// points from the right
                new Tree(Math.sqrt(3), 1),
                new Tree(1, -1),
                new Tree(1, -Math.sqrt(3)),
                new Tree(Math.sqrt(3), -1),
                new Tree(1, 0),
// points from the left
                new Tree(-Math.sqrt(3), 1),
                new Tree(-Math.sqrt(3), -1),
                new Tree(-1, -Math.sqrt(3)),
                new Tree(-1, 0)
        );

        assertEquals(Math.PI * 5 / 3, angleFinder.findAngle(trees, Math.PI / 2), epsilon);
    }

    @Test
    public void testTreeToAngle_angleFromFirstQuarter() {
        assertEquals(Math.PI / 4, angleFinder.treeToAngle(new Tree(1, 1)), epsilon);
    }

    @Test
    public void testTreeToAngle_halfPi() {
        assertEquals(Math.PI / 2, angleFinder.treeToAngle(new Tree(0, 1)), epsilon);
    }

    @Test
    public void testTreeToAngle_angleFromSecondQuarter() {
        assertEquals(Math.PI * 3 / 4, angleFinder.treeToAngle(new Tree(-1, 1)), epsilon);
    }

    @Test
    public void testTreeToAngle_Pi() {
        assertEquals(Math.PI, angleFinder.treeToAngle(new Tree(-1, 0)), epsilon);
    }

    @Test
    public void testTreeToAngle_angleFromThirdQuarter() {
        assertEquals(Math.PI * 5 / 4, angleFinder.treeToAngle(new Tree(-1, -1)), epsilon);
    }

    @Test
    public void testTreeToAngle_3over2Pi() {
        assertEquals(Math.PI * 3 / 2, angleFinder.treeToAngle(new Tree(0, -1)), epsilon);
    }

    @Test
    public void testTreeToAngle_angleFromForthQuarter() {
        assertEquals(Math.PI * 7 / 4, angleFinder.treeToAngle(new Tree(1, -1)), epsilon);
    }

    @Test
    public void testTreeToAngle_zero() {
        assertEquals(0d, angleFinder.treeToAngle(new Tree(1, 0)), epsilon);
    }

    @Test
    public void testConvertTreesToLine_oneTree_oneNumber() {
        List<Tree> trees = Arrays.asList(new Tree(-1, 1));
        List<Double> expected = Arrays.asList(Math.PI * 3 / 4);

        assertEquals(expected, angleFinder.convertTreesToLine(trees, Math.PI / 2));
    }

    @Test
    public void testConvertTreesToLine_oneTree_twoNumbers() {
        List<Tree> trees = Arrays.asList(new Tree(1, 1));
        List<Double> expected = Arrays.asList(Math.PI * 1 / 4, Math.PI * 9 / 4);

        assertEquals(expected, angleFinder.convertTreesToLine(trees, Math.PI / 2));
    }

    @Test
    public void testConvertTreesToLine_twoTrees_threeNumbers() {
        List<Tree> trees = Arrays.asList(new Tree(1, 1), new Tree(-1, 1));
        List<Double> expected = Arrays.asList(Math.PI * 1 / 4, Math.PI * 3 / 4, Math.PI * 9 / 4);

        assertEquals(expected, angleFinder.convertTreesToLine(trees, Math.PI / 2));
    }
}
