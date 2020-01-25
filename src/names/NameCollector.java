package names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NameCollector {

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    // maps from sex to map of year to map of name to popularity
    private HashMap<String, HashMap<Integer, HashMap<String, Integer>>> names = new HashMap<>();
    // maps from sex to map of year to list of names ordered by rank
    private HashMap<String, HashMap<Integer, List<String>>> rankedNames = new HashMap<>();

    private int maxYear = Integer.MIN_VALUE;
    private int minYear = Integer.MAX_VALUE;


    public NameCollector(List<String> yearNameFiles) {
        putSexKeysIntoMap(names);
        putSexKeysIntoMap(rankedNames);
        for (String yearNameFile : yearNameFiles) {
            int year = extractYear(yearNameFile);
            initializeNameMap(year);
            if (year > maxYear) maxYear = year;
            if (year < minYear) minYear = year;
            scanFile(yearNameFile, year);
        }
        rankNames();
    }

    public int getPopularityForYear(String sex, int year, String name) {
        return names.get(sex).get(year).get(name);
    }

    public List<Integer> getPopularityForRangeOfYears(String sex, String name, int start, int end) {
        List<Integer> popularityList = new ArrayList<>();
        System.out.println("Popularity for " + name + ", " + sex + " from " + start + " to " + end + ":");
        for (int year = start; year <= end; year++) {
            int popularity = getPopularityForYear(sex, year, name);
            popularityList.add(popularity);
            System.out.println(year + ": " + popularity);
        }
        return popularityList;
    }

    public List<Integer> getPopularityForAllYears(String sex, String name) {
        return getPopularityForRangeOfYears(sex, name, minYear, maxYear);
    }

    public String getMatchingRankInMostRecentYear(String sex, int year, String name) {
        // subtract 1 to get rank index
        int rankIndex = getRankBySexYearAndName(sex, year, name) - 1;
        return getNameBySexYearAndRank(sex, maxYear, rankIndex);
    }

    public int getRankBySexYearAndName(String sex, int year, String name) {
        int rank = rankedNames.get(sex).get(year).indexOf(name);
        if (rank == -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Name \"" + name + "\" with sex " + sex + " not present in data for year " + year + ".");
                e.printStackTrace();
            }
        }
        //rank = (rank == -1 ? -1 : rank+1);
        //System.out.println(rank);
        return rank+1;
    }

    public String getNameBySexYearAndRank(String sex, int year, int rank) {
        String name = "";
        try {
            name =  rankedNames.get(sex).get(year).get(rank);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No name for rank " + rank + " in year " + year + " for sex " + sex);
        }
        return name;
    }

    public void printRanks(String sex, int year) {
        int i = 0;
        for (String name : rankedNames.get(sex).get(year)) {
            i++;
            System.out.println(i + ": " + name + ",    " + getPopularityForYear(sex, year, name));
        }
    }

    private void rankNames() {
        for (String sex : names.keySet()) {
            for (int year : names.get(sex).keySet()) {
                // rank the names first by popularity and then, in the case of ties, alphabetically
                List<String> ranks = new ArrayList<>();
                for (String name : names.get(sex).get(year).keySet()) {
                    ranks.add(name);
                }
                Collections.sort(ranks,Comparator
                        .comparing((String name) -> getPopularityForYear(sex, year, name))
                        .reversed()
                        .thenComparing((name) -> name));
                rankedNames.get(sex).put(year, ranks);
            }
        }
    }

    private void putSexKeysIntoMap(HashMap map) {
        map.put(MALE, new HashMap<>());
        map.put(FEMALE, new HashMap<>());
    }

    private int initializeNameMap(int year) {
        names.get(MALE).put(year, new HashMap<>());
        names.get(FEMALE).put(year, new HashMap<>());
        return year;
    }

    private int extractYear(String yearNameFile) {
        String [] slashSplit = yearNameFile.split("/");
        return Integer.parseInt(slashSplit[slashSplit.length - 1].split("\\.")[0].split("yob")[1]);
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
