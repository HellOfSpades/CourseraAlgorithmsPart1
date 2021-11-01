

public class BruteCollinearPointsOld {
    private LineSegment[] segments;

    public BruteCollinearPointsOld(Point[] points) {    // finds all line segments containing 4 points
        //throw exceptions if input is faulty
        if(points==null)throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if(points[i]==null)throw new IllegalArgumentException();
            for (int j = 0; j < i; j++) {
                if(points[i].compareTo(points[j])==0)throw new IllegalArgumentException();
            }
        }

        //making the segments and holding the current index for the next segment in "current"
        segments = new LineSegment[points.length];

        //========================================calculating the lines===================================================================

        calculateLines(points);

        //=================================================================================================================================
    }
    public           int numberOfSegments() {        // the number of line segments
        return segments.length;
    }
    public LineSegment[] segments() {                // the line segments
        return segments;
    }

    private void sort(PointSlope[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i-1; j >= 0 ; j--) {
                if(array[j+1].slope<array[j].slope){
                    PointSlope temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    //class to be able to hold the points and their slopes together in one array during calculations
    private class PointSlope{
        public Point point;
        public double slope;

        public PointSlope(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }
    }
    private void calculateLines(Point[] points){

        int current = 0;
        //filling as many segments as possible
        for (int i = 0; i < points.length; i++) {

            //calculating all the slopes between the current point, and all other points
            PointSlope[] slopes = new PointSlope[points.length-1];
            int n = 0;//current index for the slope array
            for (int j = 0; j < points.length; j++) {
                if(j!=i){
                    slopes[n] = new PointSlope( points[j], points[i].slopeTo(points[j]));
                    n++;
                }
            }
            sort(slopes);//sort the slopes
            //put the points into a line segment if they lie on the same slope
            //but only if point[i] is the smallest out of all of them
            for (int j = 0; j < slopes.length-2; j++) {
                //checking if there are 3 same slopes in a row
                if(slopes[j].slope==slopes[j+2].slope){

                    Point maxx = points[i];
                    boolean iIsMin = true;

                    //======problem area, must fix it to access the points at correct indexes===
                    //checking all points with the same slope
                    for (int k = j; k <= j+2; k++) {
                        if(slopes[k].point.compareTo(points[i])<0){
                            iIsMin = false;
                            break;
                        }
                        else if(slopes[k].point.compareTo(maxx)>0){
                            maxx = slopes[k].point;
                        }


                    }
                    if(iIsMin){
                        //making a new line segment at current position, and incrementint current position
                        segments[current] = new LineSegment(points[i],maxx);
                        current++;
                    }

                }
            }
        }
        //removing extra elements
        LineSegment[] polishedsegments = new LineSegment[current];
        for (int i = 0; i < current; i++) {
            polishedsegments[i] = segments[i];
        }
        segments = polishedsegments;
    }

//    public static void main(String[] args){
//
//        Point[] points = new Point[8];
////        points[4] = new Point(-1,0);
////        points[0] = new Point( 0,1);
////        points[1] = new Point( 1,2);
////        points[2] = new Point( 2,3);
////        points[3] = new Point( 3,4);
//        points[0] = new Point(0,0);
//        points[1] = new Point(1,6);
//        points[2] = new Point(2,1);
//        points[3] = new Point(3,3);
//        points[4] = new Point(4,4);
//        points[5] = new Point(2,2);
//        points[6] = new Point(-1,-6);
//        points[7] = new Point(2,12);
//
//        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
//
//        for (int i = 0; i < bcp.numberOfSegments(); i++) {
//            System.out.println(bcp.segments[i]);
//        }
//    }
}