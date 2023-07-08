package model;

public class Session {
    private static String loggedInUsername;
    private static int userId;

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Session.userId = userId;
    }
}
