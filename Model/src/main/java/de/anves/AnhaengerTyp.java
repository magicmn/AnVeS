package de.anves;

public enum AnhaengerTyp {
    EINACHSER(1), TANDEMACHSER(2), KOFFERANHAENGER(3), MOTORRADANHAENGER(4);

    public final long id;

    AnhaengerTyp(long id) {
        this.id = id;
    }

    public static AnhaengerTyp getById(long id) {
        for (AnhaengerTyp i : AnhaengerTyp.values()) {
            if (i.id == id) {
                return i;
            }
        }
        return null;
    }
}
