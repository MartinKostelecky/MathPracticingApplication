package cz.martinkostelecky.mathpracticing.service;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.service.impl.UnicornBadgeServiceImpl;

import java.util.List;

/**
 * @author Martin Kostelecký
 */
public interface UnicornBadgeService {

    List<UnicornBadgeServiceImpl.UnicornBadge> getListOfUnicornBadgesForAdditionAndSubtraction(Boolean result, Example example);

    List<UnicornBadgeServiceImpl.UnicornBadge> getListOfUnicornBadgesForLogicOperators(Boolean result);

    boolean getIsAccomplished(List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgeList);

    void getNewListOfUnicornBadgesForAdditionAndSubtraction(List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgeList, Example example);

    void getNewListOfUnicornBadgesForLogicOperators(List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgeList);
}
