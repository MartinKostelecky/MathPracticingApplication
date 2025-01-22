package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.service.impl.UnicornBadgeServiceImpl;

import java.util.List;

public interface UnicornBadgeService {

    List<UnicornBadgeServiceImpl.UnicornBadge> getListOfUnicornBadges(Boolean result);

    boolean getIsAccomplished(List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgeList);

    void getNewListOfUnicornBadges(List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgeList);
}
