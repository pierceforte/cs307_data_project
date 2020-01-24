package names;


import java.util.ArrayList;
import java.util.Arrays;
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
    public static void main (String[] args) {
        initializeNameCollector();
        //nameCollector.getPopularityForRangeOfYears("M", "Pierce", 2000, 2018);
    }

    private static String formatNameFile(String [] format, int year) {
        return (format[0] + year + format[1]);
    }
}
