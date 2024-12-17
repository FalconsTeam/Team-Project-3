package Exception;

public class CustomerNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Пользователя с таким ID нет";
    }
}
