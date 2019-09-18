package mateacademy.internetshop;

public class IdGenerator {
    private static Long idGenerator;
    private static final Long START_VALUE = 201909L;

    private IdGenerator() {
        idGenerator = START_VALUE;
    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}
