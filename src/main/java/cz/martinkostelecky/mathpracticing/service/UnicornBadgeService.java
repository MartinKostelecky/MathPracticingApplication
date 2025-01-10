package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.model.UnicornBadge;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UnicornBadgeService {

    private static final Logger logger = LoggerFactory.getLogger(UnicornBadgeService.class);
    private List<UnicornBadge> unicornBadges;

    public List<UnicornBadge> getListOfColoredUnicorns(Boolean result) {
        if (result) {
            unicornBadges.add(new UnicornBadge());
            logger.info("Badge was added");
            if (unicornBadges.size() == 11) {
                unicornBadges = new ArrayList<>();
            }
        } else {
            unicornBadges.removeLast();
            logger.info("Badge was removed");
        }
        logger.info("Number of Unicorn badges: {}", unicornBadges.size());
        return unicornBadges;
    }

}

