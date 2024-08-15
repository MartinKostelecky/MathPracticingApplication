package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PracticingServiceImpl implements PracticingService {

    Random random = new Random();
    private final ExampleRepository exampleRepository;
    private final Map<String, Long> cache = new HashMap<>();

    @Override
    public Optional<Example> getRandomExample() {

        List<Example> examples = exampleRepository.findAll();
        if (examples.isEmpty()) {
            return Optional.empty();
        }

        Long id = random.nextLong(1, examples.size() + 1);

        // Check if the ID is already in the cache
        if (cache.containsKey("lastUsedId") && cache.get("lastUsedId").equals(id)) {
            id = random.nextLong(1, examples.size() + 1);
        }

        // Store the current ID in the cache
        cache.put("lastUsedId", id);
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
