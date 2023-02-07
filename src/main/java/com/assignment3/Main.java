package com.assignment3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {


    /**
     * The main method that allows the user to choose which operation to perform:
     * search for text in a CSV file or merge multiple CSV files.
     *
     * @param args Command line arguments
     * @throws IOException If an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        CSVTool csvTool = new CSVTool();
        Scanner sc = new Scanner(System.in);
        try {
            while(true)
            {
                System.out.println("'1' to search for text\n'2' to merge files\n'3' to search statistics\n'4' to exit");
                int choice = sc.nextInt();
                try {
                    switch (choice) {
                        case 1:
                            final String windowsFilePathRegex = "^(?:[a-zA-Z]\\:|\\\\[\\w\\.]+\\\\[\\w.$]+)\\\\(?:[\\w]+\\\\)*\\w([\\w.])+$"; //Regex to match windows file path
                            System.out.println("Enter Windows file path: ");
                            String filePath = sc.next();
                            String continueChoice = "y";
                            Matcher windowsFilePathMatcher = csvTool.createMatcher(windowsFilePathRegex, filePath);

                            if (windowsFilePathMatcher.find()) {
                                
                                while(continueChoice.equals("y"))
                                {
                                    System.out.println("Enter what word to search for: ");
                                    String word = sc.next();
                                    TextSearcher textSearcher = new TextSearcher(filePath);
                                    csvTool.addSearchTermFrequencies(word);
                                    textSearcher.searchForText(word);

                                    System.out.println("Would you like to search another word? (Y/N)");
                                    continueChoice = sc.next();
                                    continueChoice = continueChoice.toLowerCase();
                                    while(!(continueChoice.equals("y") || continueChoice.equals("n")))
                                    {
                                        System.out.println("Invalid choice, try again");
                                        System.out.println("Would you like to search another word? (Y/N)");
                                        continueChoice = sc.next().toLowerCase();
                                    }
                                }
                            }
                            else {
                                throw new IllegalArgumentException("Invalid file path");
                            }
                            break;
                        case 2:
                            final String windowsDirectoryRegex = "^[a-zA-Z]:\\\\(?:\\w+\\\\?)*$"; //Regex to match windows directory
                            System.out.println("Enter what directory to search for files: ");
                            String directory = sc.next();
                            Matcher windowsDirectoryMatcher = csvTool.createMatcher(windowsDirectoryRegex, directory);

                            if (windowsDirectoryMatcher.find()) {
                                FileMerger fileMerger = new FileMerger(directory);
                                System.out.println("Enter output file name: ");
                                String fileName = sc.next();
                                try {
                                    csvTool.validateCsvFileName(fileName);
                                    System.out.println("File name is valid");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Error: " + e.getMessage());
                                    break;
                                }

                                fileMerger.mergeFiles(fileName);
                            } else {
                                throw new IllegalArgumentException("Invalid directory path");
                            }
                            break;
                        case 3:
                            System.out.println("'1' to search for a specific word's statistics\n'2' show all words' statistics");
                            choice = sc.nextInt();
                                    
                            csvTool.setPriorityQueue(new PriorityQueue<Map.Entry<String, SearchTerm>>(new Comparator<Map.Entry<String, SearchTerm>>() {
                            @Override
                            public int compare(Map.Entry<String, SearchTerm> a, Map.Entry<String, SearchTerm> b) {
                                return b.getValue().getFrequency().compareTo(a.getValue().getFrequency());}}
                            ));
                            csvTool.getPriorityQueue().addAll(csvTool.getSearchTermFrequencies().entrySet());
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            switch(choice) {
                                case 1:
                                    System.out.println("What search term do you want statistics for?");
                                    String searchTerm = sc.next();
                                    System.out.format("%-15s %10s %40s","Search term","Frequency","Last Searched Time\n\n");
                                    System.out.format("%-15s %10s %40s",csvTool.getSearchTermFrequencies().get(searchTerm).getTerm(),csvTool.getSearchTermFrequencies().get(searchTerm).getFrequency(),formatter.format(csvTool.getSearchTermFrequencies().get(searchTerm).getLastSearchedTime())+"\n\n");
                                    break;
                                case 2:
                                    System.out.format("%-15s %10s %40s","Search term","Frequency","Last Searched Time\n\n");
                                    while (!csvTool.getPriorityQueue().isEmpty()) {
                                    Map.Entry<String, SearchTerm> entry = csvTool.getPriorityQueue().poll();
                                    System.out.format("%-15s %10s %40s",entry.getKey(),entry.getValue().getFrequency(),formatter.format(entry.getValue().getLastSearchedTime())+"\n\n");
                                }
                            }
                            break;
                        case 4:
                            return;
                        default:
                            throw new IllegalArgumentException("Invalid choice");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            }
        }
        finally {
            sc.close();
        }
        
    }
}