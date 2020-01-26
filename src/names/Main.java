package names;


import java.util.ArrayList;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {

    public static String [] YEAR_NAME_FILE_FORMAT = {"data/ssa_complete/yob",".txt"};

    private static NameCollector nameCollector;
    /**
     * Start of the program.
     */
    public static void initializeNameCollector() {
        List<String> yearNameFiles = new ArrayList<>();
        for (int year = 1880; year <= 2018; year++) {
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
         */
        //nameCollector.getRankForAllYears(NameCollector.MALE, "Pierce");
        //nameCollector.getMatchingRankInMostRecentYear(NameCollector.MALE, 2000, "Pierce");
        //nameCollector.getMostFrequentTopRankedNameInRangeOfYears(NameCollector.FEMALE, 1945, 1948);
        //nameCollector.getMostPopularStartingLetterForSexInRangeOfYears(NameCollector.FEMALE, 1890, 2018);
    }
}
