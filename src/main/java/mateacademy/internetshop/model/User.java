package mateacademy.internetshop.model;

import mateacademy.internetshop.IdGenerator;

public class User {
    private final Long id;
    private String name;

    public User(String name) {
        this.id = IdGenerator.getGeneratedId();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
