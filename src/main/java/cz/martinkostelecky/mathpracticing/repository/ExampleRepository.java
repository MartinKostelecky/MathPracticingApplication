package cz.martinkostelecky.mathpracticing.repository;

import cz.martinkostelecky.mathpracticing.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
