package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.repository.ExampleRepository;
import cz.martinkostelecky.mathpracticing.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    @Override
    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    public void saveExample(Example example) {
        exampleRepository.save(example);
    }

    @Override
    public Example getExampleById(long id) {
        Optional<Example> example = exampleRepository.findById(id);
        return example.orElse(null);
        //TODO throw exampleNotFoundException
    }

    @Override
    public void updateInsuredPerson(Example example) {

        Optional<Example> optionalExistingExample = exampleRepository.findById(example.getId());

        if (optionalExistingExample.isPresent()) {
            Example existingExample = optionalExistingExample.get();
            existingExample.setId(example.getId());
            existingExample.setExampleTitle(example.getExampleTitle());
            existingExample.setRightAnswer(example.getRightAnswer());
            exampleRepository.save(existingExample);
        }
        //TODO throw exampleNotFoundException
    }

    @Override
    public void deleteExampleById(Long id) {

        exampleRepository.deleteById(id);

    }
}
