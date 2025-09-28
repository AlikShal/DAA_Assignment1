package alikhan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты  быстрой сортировки (RobustQuickSort)")
class QuickSortTest {

    private QuickSort sorter;
    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        sorter = new QuickSort();
    }

    @Test
    @DisplayName(" Сортировка массива")
    void testRandomArray() {
        int[] actual = generateRandomArray(1000, 10000);
        int[] expected = Arrays.copyOf(actual, actual.length);
        Arrays.sort(expected); // Используем эталонную сортировку для проверки

        sorter.sort(actual);

        assertArrayEquals(expected, actual, "Массив со случайными элементами отсортирован неверно");
    }

    @Test
    @DisplayName(" Сортировка уже отсортированного массива")
    void testAlreadySortedArray() {
        int[] actual = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        sorter.sort(actual);

        assertArrayEquals(expected, actual, "Без изменений");
    }

    @Test
    @DisplayName("Сортировка обратного порядка чисел")
    void testReverseSortedArray() {
        int[] actual = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        sorter.sort(actual);

        assertArrayEquals(expected, actual, "Массив, отсортированный в обратном порядке, обработан неверно");
    }

    @Test
    @DisplayName(" Массив с дублирующимися элементами")
    void testArrayWithDuplicates() {
        int[] actual = {5, 8, 5, 2, 8, 5, 2, 2, 9};
        int[] expected = {2, 2, 2, 5, 5, 5, 8, 8, 9};

        sorter.sort(actual);

        assertArrayEquals(expected, actual, "Массив с дубликатами отсортирован неверно");
    }

    @Test
    @DisplayName("Массив с одинаковыми элементами")
    void testArrayWithAllSameElements() {
        int[] actual = {7, 7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7, 7};

        sorter.sort(actual);

        assertArrayEquals(expected, actual, "Без изменений");
    }

    @Test
    @DisplayName("Пустой массив")
    void testEmptyArray() {
        int[] arr = {};
        // Ожидаем, что метод просто завершит работу без ошибок
        assertDoesNotThrow(() -> sorter.sort(arr));
        assertEquals(0, arr.length, "Пустой массив");
    }

    @Test
    @DisplayName(" Работа с массивом из одного элемента")
    void testSingleElementArray() {
        int[] actual = {42};
        int[] expected = {42};

        sorter.sort(actual);

        assertArrayEquals(expected, actual, "Без изменений"); //1 элемент
    }

    @Test
    @DisplayName(" Проверка на StackOverflowError на большом массиве")
    @Timeout(value = 5, unit = TimeUnit.SECONDS) // Тест не должен длиться дольше 5 секунд
    void testLargeArrayForStackOverflow() {
        int size = 500_000; // Достаточно большой размер для вызова StackOverflowError в наивной реализации
        int[] largeArray = generateRandomArray(size, Integer.MAX_VALUE);

        // Этот тест проверяет, что оптимизация хвостовой рекурсии работает
        // и не вызывает переполнение стека.
        assertDoesNotThrow(() -> sorter.sort(largeArray),
                "Сортировка большого массива не должна вызывать StackOverflowError");
    }

    // Вспомогательный метод для генерации случайных массивов
    private int[] generateRandomArray(int size, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue);
        }
        return array;
    }
}

