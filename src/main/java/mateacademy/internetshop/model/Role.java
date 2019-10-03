package mateacademy.internetshop.model;

public class Role {
    private Long roleId;
    private RoleName roleName;

    public enum RoleName {
        USER, ADMIN;
    }

    public Role(Long roleId, RoleName roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
