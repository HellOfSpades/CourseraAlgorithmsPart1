import java.util.Comparator;

public class Point implements Comparable<Point> {
    final private int x,y;

    public Point(int x, int y){// constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public   void draw(){// draws this point

    }
    public   void drawTo(Point that){// draws the line segment from this point to that point

    }
    public String toString(){// string representation
        return "("+x+":"+y+")";
    }
    public int compareTo(Point that){// compare two points by y-coordinates, breaking ties by x-coordinates
        int output = this.y-that.y;
        if(output==0)output = this.x-that.x;
        return output;
    }
    public double slopeTo(Point that){// the slope between this point and that point

        if(this.compareTo(that)==0)return Double.POSITIVE_INFINITY;

        double value = (double)(that.y-this.y)/(double)(that.x-this.x);
        if(value==Double.NEGATIVE_INFINITY)value = Double.POSITIVE_INFINITY;
        if(value==-0)value = 0;
        return value;
    }
    public Comparator<Point> slopeOrder(){// compare two points by slopes they make with this point
        return  new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (int)(slopeTo(o1)-slopeTo(o2));
            }
        };
    }
}
