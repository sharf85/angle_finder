import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AngleFinder {

    /**
     * The method finds the angle so that as many trees as possible would be visible from the point O
     *
     * @param trees - a list of points with coordinates x, y. At least one of the coordinates has to be nonzero
     * @param alpha - a viewing width (can be from 0 to 2 * Pi) - radians
     * @return the angle between OX and the view - from 0 to 2 * Pi - radians
     */
    public double findAngle(List<Tree> trees, double alpha) {
        List<Double> numbers = convertTreesToLine(trees, alpha);

        LinkedList<Double> queue = new LinkedList<>();
        double res = 0;
        int maxTreesNum = 0;

        for (Double number : numbers) {
            queue.add(number);

            while (number - queue.getFirst() > alpha)
                queue.removeFirst();

            int queueSize = queue.size();
            if (queueSize > maxTreesNum) {
                maxTreesNum = queueSize;
                res = queue.getFirst();
            }

        }

        return res;
    }

    /**
     * The method maps points on Cartesian Plane to the line of Real Numbers and sorts. Every point
     * corresponds to its angle from OX. In addition, we append the points with angles less than alpha so that
     * the point maps to its angle + 2 * Pi. Meaning, such points will be present in the list twice, as its
     * angle and as its angle plus 2 * Pi
     *
     * @param trees points on the Cartesian Plane
     * @param alpha the angle of the view
     * @return a sorted list of the mapped trees (points)
     */
    protected List<Double> convertTreesToLine(List<Tree> trees, double alpha) {
        return trees.stream()
                .flatMap(tree -> {
                    double angle = treeToAngle(tree);
                    return angle < alpha ? Stream.of(angle, angle + 2 * Math.PI) : Stream.of(angle);
                })
                .sorted()
                .collect(Collectors.toList());
    }


    /**
     * The method maps a point on the Cartesian plane to the number, which is its angle in Polar coordinates
     *
     * @param tree a point
     * @return the angle from 0 to 2 * Pi
     */
    protected double treeToAngle(Tree tree) {
        double res = Math.atan2(tree.y, tree.x);
        return res >= 0 ? res : res + 2 * Math.PI;
    }


}
