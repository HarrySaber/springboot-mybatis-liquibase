package cn.bonuli.values;

/**
 * LoginUser
 *
 * @author D.jin
 * @date 2018/10/22
 */
public class LoginUser {
    private String userName;
    private String password;
    private String company;

    public LoginUser(String userName, String password, String company) {
        this.userName = userName;
        this.password = password;
        this.company = company;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
