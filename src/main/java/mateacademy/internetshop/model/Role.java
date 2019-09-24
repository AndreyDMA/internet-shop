package mateacademy.internetshop.model;

import mateacademy.internetshop.IdGenerator;

public class Role {
    private final Long roleId;
    private RoleName roleName;

    public enum RoleName {
        USER, ADMIN;
    }

    public Role() {
        this.roleId = IdGenerator.getRoleGeneratedId();
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }
}
