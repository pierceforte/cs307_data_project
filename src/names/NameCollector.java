package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NameCollector {

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    // maps from sex to map of year to map of name to popularity
    private HashMap<String, HashMap<Integer, HashMap<String, Integer>>> names = new HashMap<>();
    // maps from year to sex to list of names in order by rank
    private HashMap<Integer, HashMap<String, List<String>>> rankedNames = new HashMap<>();

    private int maxYear = Integer.MIN_VALUE;
    private int minYear = Integer.MAX_VALUE;


    public NameCollector(List<String> yearNameFiles) {
        initializeMap(names);
        for (String yearNameFile : yearNameFiles) {
            int year = extractYearAndCreateMap(yearNameFile);//, names);
            if (year > maxYear) maxYear = year;
            if (year < minYear) minYear = year;
            scanFile(yearNameFile, year);
        }
    }

    public int getPopularity(int year, String sex, String name) {
        return names.get(sex).get(year).get(name);
    }

    public List<Integer> getPopularityForRangeOfYears(String sex, String name, int start, int end) {
        List<Integer> popularityList = new ArrayList<>();
        System.out.println("Popularity for " + name + ", " + sex + " from " + start + " to " + end + ":");
        for (int year = start; year <= end; year++) {
            int popularity = getPopularity(year, sex, name);
            popularityList.add(popularity);
            System.out.println(year + ": " + popularity);
        }
        return popularityList;
    }

    public List<Integer> getPopularityForAllYears(String sex, String name) {
        return getPopularityForRangeOfYears(sex, name, minYear, maxYear);
    }

    public List<Integer> getMatchingRankInMostRecentYear() {
        return null;
    }
/*
    private void rankNames() {
        for (String sex : names.keySet()) {
            for (int year : names.get(sex).keySet()) {
               Set<String> ranks = new TreeSet<>(
                       Comparator
                               .comparing((String name) -> names.get(sex).get(year).get(name))
                               .reversed()
                               .thenComparing((name) -> name));
                for (String name : names.get(sex).get(year).keySet()) {
                    ranks.add(name);
                }
                rankedNames.put()
            }
        }
    }
*/
    private int extractYearAndCreateMap(String yearNameFile){// HashMap<String, HashMap<Integer, HashMap>> map) {
        int year = extractYear(yearNameFile);
        names.get(MALE).put(year, new HashMap<>());
        names.get(FEMALE).put(year, new HashMap<>());
        return year;
    }

    private int extractYear(String yearNameFile) {
        String [] slashSplit = yearNameFile.split("/");
        return Integer.parseInt(slashSplit[slashSplit.length - 1].split("\\.")[0].split("yob")[1]);
    }

    private void initializeMap(HashMap map) {
        map.put(MALE, new HashMap<>());
        map.put(FEMALE, new HashMap<>());
    }

    private void scanFile(String yearNameFile, int year) {
        try {
            File file = new File(yearNameFile);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] cleanRow = data.split(",");
                String sex = cleanRow[1]; String name = cleanRow[0];
                int popularity = Integer.parseInt(cleanRow[2]);
                names.get(sex).get(year).put(name, popularity);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading name file: " + yearNameFile);
            e.printStackTrace();
        }
    }
}
