# a5-analysis
A graphical and a command line user interface combining most of what we have seen.

This assignment has two parts:
- [1. Text Analysis GUI](#1-text-analysis-gui)
- [2. Data Analysis](#2-data-analysis)

# 1. Text Analysis GUI

**Problem Statement**

Write a graphical user interface (GUI) application that allows a user to type or paste text into a text area. A button triggers measurement of number of sentences and words and these results are displayed in a separate text area within the GUI. Sentences are delimited by either period `.`, exclamation mark `!`, or question mark `?`. Words are delimited by any whitespace. A second button allows clearing the input and output text areas.

**GUI Specifications**

The GUI should prompt the user to enter text in a `JTextArea`, the input. A second `JTextArea` is used to display the results, the output. There are two `JButton` elements: `analyze` and `clear`. The former to trigger text analysis and display of the results, the latter to clear all text areas.

Many of the components can be found in `StatsGUI` used in the previous assignment. You are free to layout the components, keeping in mind that multiple lines of text are potentially entered - the size of the input text area should allow a good view of the text. 

**Design Specifications**

You need two Java classes for this project. The design follows `StatsGUI` and `Stats` classes in the previous example.

`TextAnalysisGUI` provides the GUI elements, inherits from `JFrame` and implements `ActionListener`. Its constructor sets up the elements. This class has a `main()` where a `TextAnalysisGUI` is instantiated. When the `analyze` button is clicked, a new `TextAnayzer` object is instantiated with the text entered in the input area. The results are displayed in the output area by calling the `TextAnayzer` object's `toString()` method.

`TextAnalyzer` provides a constructor that takes a String text as argument. In the constructor, the number of sentences and number of words are calculated and stored in two private instance variables `numOfSentences`, `numOfWords`. The class has two getter methods for these instance variables. The method `toString()` is overriden to return a string representation containing the two instance variables and descriptive text.

Two files `src/TextAnalysisGUI.java` and `src/TextAnalyzer.java` are created to get you started.

**Reporting**

In the markdown file `TextAnalysis.md` add the UML class diagram of `TextAnalysisGUI` and `TextAnalyzer`, their dependencies and their relationships. Add JavaDoc and comments to all your classes. Include a screenshot demonstrating successful execution and outputs of analyzing the text in `test.txt` and `feynman.txt`. For the latter, there are 1071 words and 53 sentences. 

# 2. Data Analysis

**Problem Statement**

Write a program that can calculate basic statistics of a single column of data read from a comma-separated values (csv) file.

A command line interface (CLI) program prompts the user for the csv file name. The first row in this file is the header row, following rows contain decimal numbers that can be represented as `double`. There are a maximum of 100 data rows. The number of columns may vary from file to file. Once the data is loaded, the header and the data are displayed. The user selects a column to be analyzed by entering the column index. Subsequently, the selected column header and values are printed along with basic statistics: min, max, sum and mean.

**CLI Specifications**

The dialog is already implemented in `DataAnalysisCLI`'s run method. Input/Output is performed with a `KeyboardReader`. 

The dialog implemented in `src/DataAnalysisCLI.java` uses `src/KeyboardReader.java`, both  provided. Studying code in `src/DataAnalysisCLI.java` will be helpful to understand other classes and how they interact.

**Design Specifications**

In addition to `DataAnalysisCLI`, an interface `IBasicStats` is available in `src/IBasicStats.java`. This interface defines the basic statistics methods min, max, sum and mean to be implemented.

You need two additional Java classes for this project. Both classes use the strategy for storing data up to a maximum of 100 entries that was presented in `Stats` in the previous assignment.

`DataFrame` represents a spreadsheet of maximum 100 data rows and a variable number of columns depending on the csv file. Its constructor takes a file name of a properly formated csv file. The file is parsed line-by-line using `Scanner`. The constructor throws `IOException`. It saves the header row in an instance variable `headerNames` of type String array. A getter method `getHeaderNames()` returns `headerNames`. All data rows are stored in a 2D double array. A `numOfRows` instance variable keeps track of the number of data rows, `numOfCols` contains the number of columns. There are getters for both these instance variables. The class overrides `toString()` which returns a String representation of the header names and at most 10 data rows, one row per line. The public method `getColumnByIndex(int)` takes a valid index of a column and returns a `DataSeries` object with label equal to column header name, and containing all data values.

`DataSeries` represents a 1D collection of values. The class has two instance variables `label` (a String) and `data` (an array of double). Its constructor takes a String as parameter which is assigned to `label`, and it initializes the `data` array to a size of 100. An `add()` method allows adding values and a `count` instance variable keeps track of how many elements have been added. There is a getter method `size()` that returns `count`. The class overrides `toString()` which returns a String representation of the label and at most 10 data values on a single line. This class implements the `IBasicStats` interface. `DataSeries` is used by `DataFrame` and `DataAnalysisCLI`.

**Reporting**

In the markdown file `DataAnalysis.md` add the UML class diagram of `DataAnalysisCLI`, `IBasicStats`, `DataFrame` and `DataSeries`, their dependencies and their relationships. Add JavaDoc and comments to `DataFrame` and `DataSeries`. Include a screenshot demonstrating successful execution and outputs of analyzing a column in `test.csv` and `diabetes100.csv`. Verify your outputs of these two datasets with alternate means, e.g. excel. Report on your verification.
