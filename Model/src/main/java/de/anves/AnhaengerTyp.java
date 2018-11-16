package de.anves;

public enum AnhaengerTyp {
    EINACHSER(0), TANDEMACHSER(1), KOFFERANHAENGER(2), MOTORRADANHAENGER(3);

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

    public static AnhaengerTyp getByString(String string) {
        for (AnhaengerTyp i : AnhaengerTyp.values()) {
            if (i.name().equalsIgnoreCase(string)) {
                return i;
            }
        }
        return null;
    }

}
