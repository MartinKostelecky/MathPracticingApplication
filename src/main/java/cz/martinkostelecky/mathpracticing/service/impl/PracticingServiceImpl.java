package cz.martinkostelecky.mathpracticing.service.impl;


import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.exception.ExampleNotFoundException;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Martin Kostelecký
 */
@Service
@RequiredArgsConstructor
public class PracticingServiceImpl implements PracticingService {

    private static final Logger logger = LoggerFactory.getLogger(PracticingServiceImpl.class);
    Random random = new Random();
    private final ExampleRepository exampleRepository;
    private final Map<String, Long> cache = new HashMap<>();

    /**
     * gets random example by category and by id from database
     *
     * @param example
     * @return example of certain category by id
     */
    @Override
    public Optional<Example> getRandomExample(Example example) throws ExampleNotFoundException {

        List<Example> examples = exampleRepository.findByCategory(example.getCategory());
        if (examples.isEmpty()) {
            logger.info("Žádný příklad nenalezen");
            throw new ExampleNotFoundException("Žádný příklad nenalezen");
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
        logger.info("Random example id: {}", id);

        return exampleRepository.findById(id);
    }

    @Override
    public int getRandomNumber() {
        return random.nextInt(13);
    }

    /**
     * evaluates answer
     *
     * @param example
     * @return whether answer was correct
     */
    @Override
    public Boolean getResult(Example example) {

        if (exampleRepository.findById(example.getId()).isPresent()) {
            Example exampleToCompare = exampleRepository.findById(example.getId()).get();
            example.setIsCorrect(example.getAnswer().equals(exampleToCompare.getRightAnswer()));

            logger.info("User answer {} for example {} was: {}",
                    example.getAnswer(), exampleToCompare.getExampleTitle(), example.getIsCorrect());

        }
        return example.getIsCorrect();
    }


    /**
     * evaluates answer in logic operators practicing
     *
     * @param firstNumber
     * @param secondNumber
     * @param chosenOperator
     * @return whether answer was correct
     */
    @Override
    public Boolean getResultLogicOperators(int firstNumber, int secondNumber, String chosenOperator) {

        boolean result = switch (chosenOperator) {
            case ">" -> firstNumber > secondNumber;
            case "<" -> firstNumber < secondNumber;
            case "=" -> firstNumber == secondNumber;
            default -> false;
        };

        logger.info("User answer for example {} {} {} was: {}", firstNumber, chosenOperator, secondNumber, result);
        return result;
    }

}
