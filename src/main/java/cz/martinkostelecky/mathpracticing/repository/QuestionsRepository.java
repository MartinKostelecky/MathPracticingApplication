package cz.martinkostelecky.mathpracticing.repository;

import cz.martinkostelecky.mathpracticing.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {
}
