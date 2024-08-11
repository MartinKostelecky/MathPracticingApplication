package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class PracticingServiceImpl implements PracticingService {

    Random random = new Random();

    private final ExampleRepository exampleRepository;

    @Override
    public Optional<Example> getRandomExample() {

        //TODO Optional
        List<Example> examples = exampleRepository.findAll();

        Long id = random.nextLong(1, examples.size() + 1);
        log.info("Random example id: {}", id);

        return exampleRepository.findById(id);
    }

    @Override
    public Boolean getResult(Example example) {

        if (exampleRepository.findById(example.getId()).isPresent()) {
            Example exampleToCompare = exampleRepository.findById(example.getId()).get();
            example.setIsCorrect(example.getAnswer().equals(exampleToCompare.getRightAnswer()));

            log.info("User answer {} for example {} was: {}", example.getAnswer(), exampleToCompare.getExampleTitle(), example.getIsCorrect());
        }
        return example.getIsCorrect();
    }
}
