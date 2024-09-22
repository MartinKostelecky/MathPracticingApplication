package cz.martinkostelecky.mathpracticing.repository;

import cz.martinkostelecky.mathpracticing.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(e) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Example e " +
            "WHERE e.exampleTitle = ?1")
    Boolean existByExampleTitle(String exampleTitle);

    List<Example> findByCategory(String category);
}
