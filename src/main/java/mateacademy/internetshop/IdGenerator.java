package mateacademy.internetshop;

public class IdGenerator {
    private static Long bucketGeneratedId = 0L;
    private static Long itemGeneratedId = 0L;
    private static Long orderGeneratedId = 0L;
    private static Long userGeneratedId = 0L;
    private static Long roleGeneratedId = 0L;

    private IdGenerator() {
    }

    public static Long getBucketGeneratedId() {
        return bucketGeneratedId++;
    }

    public static Long getItemGeneratedId() {
        return itemGeneratedId++;
    }

    public static Long getOrderGeneratedId() {
        return orderGeneratedId++;
    }

    public static Long getUserGeneratedId() {
        return userGeneratedId++;
    }

    public static Long getRoleGeneratedId() {
        return roleGeneratedId++;
    }
}
