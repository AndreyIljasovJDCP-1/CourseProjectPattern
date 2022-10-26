package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Тест класса Todos.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodosTests {
    private static Todos todos;

    @BeforeEach
    void setUp() {
        todos = new Todos();
    }

    @AfterEach
    void tearDown() {
        todos = null;
    }

    @Test
    @Order(1)
    @DisplayName("Тест: addTask() добавить новую задачу. Лист + 1 и стек + 1.")
    void addTask() {
        Request request = new Request(Operation.ADD, "walk");
        todos.addTask(request);
        Assertions.assertAll("Добавление новой задачи",
                () -> Assertions.assertEquals(1, todos.getListTask().size(), "check list"),
                () -> Assertions.assertEquals(1, todos.getRequestDeque().size(), "check stack"));
    }

    @Test
    @Order(2)
    @DisplayName("Тест: addTask() добавить новую задачу, если лист задач полон (7 задач).")
    void addTaskFullList() {
        Request request = new Request(Operation.ADD, "walk");
        todos.setListTask(List.of("walk", "run", "jump", "eat", "sleep", "train", "play"));
        todos.addTask(request);
        Assertions.assertAll("Добавление новой задачи, если лист полон.",
                () -> Assertions.assertEquals(7, todos.getListTask().size(), "check list"),
                () -> Assertions.assertEquals(0, todos.getRequestDeque().size(), "check stack"));
    }

    @Test
    @Order(3)
    @DisplayName("Тест: removeTask() удалить задачу, если задача есть в списке.")
    void removeTask() {
        Request request = new Request(Operation.REMOVE, "walk");
        todos.setListTask(new ArrayList<>(List.of("walk", "run", "jump", "eat", "sleep", "train", "play")));
        todos.removeTask(request);
        String lastInDeque = todos.getRequestDeque().peekLast().getTask();
        List<String> expected = new ArrayList<>(List.of("run", "jump", "eat", "sleep", "train", "play"));
        Assertions.assertAll("Удалить задачу из списка.",
                () -> Assertions.assertEquals(6, todos.getListTask().size(), "check list"),
                () -> Assertions.assertEquals(expected, todos.getListTask(), "check list value"),
                () -> Assertions.assertEquals(1, todos.getRequestDeque().size(), "check stack"),
                () -> Assertions.assertEquals("walk", lastInDeque, "check stack value"));
    }

    @Test
    @Order(4)
    @DisplayName("Тест: removeTask() удалить задачу, если задачи нет в списке.")
    void removeTaskNotInList() {
        Request request = new Request(Operation.REMOVE, "Walk");
        todos.setListTask(new ArrayList<>(List.of("walk", "run", "jump", "eat", "sleep", "train", "play")));
        todos.removeTask(request);
        Assertions.assertAll("Удалить задачу из списка.",
                () -> Assertions.assertEquals(7, todos.getListTask().size(), "check list"),
                () -> Assertions.assertEquals(0, todos.getRequestDeque().size(), "check stack"),
                () -> Assertions.assertNull(todos.getRequestDeque().peekLast(), "check stack value"));
    }

    @Test
    @Order(5)
    @DisplayName("Тест: getAllTasks() получить список всех задач в лексикографическом порядке.")
    void getAllTasks() {
        todos.setListTask(new ArrayList<>(List.of("walk", "run", "jump", "eat", "sleep", "train", "play")));
        String expected = "eat jump play run sleep train walk";
        Assertions.assertEquals(expected, todos.getAllTasks());
    }

    @Test
    @Order(6)
    @DisplayName("Тест: restoreTask() отменить 2 задачи.")
    void restoreTask() {
        todos.addTask(new Request(Operation.ADD, "walk"));
        todos.addTask(new Request(Operation.ADD, "eat"));
        todos.removeTask(new Request(Operation.REMOVE, "walk"));
        todos.addTask(new Request(Operation.ADD, "jump"));
        todos.restoreTask();
        todos.restoreTask();
        String expected = "eat walk";
        Assertions.assertEquals(expected, todos.getAllTasks());
    }
}
