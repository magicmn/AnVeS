package de.anves;

public enum AnhaengerTypEnum {
    EINACHSER(1), TANDEMACHSER(2), KOFFERANHAENGER(3), MOTORRADANHAENGER(4);

    public final long id;

    AnhaengerTypEnum(long id) {
        this.id = id;
    }
}
