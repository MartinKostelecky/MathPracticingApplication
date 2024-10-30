package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Martin Kostelecký
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PracticingServiceImpl implements PracticingService {

    Random random = new Random();
    private final ExampleRepository exampleRepository;
    private final Map<String, Long> cache = new HashMap<>();

    /**
     * gets random example by category and by id from database
     * @param example
     * @return example of certain category by id
     */
    @Override
    public Optional<Example> getRandomExample(Example example) {

        List<Example> examples = exampleRepository.findByCategory(example.getCategory());
        if (examples.isEmpty()) {
            return Optional.empty();
        }

        List<Long> ids = examples.stream().map(Example::getId).toList();

        Long id = ids.get((int) random.nextLong(ids.size()));

        // Check if the ID is already in the cache
        if (cache.containsKey("lastUsedId") && cache.get("lastUsedId").equals(id)) {
            //if yes, new id is selected
            id = ids.get((int) random.nextLong(ids.size()));
        }

        // Store the current ID in the cache
        cache.put("lastUsedId", id);
        log.info("Random example id: {}", id);

        return exampleRepository.findById(id);
    }


    /**
     * evaluates answer
     * @param example
     * @return whether answer was correct
     */
    @Override
    public Boolean getResult(Example example) {

        if (exampleRepository.findById(example.getId()).isPresent()) {
            Example exampleToCompare = exampleRepository.findById(example.getId()).get();
            example.setIsCorrect(example.getAnswer().equals(exampleToCompare.getRightAnswer()));

            log.info("User answer {} for example {} was: {}",
                    example.getAnswer(), exampleToCompare.getExampleTitle(), example.getIsCorrect());

        }
        return example.getIsCorrect();
    }

}
