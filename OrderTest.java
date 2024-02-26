package ru.inno.market.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order(1, new Client(1, "John Doe"));
    }

    // Проверяем, добавляется ли товар в корзину заказа
    @Test
    public void testAddItem() {
        Item item = new Item(1, "Test Item", Category.SMARTPHONES, 100.0);
        order.addItem(item);
        assertTrue(order.getItems().containsKey(item));
        assertEquals(1, order.getItems().get(item));
    }

    // Проверяем, применяется ли скидка к заказу
    @Test
    public void testApplyDiscount() {
        order.addItem(new Item(1, "Test Item", Category.SMARTPHONES, 100.0));
        order.applyDiscount(0.1); // Применяем скидку 10%
        assertEquals(90.0, order.getTotalPrice());
        assertTrue(order.isDiscountApplied());
    }

    // Проверяем, возвращает ли метод getClient() правильного клиента
    @Test
    public void testGetClient() {
        Client expectedClient = new Client(1, "John Doe");
        assertEquals(expectedClient, order.getClient());
    }

    // Проверяем, возвращает ли метод getTotalPrice() правильную общую стоимость заказа
    @Test
    public void testGetTotalPrice() {
        order.addItem(new Item(1, "Test Item 1", Category.SMARTPHONES, 100.0));
        order.addItem(new Item(2, "Test Item 2", Category.SMARTPHONES, 200.0));
        assertEquals(300.0, order.getTotalPrice());
    }

    // Проверяем, возвращает ли метод isDiscountApplied() правильное значение о применении скидки
    @Test
    public void testIsDiscountApplied() {
        assertFalse(order.isDiscountApplied());
        order.applyDiscount(0.1); // Применяем скидку
        assertTrue(order.isDiscountApplied());
    }

    // Проверяем, правильно ли работает метод equals()
    @Test
    public void testEquals() {
        Order anotherOrder = new Order(1, new Client(1, "John Doe"));
        assertEquals(order, anotherOrder);
    }

    // Проверяем, правильно ли работает метод hashCode()
    @Test
    public void testHashCode() {
        Order anotherOrder = new Order(1, new Client(1, "John Doe"));
        assertEquals(order.hashCode(), anotherOrder.hashCode());
    }

    // Проверяем, возвращает ли метод toString() ожидаемую строку
    @Test
    public void testToString() {
        Client client = new Client(1, "John Doe");
        Order order = new Order(1, client);
        String expectedString = "Order{id=1, cart={}, client=Client{id=1, nickname='John Doe'}, totalPrice=0.0, discountApplied=false}";
        assertEquals(expectedString, order.toString(), "toString() should return expected string representation");
    }


}
