data
====

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 


Name: Pierce Forte (phf7)

### Timeline

Start Date: January 22 (when I joined the class)

Finish Date: January 26

Hours Spent: 15+

### Resources Used  
The main resources I used were Piazza as well as the given tutorials/readings (the JUnit one was particularly helpfull).

### Running the Program

Main class:   
The main class can be used to create a NameCollector object, using the "yob1880.txt" through "yob2018.txt" files in "data/ssa_complete/". From here, this object can be used to gather information listed in the basic requirements, using the methods "expectedRankForAllYears", "getMatchingRankInMostRecentYear", "getMostFrequentTopRankedNameInRangeOfYears", and "getMostPopularStartingLetterForSexInRangeOfYears". It can also gather information from the first four questions, using the methods "getRankForRangeOfYears", "getDifferenceInRankBetweenFirstAndLastYearsForNameAndSex", "getNameWithGreatestDifferenceInRankBetweenTwoYears", and "getAverageRankInRangeOfYears".

Beyond these requirements, *all* years and names in this data set can be accurately explored, and error handling is in place for when a user-inputed file, name, or matching-rank-request (question 2) is not valid.

Data files needed: 
The "yob1880.txt" through "yob2018.txt" files must all exist in "data/ssa_complete/". If these files exist in a different directory, the YEAR_NAME_FILE_FORMAT constant can be modified in the Main class. Likewise, if the starting year and ending year are not 1880 and 2018, respectively, then FIRST_YEAR_OF_DATA and LAST_YEAR_OF_DATA constants can also be modified in the Main class.  

Note that the same applies to the NameCollectorTest class found within "src/test/", although these constants are named slightly differently, and the test data files are found in "data/test/". 

Key/Mouse inputs:

Cheat keys:

Known Bugs:

Extra credit:  
Given that I joined late, the goal was to complete the basic requirements. My implementation of "final" questions 1-4, a main() method that works on the full data set, and error handling could be considered "extra".


### Notes/Assumptions
Note that after speaking with Dr. Duvall, it was only expected that I show "good faith" and complete the basic requirements given that I joined this class late. I hope that my work is sufficient and that my attempts to go beyond the basic requirements were successful.

The initalizeNameCollector() and formatFileName() classes assume that the name files are given in the format "yob****.txt", where **** is the year. It is also assumed that the actual files are contained in the data/ssa_complete/ directory, and that the test files are contained in the data/test/ directory.

It is assumed that for question 4, the overall popularity of a starting letter is determined based on how many babies were given a name with this starting letter (regardless of the name), *NOT* the number of *different* names with this starting letter. For example, for the range 2000 to 2001, if the name Anne was given 500 times in 2000, and Jane and Jessica were each given 10 times in each of the aforementioned years, the starting letter "A" would be considered most popular: the total for "A" is 500, and the total for "J" is 40.

### Impressions   
Although I did not have much time to work on this project, I think that it went well and that I learned a lot. I am glad that I was able to understand the concepts/data structures involved, and I believe that I made a good effort to write clean code.
