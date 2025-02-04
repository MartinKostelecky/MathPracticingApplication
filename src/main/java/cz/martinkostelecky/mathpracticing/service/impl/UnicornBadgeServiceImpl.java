package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.service.UnicornBadgeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Kostelecký
 */
@Service
@AllArgsConstructor
public class UnicornBadgeServiceImpl implements UnicornBadgeService {

    private static final Logger logger = LoggerFactory.getLogger(UnicornBadgeServiceImpl.class);

    private List<UnicornBadge> unicornBadgesAddition;
    private List<UnicornBadge> unicornBadgesSubtraction;
    private List<UnicornBadge> unicornBadgesLogic;

    private static final int MAX_BADGES = 10;

    public List<UnicornBadge> getListOfUnicornBadgesForAdditionAndSubtraction(Boolean result, Example example) {

        if (example.getCategory().equals("Sčítání")) {
            return processUnicornBadges(result, unicornBadgesAddition);
        } else {
            return processUnicornBadges(result, unicornBadgesSubtraction);
        }
    }

    public List<UnicornBadge> getListOfUnicornBadgesForLogicOperators(Boolean result) {
        return processUnicornBadges(result, unicornBadgesLogic);
    }

    private List<UnicornBadge> processUnicornBadges(Boolean result, List<UnicornBadge> unicornBadges) {
        if (result) {
            unicornBadges.add(new UnicornBadge());
            logger.info("Badge was added");

        } else if (!unicornBadges.isEmpty()) {
            unicornBadges.removeLast();
            logger.info("Badge was removed");
        } else {
            return new ArrayList<>();
        }
        logger.info("Number of Unicorn badges: {}", unicornBadges.size());
        return unicornBadges;
    }

    public boolean getIsAccomplished(List<UnicornBadge> unicornBadgeList) {
        return unicornBadgeList.size() == MAX_BADGES;
    }

    public void getNewListOfUnicornBadgesForAdditionAndSubtraction(List<UnicornBadge> unicornBadgeList, Example example) {
        if (example.getCategory().equals("Sčítání")) {
            if (unicornBadgeList.size() == MAX_BADGES) {
                logger.info("Addition - new badge list created");
                unicornBadgesAddition = new ArrayList<>();
            }
        } else {
            if (unicornBadgeList.size() == MAX_BADGES) {
                logger.info("Subtraction - new badge list created");
                unicornBadgesSubtraction = new ArrayList<>();
            }
        }
    }

    public void getNewListOfUnicornBadgesForLogicOperators(List<UnicornBadge> unicornBadgeList) {
        if (unicornBadgeList.size() == MAX_BADGES) {
            logger.info("Logic operators - new badge list created");
            unicornBadgesLogic = new ArrayList<>();
        }
    }

    @NoArgsConstructor
    public static class UnicornBadge {
    }
}

