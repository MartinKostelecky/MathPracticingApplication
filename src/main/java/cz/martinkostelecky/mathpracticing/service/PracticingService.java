package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.exception.ExampleNotFoundException;

import java.util.Optional;

/**
 * @author Martin Kostelecký
 */
public interface PracticingService {

    Optional<Example> getRandomExample(Example example) throws ExampleNotFoundException;

    int getRandomNumber();

    Boolean getResult(Example example);

    Boolean getResultLogicOperators(int firstNumber, int secondNumber, String chosenOperator);

}
