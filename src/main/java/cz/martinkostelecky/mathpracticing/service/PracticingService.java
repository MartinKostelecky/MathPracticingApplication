package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Martin Kostelecký
 */
public interface PracticingService {

    Optional<Example> getRandomExample(Example example);

    Boolean getResult(Example example);

    void playSound(Boolean result) throws UnsupportedAudioFileException, IOException, LineUnavailableException;
}
