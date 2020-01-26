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

    public List<Integer> getRankForRangeOfYears(String sex, String name, int start, int end) {
        List<Integer> popularityList = new ArrayList<>();
        System.out.println("Rank for " + name + ", " + sex + " from " + start + " to " + end + ":");
        for (int year = start; year <= end; year++) {
            int rank = getRankBySexYearAndName(sex, year, name);
            popularityList.add(rank);
            System.out.println(year + ": " + rank);
        }
        System.out.println("\n");
        return popularityList;
    }

    public List<Integer> getRankForAllYears(String sex, String name) {
        return getRankForRangeOfYears(sex, name, minYear, maxYear);
    }

    public String getMatchingRankInMostRecentYear(String sex, int year, String name) {
        // subtract 1 to get rank index
        int rankIndex = getRankBySexYearAndName(sex, year, name) - 1;
        String match = getNameBySexYearAndRank(sex, maxYear, rankIndex);
        System.out.println("The name in the most recent year (" + maxYear + ") with same rank (" + (rankIndex+1) +
                        ") as " + name + ", " + sex + " in " + year + " is:\n" + match + "\n");
        return match;
    }

    public List<String> getMostFrequentTopRankedNameInRangeOfYears(String sex, int start, int end) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (int year = start; year <= end; year++) {
            String topRank = rankedNames.get(sex).get(year).get(0);
            frequencyMap.putIfAbsent(topRank, 0);
            frequencyMap.put(topRank, frequencyMap.get(topRank)+1);
        }

        List<String> mostFrequentTopRanks = new ArrayList<>();
        List<String> topRanks = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(topRanks, Comparator
                .comparing((String name) -> frequencyMap.get(name))
                .reversed()
                .thenComparing((String name) -> name));
        int highestFrequency = 0;
        for (String topRank : topRanks) {
            if (frequencyMap.get(topRank) >= highestFrequency) {
                highestFrequency = frequencyMap.get(topRank);
                mostFrequentTopRanks.add(topRank);
            }
            else break;
        }

        Collections.sort(mostFrequentTopRanks);
        System.out.println("Between " + start + " and " + end + ", the following name(s) held the top rank for "
                + highestFrequency + " years.");
        printAllCollectionElements(mostFrequentTopRanks);
        return mostFrequentTopRanks;
    }

    public Set<String> getMostPopularStartingLetterForSexInRangeOfYears(String sex, int start, int end) {
        int [] tracker = new int [26];
        for (int year = start; year <= end; year++) {
            for (String name : names.get(sex).get(year).keySet()) {
                tracker[name.toLowerCase().charAt(0)-97] += getPopularityForYear(sex, year, name);
            }
        }
        char mostPopularLetter = '.';
        int highestCount = -1;
        for (int i = 0; i < tracker.length; i++) {
            if (tracker[i] > highestCount) {
                mostPopularLetter = (char) (97+i);
                highestCount = tracker[i];
            }
        }

        Set<String> namesStartingWithMostPopularLetter = new TreeSet<>(Comparator.comparing((String name) -> name));
        for (int year = start; year <= end; year++) {
            for (String name : names.get(sex).get(year).keySet()) {
                if (name.toLowerCase().charAt(0) == mostPopularLetter) namesStartingWithMostPopularLetter.add(name);
            }
        }

        System.out.println("Between " + start + " and " + end + ", the following name(s) of sex " + sex
                + " had the most popular starting letter.");
        printAllCollectionElements(namesStartingWithMostPopularLetter);
        return namesStartingWithMostPopularLetter;
    }

    private int getRankBySexYearAndName(String sex, int year, String name) {
        int rank = rankedNames.get(sex).get(year).indexOf(name);
        if (rank == -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Name \"" + name + "\" with sex " + sex + " not present in data for year " + year + ".\n");
                e.printStackTrace();
            }
        }
        return rank+1;
    }

    private String getNameBySexYearAndRank(String sex, int year, int rank) {
        String name = "";
        try {
            name =  rankedNames.get(sex).get(year).get(rank);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("No name for rank " + rank + " in year " + year + " for sex " + sex + ".\n");
        }
        return name;
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

    private void printAllCollectionElements(Collection collection) {
        for (Object obj : collection) System.out.println(obj);
        System.out.println("\n");
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
