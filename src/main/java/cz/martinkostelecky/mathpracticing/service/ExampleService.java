package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.exception.ExampleAlreadyExistException;
import cz.martinkostelecky.mathpracticing.exception.ExampleNotFoundException;

import java.util.List;

/**
 * @author Martin Kostelecký
 */
public interface ExampleService {

    List<Example> getAllExamples();

    void saveExample(Example example) throws ExampleAlreadyExistException;

    Example getExampleById(long id) throws ExampleNotFoundException;

    void updateExample(Example example) throws ExampleNotFoundException, ExampleAlreadyExistException;

    void deleteExampleById(Long id);
}
