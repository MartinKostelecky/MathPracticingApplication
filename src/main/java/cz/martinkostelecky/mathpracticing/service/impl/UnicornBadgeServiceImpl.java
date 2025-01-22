package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.service.UnicornBadgeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UnicornBadgeServiceImpl implements UnicornBadgeService {

    private static final Logger logger = LoggerFactory.getLogger(UnicornBadgeServiceImpl.class);
    private List<UnicornBadge> unicornBadges;
    private static final int MAX_BADGES = 10;

    public List<UnicornBadge> getListOfUnicornBadges(Boolean result) {

        if (result) {
            unicornBadges.add(new UnicornBadge());
            logger.info("Badge was added");

        } else {
            unicornBadges.removeLast();
            logger.info("Badge was removed");
        }
        logger.info("Number of Unicorn badges: {}", unicornBadges.size());
        return unicornBadges;
    }

    public boolean getIsAccomplished(List<UnicornBadge> unicornBadgeList) {
        return unicornBadgeList.size() == MAX_BADGES;
    }

    public void getNewListOfUnicornBadges(List<UnicornBadge> unicornBadgeList) {
        if (unicornBadgeList.size() == MAX_BADGES) {
            logger.info("New badge list created");
            unicornBadges = new ArrayList<>();
        }
    }

    @NoArgsConstructor
    public static class UnicornBadge {
    }
}

