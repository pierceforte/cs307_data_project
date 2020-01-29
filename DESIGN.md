names of all people who worked on the project
each person's role in developing the project
what are the project's design goals, specifically what kinds of new features did you want to make easy to add
describe the high-level design of your project, focusing on the purpose and interaction of the core classes
what assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline


### Name  
Pierce Forte (phf7)

### Note  
I will mention this here so I don't have to do so repeatedly throughout the document. Since I joined the class late, I was only able to fully complete the Basic requirements and the first 4 of the Complete requirements (without proper testing, however). My main() worked on the complete data set and provided printed output regarding the different questions I completed, and I handled some errors like when a name was not present in the dataset, when a matching-rank-request was not valid (rank not present in a given year), and when a given file to take data from was not valid. Finally, my program is only able to read data from files stored locally and in the same folder, but this location (as well as the years of data available) can easily be modified within the code in the "Main" or "NameCollectorTest" class's constant variables.

### Development Roles  
Since this was a single-person assignment, I completed all of the work.

### Design Goals  
My main design goals were to build an effective data structure for storing and ranking the names, and to slowly build up to the more advanced features. For example, I started off with creating a method to find a name's popularity, then used this feature to make a method for ranking the names, then made a method to find a name's rank, then made a method to find a name's overall rank in multiple years, etc. By building up from simple methods to more complex ones, adding features became easier because most of the work (the smaller pieces of the more complex function's purpose) had already been completed. As such, I wanted to make it easier to add new features that built upon previous features.

### High-level Design
The "Main" and "NameCollectorTest" classes both served as ways to gather input. Here, the files containing baby name data can be read based on a range of years (which are set in constant variables), and they are used to create a "NameCollector" object. This "NameCollector" class served as the processing aspect of the program. It creates the data structures that store the names and their popularities, and it offers the methods for answering each of the assignment questions. Finally, this class also provides output to the user when one of its methods is called (from an instance of the class) from the "Main" and "NameCollectorTest" classes; the information is printed to the screen, and since the methods also return the requested information, they can be tested within the "NameCollectorTest" class.

### Assumptions  
The initalizeNameCollector() and formatFileName() classes assume that the name files are given in the format "yob****.txt", where **** is the year. It is also assumed that the actual files are contained in the data/ssa_complete/ directory, and that the test files are contained in the data/test/ directory. This made it easier for me to design the program because I didn't have to account for the very real possibility that a file might be named differently or stored elsewhere; however, I did make it very easy for a user to adjust these things within the code by placing the assumed file paths and year ranges in constant variables.

It is assumed that for Basic question 4, the overall popularity of a starting letter is determined based on how many babies were given a name with this starting letter (regardless of the name), NOT the number of different names with this starting letter. For example, for the range 2000 to 2001, if the name Anne was given 500 times in 2000, and Jane and Jessica were each given 10 times in each of the aforementioned years, the starting letter "A" would be considered most popular: the total for "A" is 500, and the total for "J" is 40. This assumption made designing the program easier because I did not have to account for different interpretations (perhaps by adding a boolean parameter to the method and using an if tree to return the correct output); instead, I merely had to implement my understanding of the question.   

Further, an early decision I made was to create a single two-level hashmap (needed to say, for example, map.get().get() to obtain the requested data) to store all of the ranks and a single three-level hashmap to store all of the names and their popularities. The reason I chose to store the data like this was because it allowed me to store *everything* all in once place, and I had a consistent way of accessing a name's rank regardless of that name's sex or year. By taking the time to correctly implement a more complex data structure, I was able to avoid any worries later on of where the data I needed was or how to access it.


### How to Add New Features  
Since I did not do Complete requirements 5-10, I will explain how to add them here. 
