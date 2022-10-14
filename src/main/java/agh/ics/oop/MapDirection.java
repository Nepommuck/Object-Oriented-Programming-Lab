package agh.ics.oop;

import static java.lang.System.out;

enum MapDirection {
    SOUTH,
    WEST,
    NORTH,
    EAST;

    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
        };
    }

    public MapDirection next() {
        return MapDirection.values()[
                (this.ordinal() + 1) % 4
                ];
    }

    public MapDirection previous() {
        return MapDirection.values()[
                (this.ordinal() - 1 + 4) % 4
                ];
    }

    public Vector2d toUnitVector() {
        int x=0, y=0;
        switch (this) {
            case SOUTH -> y -= 1;
            case NORTH -> y += 1;
            case WEST -> x -= 1;
            case EAST -> x += 1;
        }
        return new Vector2d(x, y);
    }

    public static void main(String[] args) {
        MapDirection a = NORTH;
        MapDirection b;
        for (int i=0; i<10; i++) {
            out.println(a);
            out.println(a.toUnitVector());
            a = a.previous();
        }
        out.println((-4) % 4);
    }
}
