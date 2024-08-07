package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Question;

import java.util.Optional;

public interface PracticingService {

    Optional<Question> getRandomQuestion();
}
