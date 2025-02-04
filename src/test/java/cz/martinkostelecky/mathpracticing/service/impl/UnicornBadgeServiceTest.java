package cz.martinkostelecky.mathpracticing.service.impl;

import cz.martinkostelecky.mathpracticing.entity.Example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class UnicornBadgeServiceTest {

    private UnicornBadgeServiceImpl badgeServiceTest;

    private List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgesAdditionTestList = new ArrayList<>();
    private List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgesSubtractionTestList = new ArrayList<>();
    private List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgesLogicTestList = new ArrayList<>();

    private static final int MAX_BADGES = 10;

    @BeforeEach
    void setUp() {
        badgeServiceTest = new UnicornBadgeServiceImpl(
                unicornBadgesAdditionTestList, unicornBadgesSubtractionTestList, unicornBadgesLogicTestList
        );
    }

    //tests for Addition only, Subtraction use same methods and logic
    @Test
    void canGetListOfUnicornBadgesForAddition_badgeAdded() {
        //given
        boolean result = true;
        Example example = new Example();
        example.setCategory("Sčítání");

        //when
        unicornBadgesAdditionTestList = badgeServiceTest.getListOfUnicornBadgesForAdditionAndSubtraction(result, example);

        //then
        assertFalse(unicornBadgesAdditionTestList.isEmpty());
    }

    @Test
    void canGetListOfUnicornBadgesForAddition_badgeRemoved() {
        //given
        boolean result = false;
        Example example = new Example();
        example.setCategory("Sčítání");
        unicornBadgesAdditionTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());

        //when
        unicornBadgesAdditionTestList = badgeServiceTest.getListOfUnicornBadgesForAdditionAndSubtraction(result, example);

        //then
        assertTrue(unicornBadgesAdditionTestList.isEmpty());
    }

    @Test
    void addUnicornBadgeToSubtractionBadgeListOnly() {
        boolean result = true;
        Example example = new Example();
        example.setCategory("Odčítání");

        unicornBadgesSubtractionTestList = badgeServiceTest.getListOfUnicornBadgesForAdditionAndSubtraction(result, example);

        assertFalse(unicornBadgesSubtractionTestList.isEmpty());
        assertTrue(unicornBadgesAdditionTestList.isEmpty());
        assertTrue(unicornBadgesLogicTestList.isEmpty());
    }


    //for Addition only, method works the same for Subtraction and Logic operators
    @Test
    void canGetIsAccomplished() {
        //given
        for (int i = 0; i < MAX_BADGES; i++) {
            unicornBadgesAdditionTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        boolean isAccomplished = badgeServiceTest.getIsAccomplished(unicornBadgesAdditionTestList);

        //then
        assertTrue(isAccomplished);
    }

    @Test
    void canGetNewListOfUnicornBadgesForAddition_MaxBadges() {
        //given
        Example example = new Example();
        example.setCategory("Sčítání");

        for (int i = 0; i < MAX_BADGES; i++) {
            unicornBadgesAdditionTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        badgeServiceTest.getNewListOfUnicornBadgesForAdditionAndSubtraction(unicornBadgesAdditionTestList, example);

        //then
        assertEquals(Collections.emptyList(), badgeServiceTest.getListOfUnicornBadgesForAdditionAndSubtraction(false, example));
    }

    @Test
    void cantGetNewListOfUnicornBadgesForAddition_LessThanMaxBadges() {
        //given
        Example example = new Example();
        example.setCategory("Sčítání");

        for (int i = 0; i < MAX_BADGES - 1; i++) {
            unicornBadgesAdditionTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        badgeServiceTest.getNewListOfUnicornBadgesForAdditionAndSubtraction(unicornBadgesAdditionTestList, example);

        //then
        assertNotEquals(Collections.emptyList(), badgeServiceTest.getListOfUnicornBadgesForAdditionAndSubtraction(false, example));
    }

    @Test
    void canGetListOfUnicornBadgesForLogicOperators_badgeAdded() {
        //given
        boolean result = true;

        //when
        unicornBadgesLogicTestList = badgeServiceTest.getListOfUnicornBadgesForLogicOperators(result);

        //then
        assertFalse(unicornBadgesLogicTestList.isEmpty());
    }

    @Test
    void canGetListOfUnicornBadgesForLogicOperators_badgeRemoved() {
        //given
        boolean result = false;

        //when
        unicornBadgesLogicTestList = badgeServiceTest.getListOfUnicornBadgesForLogicOperators(result);

        //then
        assertTrue(unicornBadgesLogicTestList.isEmpty());
    }

    @Test
    void canGetNewListOfUnicornBadgesForLogicOperators_MaxBadges() {
        //given
        for (int i = 0; i < MAX_BADGES; i++) {
            unicornBadgesLogicTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        badgeServiceTest.getNewListOfUnicornBadgesForLogicOperators(unicornBadgesLogicTestList);

        //then
        assertEquals(Collections.emptyList(), badgeServiceTest.getListOfUnicornBadgesForLogicOperators(false));
    }

    @Test
    void cantGetNewListOfUnicornBadgesForLogicOperators_LessThanMaxBadges() {
        //given
        for (int i = 0; i < MAX_BADGES - 1; i++) {
            unicornBadgesLogicTestList.add(new UnicornBadgeServiceImpl.UnicornBadge());
        }

        //when
        badgeServiceTest.getNewListOfUnicornBadgesForLogicOperators(unicornBadgesLogicTestList);

        //then
        assertNotEquals(Collections.emptyList(), badgeServiceTest.getListOfUnicornBadgesForLogicOperators(false));
    }


}
