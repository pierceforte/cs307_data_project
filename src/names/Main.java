package names;


import java.util.ArrayList;
import java.util.List;

// this class represents the INPUT stage of IPO, as well as the potential OUTPUT
public class Main {

    public static int FIRST_YEAR_OF_DATA = 1880;
    public static int LAST_YEAR_OF_DATA = 2018;
    public static String [] YEAR_NAME_FILE_FORMAT = {"data/ssa_complete/yob",".txt"};

    private static NameCollector nameCollector;

    public static void initializeNameCollector() {
        List<String> yearNameFiles = new ArrayList<>();
        for (int year = FIRST_YEAR_OF_DATA; year <= LAST_YEAR_OF_DATA; year++) {
            yearNameFiles.add(formatNameFile(YEAR_NAME_FILE_FORMAT, year));
        }
        nameCollector = new NameCollector(yearNameFiles);
    }

    // made public to utilize within JUnit tests
    public static String formatNameFile(String [] format, int year) {
        return (format[0] + year + format[1]);
    }

    public static void main (String[] args) {
        initializeNameCollector();
        /**
         * Basic questions 1-4 can be answered with the following calls (which contain example inputs)
         * Given the inclusion of print statements in their methods, this is where the OUTPUT of IPO
         * can be executed.
         */
        //nameCollector.getRankForAllYears(NameCollector.MALE, "Pierce");
        //nameCollector.getMatchingRankInMostRecentYear(NameCollector.MALE, 2000, "Pierce");
        //nameCollector.getMostFrequentTopRankedNameInRangeOfYears(NameCollector.FEMALE, 1945, 1948);
        //nameCollector.getMostPopularStartingLetterForSexInRangeOfYears(NameCollector.FEMALE, 1890, 2018);
        /**
         * Final questions 1, 2, and 4 can be answered with the following calls (which contain example inputs). Note
         * that I did not have time to implement JUnit tests.
         */
        //nameCollector.getRankForRangeOfYears(NameCollector.MALE, "Pierce", 1880, 2018);
        //nameCollector.getDifferenceInRankBetweenFirstAndLastYearsForNameAndSex(NameCollector.MALE, "Pierce");
        nameCollector.getAverageRankInRangeOfYears(NameCollector.MALE, "Pierce", 2000, 2018);
    }
}
