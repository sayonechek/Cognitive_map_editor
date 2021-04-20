package graph;

import figures.Point;

public class Arc {

    public Point sourcePoint;
    public Point targetPoint;
    public boolean isDirected;
    public String weight = "";

    public Arc(Point sourcePoint, Point targetPoint, boolean isDirected) {
        this.isDirected = isDirected;
        this.sourcePoint = sourcePoint;
        this.targetPoint = targetPoint;
    }

    public Arc(Point sourcePoint, Point targetPoint, boolean isDirected, String weight) {
        this.isDirected = isDirected;
        this.sourcePoint = sourcePoint;
        this.targetPoint = targetPoint;
        this.weight = weight;
    }
}
