package cz.martinkostelecky.mathpracticing.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class UnicornBadgeServiceTest {

    private UnicornBadgeServiceImpl badgeServiceTest;
    private List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgesTestList = new ArrayList<>();
    private static final int MAX_BADGES = 10;

    @BeforeEach
    void setUp() {
        badgeServiceTest = new UnicornBadgeServiceImpl(unicornBadgesTestList);
    }

    @Test
    void canGetListOfUnicornBadges_badgeAdded() {
        //given
        boolean result = true;

        //when
        unicornBadgesTestList = badgeServiceTest.getListOfUnicornBadges(result);

        //then
        assertFalse(unicornBadgesTestList.isEmpty());
    }

    @Test
    void canGetListOfUnicornBadges_badgeRemoved() {
        //given
        boolean result = false;
        unicornBadgesTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());

        //when
        unicornBadgesTestList = badgeServiceTest.getListOfUnicornBadges(result);

        //then
        assertTrue(unicornBadgesTestList.isEmpty());
    }

    @Test
    void canGetIsAccomplished() {
        //given
        for (int i = 0; i < MAX_BADGES; i++) {
            unicornBadgesTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        boolean isAccomplished = badgeServiceTest.getIsAccomplished(unicornBadgesTestList);

        //then
        assertTrue(isAccomplished);
    }

    @Test
    void canGetNewListOfUnicornBadges_MaxBadges() {
        //given
        for (int i = 0; i < MAX_BADGES; i++) {
            unicornBadgesTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        badgeServiceTest.getNewListOfUnicornBadges(unicornBadgesTestList);

        //then
        assertEquals(Collections.emptyList(), badgeServiceTest.getListOfUnicornBadges(false));
    }

    @Test
    void cantGetNewListOfUnicornBadges_LessThanMaxBadges() {
        //given
        for (int i = 0; i < MAX_BADGES - 1; i++) {
            unicornBadgesTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        badgeServiceTest.getNewListOfUnicornBadges(unicornBadgesTestList);

        //then
        assertNotEquals(Collections.emptyList(), badgeServiceTest.getListOfUnicornBadges(false));
    }
}
