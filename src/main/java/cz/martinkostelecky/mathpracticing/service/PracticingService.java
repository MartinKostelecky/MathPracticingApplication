package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;

import java.util.Optional;

/**
 * @author Martin Kostelecký
 */
public interface PracticingService {

    Optional<Example> getRandomExample(Example example);

    Boolean getResult(Example example);

}
