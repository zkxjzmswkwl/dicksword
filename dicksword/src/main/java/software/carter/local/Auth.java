package software.carter.local;

public class Auth {
    private static Auth auth;

    static {
        auth = new Auth();
    }

    public static Auth s() {
        return auth;
    }

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        if (this.username != null)
            return this.username;
        return "NULL";
    }
}
