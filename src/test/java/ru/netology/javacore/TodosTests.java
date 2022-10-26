package ru.netology.javacore;

import org.junit.jupiter.api.*;

@DisplayName("Тест класса Person.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodosTests {


    private static Todos todos;

    @BeforeAll
    static void setUp() {
        todos = new Todos();
    }

    @AfterAll
    static void tearDown() {
        todos = null;
    }


}
