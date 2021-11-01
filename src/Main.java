
public class Main {
    public static void main(String[] args) {

        Point[] points = new Point[9];
        points[0] = new Point(1,2);
        points[1] = new Point(-5,10);
        points[2] = new Point(3,-2);
        points[3] = new Point(-3,10);
        points[4] = new Point(1,7);
        points[5] = new Point(0,2);
        points[6] = new Point(4,-4);
        points[7] = new Point(-1,6);
        points[8] = new Point(0,4);

        //lines- (-3,10),(-1,6),(0,4),(1,2),(3,-2),(4,-4)

        FastCollinearPoints bcp = new FastCollinearPoints(points);

        for (int i = 0; i < bcp.numberOfSegments(); i++) {
            System.out.println(bcp.segments()[i]);
        }


//        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//
//        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//
//        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }

}
