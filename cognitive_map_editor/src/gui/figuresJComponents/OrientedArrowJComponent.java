package gui.figuresJComponents;

import figures.Circle;
import figures.Point;

import java.awt.*;
import java.awt.geom.Line2D;

public class OrientedArrowJComponent extends NotOrientedArrowJComponent {

    public OrientedArrowJComponent(Point sourcePoint, Point targetPoint) {
        super(sourcePoint, targetPoint);
        arrow.isDirected = true;
    }

    public OrientedArrowJComponent(Point sourcePoint, Point targetPoint, String weight) {
        super(sourcePoint, targetPoint, weight);
        arrow.isDirected = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(color);

        Point targetPoint = arrow.targetPoint;
        Point sourcePoint = arrow.sourcePoint;
        int radius = Circle.radius;

        double k = (double) (targetPoint.y - sourcePoint.y) / (targetPoint.x - sourcePoint.x);

        double a = Math.sqrt(Math.pow(radius, 2) / (Math.pow(k, 2) + 1));
        double b = a * k;
        int sourceDy, sourceDx, targetDy, targetDx;

        if (targetPoint.x > sourcePoint.x) {
            sourceDy = (int) (sourcePoint.y + b);
            sourceDx = (int) (sourcePoint.x + a);
            targetDy = (int) (targetPoint.y - b);
            targetDx = (int) (targetPoint.x - a);
        } else if (targetPoint.x < sourcePoint.x) {
            sourceDy = (int) (sourcePoint.y - b);
            sourceDx = (int) (sourcePoint.x - a);
            targetDy = (int) (targetPoint.y + b);
            targetDx = (int) (targetPoint.x + a);
        } else {
            sourceDy = targetPoint.y > sourcePoint.y ? sourcePoint.y + radius : sourcePoint.y - radius;
            sourceDx = sourcePoint.x;
            targetDy = sourcePoint.y > targetPoint.y ? targetPoint.y + radius : targetPoint.y - radius;
            targetDx = targetPoint.x;
        }
        if (!isFinish) {
            targetDx = targetPoint.x;
            targetDy = targetPoint.y;
        }

        drawArrowLine(g2, sourceDx, sourceDy, targetDx, targetDy, 10, 5);
        line = new Line2D.Double(sourcePoint.x, sourcePoint.y, targetPoint.x, targetPoint.y);


        int differenceY = sourcePoint.y > targetPoint.y ? 10 : -10;
        int differenceX = sourcePoint.x > targetPoint.x ? -10 : 10;

        int midX = (targetPoint.x + sourcePoint.x) / 2 + differenceX;
        int midY = (targetPoint.y + sourcePoint.y) / 2 + differenceY;

        g2.setColor(Color.red);


        g2.drawString(weight, midX, midY);

    }

    private void drawArrowLine(Graphics2D graphics2D, int x1, int y1, int x2, int y2, int arrowWidth, int arrowHeight) {
        int length = x2 - x1, height = y2 - y1;
        double distance = Math.sqrt(length * length + height * height);
        double xFirst = distance - arrowWidth, xSecond = xFirst, yFirst = arrowHeight, ySecond = -arrowHeight, x;
        double sin = height / distance, cos = length / distance;

        x = xFirst * cos - yFirst * sin + x1;
        yFirst = xFirst * sin + yFirst * cos + y1;
        xFirst = x;

        x = xSecond * cos - ySecond * sin + x1;
        ySecond = xSecond * sin + ySecond * cos + y1;
        xSecond = x;

        int[] xpoints = {x2, (int) xFirst, (int) xSecond};
        int[] ypoints = {y2, (int) yFirst, (int) ySecond};
        line = new Line2D.Double(x1, y1, x2, y2);
        graphics2D.draw(line);
        graphics2D.fillPolygon(xpoints, ypoints, 3);
    }
}
