package ru.inno.market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.inno.market.core.Catalog;
import ru.inno.market.model.Category;
import ru.inno.market.model.Item;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MarketServiceTest {

    @Nested
    class MarketServiseTest {

        private Catalog catalog;

        @BeforeEach
        public void setUp() {
            catalog = new Catalog();
        }

        // Проверяем, возвращает ли метод getItemById правильный товар по его id
        @Test
        public void testGetItemById() {
            Item expected = new Item(1, "Apple iPhone SE", Category.SMARTPHONES, 97990);
            Item actual = catalog.getItemById(1);
            assertEquals(expected, actual, "Returned item should match the expected item");
        }

        // Проверяем, выбрасывает ли метод getItemById исключение NoSuchElementException
        // при попытке получить несуществующий товар
        @Test
        public void testGetItemByIdThrowsException() {
            assertThrows(NoSuchElementException.class, () -> catalog.getItemById(100),
                    "Expected NoSuchElementException when trying to get non-existing item");
        }

        // Проверяем, возвращает ли метод getCountForItem правильное количество товара по заданному Item
        @Test
        public void testGetCountForItem() {
            Item item = new Item(2, "Xiaomi POCO M5s", Category.SMARTPHONES, 19490);
            int expectedCount = 8;
            int actualCount = catalog.getCountForItem(item);
            assertEquals(expectedCount, actualCount, "Count of item should match the expected count");

        }
    }
}
