import entitys.RequestDTO;
import entitys.ResponseDTO;
import entitys.User;
import interfaces.Back;

public class Backend implements Back {
    private final User[] users = new User[10];
    private User loggedInUser = new User("", "");

    public Backend() {
    }

    @Override
    public ResponseDTO gateway(RequestDTO requestDto) {
        return switch (requestDto.mapping) {
            case "login" -> handleLogin(requestDto);
            case "logout" -> handleLogout(requestDto);
            case "signIn" -> handleSignIn(requestDto);
            default -> new ResponseDTO(false, loggedInUser);
        };
    }
    private ResponseDTO handleLogin(RequestDTO dto) {
        // Реализация метода логина
        String username = dto.data[0][1];
        String password = dto.data[1][1];
        for (User user : users) {
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser.setUsername(user.getUsername());
                loggedInUser.setPassword(user.getPassword());
            }
        }
        return new ResponseDTO(!loggedInUser.getUsername().isEmpty(), loggedInUser);
    }

    private ResponseDTO handleLogout(RequestDTO dto) {
        // Реализация метода выхода из системы
        if (!dto.isAuthenticated) {
            loggedInUser.setUsername(dto.data[0][1]);
            loggedInUser.setPassword(dto.data[1][1]);
        }
        return new ResponseDTO(false, loggedInUser);
    }

    private ResponseDTO handleSignIn(RequestDTO dto) {
        // Реализация метода регистрации
        String username = dto.data[0][1];
        String password = dto.data[1][1];
        User newUser = new User(username, password);
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                users[i] = newUser;
                break;
            }
        }
        return new ResponseDTO(false, newUser);
    }
}
