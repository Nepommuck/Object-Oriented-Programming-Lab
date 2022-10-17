package agh.ics.oop;

public enum MapDirection {
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
}
