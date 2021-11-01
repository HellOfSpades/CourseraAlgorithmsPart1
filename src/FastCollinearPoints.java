public class FastCollinearPoints {
    private LineSegment[] segments;
    private int segmentsSize = 0;
    //the length of the segments needed to be found
    final private int segmentLength = 4;

    //Api Methods
    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        //throw exceptions if input is faulty
        checkArgumentLegality(points);
        //we will dellete the extra elements later
        segments = new LineSegment[points.length];
        Point[] ourPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            ourPoints[i] = points[i];
        }
        findSegments(ourPoints);
        trimSegments();
    }
    public           int numberOfSegments(){        // the number of line segments
        return segmentsSize;
    }
    public LineSegment[] segments() {                // the line segments
        return segments;
    }



    //Inner Methods
    //checks if the input points are a legal input, throws and exception if faulty
    private void checkArgumentLegality(Point[] points){
        if(points==null)throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if(points[i]==null)throw new IllegalArgumentException();
            for (int j = 0; j < i; j++) {
                if(points[i].compareTo(points[j])==0)throw new IllegalArgumentException();
            }
        }
    }
    //removes extra elements
    private void trimSegments(){
        LineSegment[] trimedSegments = new LineSegment[segmentsSize];
        for (int i = 0; i < segmentsSize; i++) {
            trimedSegments[i] = segments[i];
        }
        segments = trimedSegments;
    }
    //fill the segments array with all possible segments from points
    private void findSegments(Point[] points){
        //sort the points array so we don't need to see which points are at ends later
        points = sortPoints(points);

        //itterate through each point to check their slopes compared to others
        for (int i = 0; i < points.length-segmentLength+1; i++) {
            //making an array of PointData objects, that store the slope from the base point to them, as well as a refference to their point
            PointData[] pointData = new PointData[points.length-i-1];
            for (int j = i+1; j < points.length; j++) {
                pointData[j-i-1] = new PointData(points[j],points[i].slopeTo(points[j]));
            }
            //sorting the array so that points with same slopes are next to one another
            pointData = sortPointData(pointData);

            for (int j = 0; j < pointData.length-segmentLength+2; j++) {
                if(pointData[j].slope==pointData[j+segmentLength-2].slope){
                    segments[segmentsSize] = new LineSegment(points[i],pointData[j+segmentLength-2].point);
                    segmentsSize++;
                    break;
                }
            }


        }
    }
    private class PointData{
        public Point point;
        public double slope;

        public PointData(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }
    }

    //methods for sorting
    private PointData[] sortPointData(PointData[] pointData){
        PointData[] aux = new PointData[pointData.length];
        sortPointData(pointData,aux,0, pointData.length-1);
        return pointData;
    }
    private void sortPointData(PointData[] pointData, PointData[] aux, int low, int high){
        if(high<=low)return;
        int mid = low + (high-low)/2;
        sortPointData(pointData,aux,low,mid);
        sortPointData(pointData,aux,mid+1,high);
        mergePointData(pointData,aux,low,mid,high);
    }
    private void mergePointData(PointData[] pointData, PointData[] aux,int low, int mid, int high){
        for (int i = 0; i < pointData.length; i++) {
            aux[i] = pointData[i];
        }
        int l = low;
        int m = mid+1;
        for (int i = low; i <= high; i++) {
            if(l>mid){
                pointData[i] = aux[m++];
            }else if(m>high){
                pointData[i] = aux[l++];
            }else if(aux[l].slope<aux[m].slope){
                pointData[i] = aux[m++];
            }else{
                pointData[i] = aux[l++];
            }

        }
    }

    private Point[] sortPoints(Point[] points){
        Point[] aux = new Point[points.length];
        sortPoints(points,aux,0, points.length-1);
        return points;
    }
    private void sortPoints(Point[] points, Point[] aux, int low, int high){
        if(high<=low)return;
        int mid = low + (high-low)/2;
        sortPoints(points,aux,low,mid);
        sortPoints(points,aux,mid+1,high);
        mergePoints(points,aux,low,mid,high);
    }
    private void mergePoints(Point[] points, Point[] aux, int low, int mid, int high){
        for (int i = 0; i < points.length; i++) {
            aux[i] = points[i];
        }
        int l = low;
        int m = mid+1;
        for (int i = low; i <= high; i++) {
            if(l>mid){
                points[i] = aux[m++];
            }else if(m>high){
                points[i] = aux[l++];
            }else if(aux[l].compareTo(aux[m])<0){
                points[i] = aux[m++];
            }else{
                points[i] = aux[l++];
            }

        }
    }
}
