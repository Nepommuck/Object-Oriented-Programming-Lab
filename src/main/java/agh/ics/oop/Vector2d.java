package agh.ics.oop;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "("+this.x+", "+this.y+")";
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(
                max(this.x, other.x),
                max(this.y, other.y)
        );
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(
                min(this.x, other.x),
                min(this.y, other.y)
        );
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(
                this.x + other.x,
                this.y + other.y
        );
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(
                this.x - other.x,
                this.y - other.y
        );
    }

    public boolean equals(Object other) {
        if (other.getClass() != this.getClass())
            return false;
        Vector2d oth = (Vector2d) other;
        return this.x == oth.x && this.y == oth.y;
    }

    public static void main(String[] arg) {
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(1, 10);
        out.println(v1.upperRight(v2).toString());
        out.println(v1.lowerLeft(v2).toString());

        out.println(v1.getClass());

        out.println(v1.equals(v2));
        Vector2d v3 = new Vector2d(1, 10);
        out.println(v2.equals(v3));
    }
}
