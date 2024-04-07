package entitys;

public class ResponseDTO {
    public boolean isAuthenticated;
    public  User user;

    public ResponseDTO(boolean isAuthenticated, User user) {
        this.isAuthenticated = isAuthenticated;
        this.user = user;
    }
}
