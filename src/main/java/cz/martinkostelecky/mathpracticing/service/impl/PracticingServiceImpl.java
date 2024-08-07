package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Question;
import cz.martinkostelecky.mathpracticing.repository.QuestionsRepository;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PracticingServiceImpl implements PracticingService {

    Random random = new Random();

    private final QuestionsRepository questionsRepository;

    @Override
    public Optional<Question> getRandomQuestion() {

        Long id = random.nextLong();

        return questionsRepository.findById(id);
    }
}
