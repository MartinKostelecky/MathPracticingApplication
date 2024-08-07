package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Question;
import cz.martinkostelecky.mathpracticing.repository.QuestionsRepository;
import cz.martinkostelecky.mathpracticing.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionsRepository questionsRepository;

    public void saveQuestion(Question question) {
        questionsRepository.save(question);
    }
}
