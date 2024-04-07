import entitys.RequestDTO;
import entitys.ResponseDTO;
import interfaces.Front;

import java.util.Scanner;

// Класс, реализующий фронтенд
class Frontend implements Front {
    private Backend backend;
    private Scanner scanner;

    public Frontend(Backend backend) {
        this.backend = backend;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void requestRegistration() {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        RequestDTO requestDto = new RequestDTO();
        requestDto.mapping = "signIn";
        requestDto.data = new String[][]{{"username", username}, {"password", password}};
        requestDto.isAuthenticated = false;

        ResponseDTO responseDTO = backend.gateway(requestDto);

        if (!responseDTO.isAuthenticated
                && responseDTO.user.getUsername().equals(username)
                && responseDTO.user.getPassword().equals(password)){
            System.out.println("Регистрация успешно завершена!");
            showLoginOptions();
        }

    }

    @Override
    public void requestLogin() {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        RequestDTO requestDto = new RequestDTO();
        requestDto.mapping = "login";
        requestDto.data = new String[][]{{"username", username}, {"password", password}};
        requestDto.isAuthenticated = true;

        ResponseDTO responseDTO = backend.gateway(requestDto);

        if (responseDTO.isAuthenticated
                && responseDTO.user.getUsername().equals(username)
                && responseDTO.user.getPassword().equals(password)) {
            System.out.println("Вход выполнен успешно.");
            showLogoutOption();
        }else{
            System.out.println("Вход не выполнен! Пользователь не найден!");
        }
    }

    @Override
    public void requestLogout() {
        RequestDTO requestDto = new RequestDTO();
        requestDto.mapping = "logout";
        requestDto.data = new String[][]{{"username", ""}, {"password", ""}};
        requestDto.isAuthenticated = false;

        ResponseDTO responseDTO = backend.gateway(requestDto);

        if (!responseDTO.isAuthenticated && responseDTO.user.getUsername().isEmpty()){
            System.out.println("Выход выполнен успешно!");
            showLoginOptions();
        }else{
            System.out.println("Выход не выполнен!");
            showLogoutOption();
        }
    }

    // Вспомогательный метод для отображения опций после регистрации
    private void showLoginOptions() {
        System.out.println("Выберите действие:");
        System.out.println("1. Войти в систему");
        System.out.println("2. Зарегистрировать нового пользователя");
        System.out.println("3. Выйти из программы");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> requestLogin();
            case "2" -> requestRegistration();
            case "3" -> System.exit(0);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    // Вспомогательный метод для отображения опции выхода из системы после входа в нее
    private void showLogoutOption() {
        System.out.println("Выберите действие:");
        System.out.println("1. Выйти из системы");
        System.out.println("2. Выйти из программы");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> requestLogout();
            case "2" -> System.exit(0);
            default -> System.out.println("Некорректный выбор.");
        }
    }
}

