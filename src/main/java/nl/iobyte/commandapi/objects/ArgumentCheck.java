package nl.iobyte.commandapi.objects;

public class ArgumentCheck {

    private final boolean key;
    private final int value;

    public ArgumentCheck(boolean key, int value) {
        this.key = key;
        this.value = value;
    }

    public boolean getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

}
