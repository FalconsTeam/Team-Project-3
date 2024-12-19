package Exception.CustomerException;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Пользователя с таким ID нет, введите ID: ";
    }
}
