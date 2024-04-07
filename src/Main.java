import entitys.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр бэкенда
        Backend backend = new Backend();
        // Создаем экземпляр фронтенда, передавая ему бэкенд
        Frontend frontend = new Frontend(backend);
        // Запускаем программу
        runApp(frontend);
        int n = 12345;
        
    }

    private static void runApp(Frontend frontend) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Выберите действие: ");
            System.out.println("1. Зарегистрироваться");
            System.out.println("2. Войти в систему");
            System.out.println("3. Выйти из системы");
            System.out.println("4. Выйти из программы");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Регистрация
                    frontend.requestRegistration();
                    break;
                case "2":
                    // Вход в систему
                    frontend.requestLogin();
                    break;
                case "3":
                    // Выход из системы
                    frontend.requestLogout();
                    break;
                case "4":
                    // Выход из программы
                    isRunning = false;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }

        scanner.close();
    }

}