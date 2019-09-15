package mateacademy.internetshop;

public class IdGenerator {
    private static Long idGenerator = 2019L;

    private IdGenerator() {
    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}
