package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.exception.ExampleNotFoundException;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PracticingServiceImplTest {

    @Mock
    private ExampleRepository exampleRepositoryTest;

    private PracticingServiceImpl practicingServiceImplTest;

    @BeforeEach
    void setUp() {
        practicingServiceImplTest = new PracticingServiceImpl(exampleRepositoryTest);
    }

    @Test
    void getRandomExample_ExamplesDatabaseEmpty() {
        //given
        Example testExample = new Example();

        testExample.setCategory("Sčítání");

        List<Example> emptyList = List.of();

        when(exampleRepositoryTest.findByCategory(testExample.getCategory())).thenReturn(emptyList);

        //when
        ExampleNotFoundException exception = assertThrows(ExampleNotFoundException.class,
                () -> practicingServiceImplTest.getRandomExample(testExample));

        //then
        assertEquals("Žádný příklad nenalezen", exception.getMessage());
    }

    @Test
    void canGetRandomExample() throws ExampleNotFoundException {
        //given
        Example testExample = new Example();

        testExample.setCategory("Sčítání");

        List<Example> examples = List.of(new Example(1L, "1+1", null, "Sčítání", "2", null));

        when(exampleRepositoryTest.findByCategory("Sčítání")).thenReturn(examples);
        when(exampleRepositoryTest.findById(1L)).thenReturn(Optional.of(examples.getFirst()));

        //when
        Optional<Example> randomExample = practicingServiceImplTest.getRandomExample(testExample);

        //then
        assertTrue(randomExample.isPresent());
        assertEquals(examples.getFirst(), randomExample.get());
    }

    @Test
    void canGetResult() {
        //given
        Example testExample = new Example(1L, "1+1", "2", "Sčítání", "2", null);

        Example exampleToCompare = new Example(1L, "1+1", null, "Sčítání", "2", null);

        when(exampleRepositoryTest.findById(testExample.getId())).thenReturn(Optional.of(exampleToCompare));

        //when
        Boolean result = practicingServiceImplTest.getResult(testExample);

        //then
        assertNotNull(result);
        assertTrue(result);

    }

    @Test
    void getResultLogicOperators() {
        //given
        int firstNumber = 2;

        int secondNumber = 1;

        String chosenOperator = ">";
        //when
        Boolean result = practicingServiceImplTest.getResultLogicOperators(firstNumber, secondNumber, chosenOperator);
        //then
        assertTrue(result);
    }

    @Test
    void getResultLogicOperators_wrongOperator() {
        //given
        int firstNumber = 1;

        int secondNumber = 0;

        String chosenOperator = "<";
        //when
        Boolean result = practicingServiceImplTest.getResultLogicOperators(firstNumber, secondNumber, chosenOperator);
        //then
        assertFalse(result);
    }
}