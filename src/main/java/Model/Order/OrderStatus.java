package Model.Order;

public enum OrderStatus {
    NEW("Новый"),
    PROCESSING("Обробатывается"),
    COMPLETE("Завершен"),
    CANCELLED("Отменён");

    OrderStatus(String string) {
    }
}
