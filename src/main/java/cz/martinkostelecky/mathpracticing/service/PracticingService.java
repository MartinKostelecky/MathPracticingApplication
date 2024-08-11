package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;

import java.util.Optional;

public interface PracticingService {

    Optional<Example> getRandomExample();

    Boolean getResult(Example example);
}
