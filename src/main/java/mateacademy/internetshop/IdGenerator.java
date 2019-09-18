package mateacademy.internetshop;

public class IdGenerator {
    private static Long idGenerator = 201909L;

    private IdGenerator() {
    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}
