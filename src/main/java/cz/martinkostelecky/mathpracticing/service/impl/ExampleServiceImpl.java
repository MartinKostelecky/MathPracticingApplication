package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.exception.ExampleAlreadyExistException;
import cz.martinkostelecky.mathpracticing.exception.ExampleNotFoundException;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import cz.martinkostelecky.mathpracticing.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author Martin Kostelecký
 */
@Service
@RequiredArgsConstructor

public class ExampleServiceImpl implements ExampleService {

    private static final Logger logger = LoggerFactory.getLogger(ExampleServiceImpl.class);
    private final ExampleRepository exampleRepository;

    @Override
    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    public void saveExample(Example example) throws ExampleAlreadyExistException {
        Boolean existByExampleTitle = exampleRepository.existByExampleTitle(example.getExampleTitle());
        if (existByExampleTitle) {
            throw new ExampleAlreadyExistException("Example " + example.getExampleTitle() + " already exist in database.");
        }
        exampleRepository.save(example);
        logger.info("Added example: {}", example);
    }

    @Override
    public Example getExampleById(long id) throws ExampleNotFoundException {
        Optional<Example> example = exampleRepository.findById(id);

        return example.orElseThrow(() -> new ExampleNotFoundException("Example not found!"));

    }

    @Override
    public void updateExample(Example example) throws ExampleNotFoundException, ExampleAlreadyExistException {

        Optional<Example> optionalExistingExample = exampleRepository.findById(example.getId());
        Boolean existByExampleTitle = exampleRepository.existByExampleTitle(example.getExampleTitle());

        if (optionalExistingExample.isPresent()) {
            Example existingExample = optionalExistingExample.get();
            existingExample.setId(example.getId());
            if (!existingExample.getExampleTitle().equals(example.getExampleTitle()) && existByExampleTitle) {
                throw new ExampleAlreadyExistException("Example " + example.getExampleTitle() + " already exist in database.");
            } else {
                existingExample.setExampleTitle(example.getExampleTitle());
            }

            existingExample.setRightAnswer(example.getRightAnswer());
            exampleRepository.save(existingExample);

            logger.info("Example id: {} updated.", example.getId());

        } else {
            throw new ExampleNotFoundException("Example not found!");
        }
    }

    @Override
    public void deleteExampleById(Long id) {

        exampleRepository.deleteById(id);
        logger.info("Example id: {} deleted.", id);
    }

}
