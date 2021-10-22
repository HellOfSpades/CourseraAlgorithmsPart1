
public class LineSegment {
    private Point p,q;

    public LineSegment(Point p, Point q){        // constructs the line segment between points p and q
        this.p = p;
        this.q = q;
    }
    public   void draw(){                        // draws this line segment

    }
    public String toString(){                    // string representation
        return p.toString()+" "+q.toString();
    }
}