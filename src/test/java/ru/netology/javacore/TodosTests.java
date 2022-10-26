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
    @org.junit.jupiter.api.Order(1)
    @DisplayName("Тест: addTask() добавить новую задачу. Лист + 1 и стек + 1.")
    void addTask() {
        Order order = new Order(Operation.ADD, "walk");
        todos.addTask(order);
        Assertions.assertAll("Добавление новой задачи",
                () -> Assertions.assertEquals(1, todos.getTaskList().size(), "check list size"),
                () -> Assertions.assertEquals(1, todos.getOrderDeque().size(), "check stack size"));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("Тест: addTask() добавить новую задачу, если лист задач полон (7 задач).")
    void addTaskFullList() {
        Order order = new Order(Operation.ADD, "walk");
        todos.setTaskList(List.of("walk", "run", "jump", "eat", "sleep", "train", "play"));
        todos.addTask(order);
        Assertions.assertAll("Добавление новой задачи, если лист полон.",
                () -> Assertions.assertEquals(7, todos.getTaskList().size(), "check list size"),
                () -> Assertions.assertEquals(0, todos.getOrderDeque().size(), "check stack size"));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("Тест: removeTask() удалить задачу, если задача есть в списке.")
    void removeTask() {
        Order order = new Order(Operation.REMOVE, "walk");
        todos.setTaskList(new ArrayList<>(List.of("walk", "run", "jump", "eat", "sleep", "train", "play")));
        todos.removeTask(order);
        String lastInDeque = todos.getOrderDeque().peekLast().getTask();
        List<String> expected = new ArrayList<>(List.of("run", "jump", "eat", "sleep", "train", "play"));
        Assertions.assertAll("Удалить задачу из списка.",
                () -> Assertions.assertEquals(6, todos.getTaskList().size(), "check list size"),
                () -> Assertions.assertEquals(expected, todos.getTaskList(), "check list value"),
                () -> Assertions.assertEquals(1, todos.getOrderDeque().size(), "check stack size"),
                () -> Assertions.assertEquals("walk", lastInDeque, "check stack value"));
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @DisplayName("Тест: removeTask() удалить задачу, если задачи нет в списке.")
    void removeTaskNotInList() {
        Order order = new Order(Operation.REMOVE, "Walk");
        todos.setTaskList(new ArrayList<>(List.of("walk", "run", "jump", "eat", "sleep", "train", "play")));
        todos.removeTask(order);
        Assertions.assertAll("Удалить задачу из списка.",
                () -> Assertions.assertEquals(7, todos.getTaskList().size(), "check list"),
                () -> Assertions.assertEquals(0, todos.getOrderDeque().size(), "check stack"),
                () -> Assertions.assertNull(todos.getOrderDeque().peekLast(), "check stack value"));
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    @DisplayName("Тест: getAllTasks() получить список всех задач в лексикографическом порядке.")
    void getAllTasks() {
        todos.setTaskList(new ArrayList<>(List.of("walk", "run", "jump", "eat", "sleep", "train", "play")));
        String expected = "eat jump play run sleep train walk";
        Assertions.assertEquals(expected, todos.getAllTasks());
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    @DisplayName("Тест: restoreTask() отменить 2 задачи.")
    void restoreTask() {
        todos.addTask(new Order(Operation.ADD, "walk"));
        todos.addTask(new Order(Operation.ADD, "eat"));
        todos.removeTask(new Order(Operation.REMOVE, "walk"));
        todos.addTask(new Order(Operation.ADD, "jump"));
        todos.restoreTask();
        todos.restoreTask();
        String expected = "eat walk";
        Assertions.assertEquals(expected, todos.getAllTasks());
    }
}
