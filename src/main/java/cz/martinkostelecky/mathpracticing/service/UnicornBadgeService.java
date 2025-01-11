package cz.martinkostelecky.mathpracticing.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    private final int MAX_BADGES = 11;

    public List<UnicornBadge> getListOfColoredUnicorns(Boolean result) {
        if (result) {
            unicornBadges.add(new UnicornBadge());
            logger.info("Badge was added");
            if (unicornBadges.size() == MAX_BADGES) {
                unicornBadges = new ArrayList<>();
            }
        } else {
            unicornBadges.removeLast();
            logger.info("Badge was removed");
        }
        logger.info("Number of Unicorn badges: {}", unicornBadges.size());
        return unicornBadges;
    }

    @NoArgsConstructor
    public static class UnicornBadge {}

}

