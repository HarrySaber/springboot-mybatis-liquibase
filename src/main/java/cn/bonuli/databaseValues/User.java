package cn.bonuli.databaseValues;

/**
 * User
 *
 * @author D.jin
 * @date 2018/10/24
 */
public class User {
    final String id;
    final String roleId;
    final String userName;
    final String password;
    final String companyId;

    public User(String id, String roleId, String userName, String password, String companyId) {
        this.id = id;
        this.roleId = roleId;
        this.userName = userName;
        this.password = password;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyId() {
        return companyId;
    }
}
