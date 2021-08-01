package academy.pocu.comp3500.lab11;
import academy.pocu.comp3500.lab11.data.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BallBoy {
    public static List<Point> findPath(final Point[] points) {
        ArrayList<Point> pointArr = new ArrayList<>(points.length + 1);
        ArrayList<Point> mst = new ArrayList<>(pointArr.size());
        Arrays.sort(points);

        pointArr.add(new Point(0, 0));
        for (int i = 0; i < points.length; i++) {
            pointArr.add(points[i]);
        }
        DisjointSet set = new DisjointSet(pointArr);

        for (int i = 0; i < points.length; i++) {
            Point n1 = pointArr.get(i);
            Point n2 = pointArr.get(i + 1);
            if (!n1.equals(n2)) {
                mst.add(n1);
                set.union(n1, n2);
            }
        }

        if (!pointArr.get(points.length - 1).equals(pointArr.get(points.length))) {
            mst.add(pointArr.get(points.length));
        }

        /*
        for (int i = points.length - 1; i > 0; i--) {
            Point n1 = pointArr.get(i);
            Point n2 = pointArr.get(i - 1);
            if (mst.contains(n1)) {
                continue;
            }

            if (!n1.equals(n2)) {
                mst.add(n1);
                set.union(n1, n2);
            }
        }
           */
        mst.add(pointArr.get(0));
        return mst;
    }
}