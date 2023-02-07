# CSV File Merger & Text Searcher
A simple Java program that merges multiple CSV files into a single file and searches for a specific word in a CSV file.

## Features
Merges multiple CSV files in a directory into a single file.
Searches for a specific word in a CSV file.
Validates the output file name to ensure it ends with .csv.
Validates the file path and directory to ensure it follows the Windows file path format.
## Requirements
Java 8 or later.
## Usage
1. Clone the repository to your local machine:
`git clone https://github.com/atsay714/Assignment3/tree/master`

2. Compile and run the program

3. Select either to search for a word, merge files, show search statistics, or exit:
```
'1' to search for text

'2' to merge files

'3' to search statistics

'4' to exit
```
4. If you choose to search for a word:

`Enter Windows file path:`

Enter the file path of the CSV file you want to search. The file path must follow the Windows file path format.

`Enter what word to search for:`

Enter the word you want to search for in the CSV file.

5. If you choose to merge files:

`Enter what directory to search for files:`

Enter the directory where the CSV files you want to merge are located. The directory must follow the Windows file path format.

`Enter output file name:` 

Enter the output file name. The file name must end with .csv.

6. If you choose to search for statistics:

```
'1' to search for a specific word's statistics
'2' show all words' statistics
```

You can choose to either search for 1 word's statistics, or show all words' statistics.
