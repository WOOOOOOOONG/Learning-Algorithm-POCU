package academy.pocu.comp3500.lab11;
import academy.pocu.comp3500.DisjointSet;
import academy.pocu.comp3500.lab11.data.Point;
import java.util.ArrayList;
import java.util.List;


public class BallBoy {
    public static List<Point> findPath(final Point[] points) {
        ArrayList<Point> pointArr = new ArrayList<>(points.length + 1);
        ArrayList<Point> mst = new ArrayList<>(pointArr.size());


        pointArr.add(new Point(0, 0));
        for (int i = 0; i < points.length; i++) {
            pointArr.add(points[i]);
        }
        DisjointSet set = new DisjointSet(pointArr);

        for (int i = 0; i < pointArr.size() - 1; i += 2) {
            Point n1 = pointArr.get(i);
            Point n2 = pointArr.get(i + 1);

            if (!n1.equals(n2)) {
                mst.add(n1);
                mst.add(n2);
                set.union(n1, n2);
            }
        }

        mst.add(pointArr.get(0));
        return mst;
    }
}