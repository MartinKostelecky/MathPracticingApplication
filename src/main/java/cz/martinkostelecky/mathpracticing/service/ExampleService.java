package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;

import java.util.List;

public interface ExampleService {

    List<Example> getAllExamples();

    void saveExample(Example example);

    Example getExampleById(long id);

    void updateInsuredPerson(Example example);

    void deleteExampleById(Long id);
}
