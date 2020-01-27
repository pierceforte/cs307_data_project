package test;

import names.Main;
import names.NameCollector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// this class represents both the INPUT and OUTPUT stages of IPO
class NameCollectorTest {

    public static int FIRST_YEAR_OF_TEST_DATA = 2000;
    public static int LAST_YEAR_OF_TEST_DATA = 2002;
    public static final String [] TEST_YEAR_NAME_FILE_FORMAT = {"data/test/yob",".txt"};
    @Test
    public void name() throws Exception {
        List<String> yearNameFiles = new ArrayList<>();
        for (int year = FIRST_YEAR_OF_TEST_DATA; year <= LAST_YEAR_OF_TEST_DATA; year++) {
            yearNameFiles.add(Main.formatNameFile(TEST_YEAR_NAME_FILE_FORMAT, year));
        }
        NameCollector nameCollector = new NameCollector(yearNameFiles);

        testBasicQuestion1(nameCollector, NameCollector.MALE, "James", List.of(1,2,3));
        testBasicQuestion1(nameCollector, NameCollector.MALE, "Richard", List.of(3,3,2));

        testBasicQuestion2(nameCollector, NameCollector.MALE, 2000, "James","William");
        testBasicQuestion2(nameCollector, NameCollector.MALE, 2001, "James","Richard");

        testBasicQuestion3(nameCollector, NameCollector.FEMALE, 2000, 2002, List.of("Anne"));
        testBasicQuestion3(nameCollector, NameCollector.FEMALE, 2000, 2001, List.of("Anne", "Jane"));

        testBasicQuestion4(nameCollector, NameCollector.FEMALE, 2000, 2001, Set.of("Anne"));
        testBasicQuestion4(nameCollector, NameCollector.FEMALE, 2000, 2002, Set.of("Jane","Jessica","Julia"));


    }

    private void testBasicQuestion1(NameCollector nameCollector, String sex, String name, List<Integer> actualRankForAllYears) {
        List<Integer> expectedRankForAllYears = nameCollector.getRankForAllYears(sex, name);
        Assertions.assertEquals(expectedRankForAllYears, actualRankForAllYears);
    }

    private void testBasicQuestion2(NameCollector nameCollector, String sex, int year, String name, String actualMatchingName) {
        String expectedMatchingName = nameCollector.getMatchingRankInMostRecentYear(sex, year, name);
        Assertions.assertEquals(expectedMatchingName, actualMatchingName);
    }

    private void testBasicQuestion3(NameCollector nameCollector, String sex, int start, int end, List<String> actualMostFrequentNames) {
        List<String> expectedMostFrequentNames = nameCollector.getMostFrequentTopRankedNameInRangeOfYears(sex, start, end);
        Assertions.assertEquals(expectedMostFrequentNames, actualMostFrequentNames);
    }

    private void testBasicQuestion4(NameCollector nameCollector, String sex, int start, int end, Set<String> actualMostFrequentNames) {
        Set<String> expectedMostFrequentNames = nameCollector.getMostPopularStartingLetterForSexInRangeOfYears(sex, start, end);
        Assertions.assertEquals(expectedMostFrequentNames, actualMostFrequentNames);
    }
}