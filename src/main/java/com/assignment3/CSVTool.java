package com.assignment3;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The MergeCSV class provides methods to perform search operation on a CSV file
 * and merge multiple CSV files in a directory.
 */
public final class CSVTool {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    public CSVTool() {
        priorityQueue = new PriorityQueue<Map.Entry<String, SearchTerm>>(new Comparator<Map.Entry<String, SearchTerm>>() {
        @Override
        public int compare(Map.Entry<String, SearchTerm> a, Map.Entry<String, SearchTerm> b) {
          return b.getValue().getFrequency().compareTo(a.getValue().getFrequency());
        }
      });
        searchTermFrequencies = new HashMap<String,SearchTerm>();
    }

    private PriorityQueue<Map.Entry<String, SearchTerm>> priorityQueue;

    private HashMap<String,SearchTerm> searchTermFrequencies;

    public HashMap<String,SearchTerm> getSearchTermFrequencies() {
        return searchTermFrequencies;
    }

    public void addSearchTermFrequencies(String searchTerm) {
        Date date = new Date();  

        if (searchTermFrequencies.containsKey(searchTerm)) 
        {
            searchTermFrequencies.put(searchTerm, new SearchTerm(searchTerm,searchTermFrequencies.get(searchTerm).getFrequency() + 1, date));
        }
        else
        {
            searchTermFrequencies.put(searchTerm, new SearchTerm(searchTerm,1,date));
        }
    }

    public PriorityQueue<Map.Entry<String, SearchTerm>> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(PriorityQueue<Map.Entry<String, SearchTerm>> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    /**
     * This method creates a Matcher for the specified regex string and input string.
     *
     * @param regexString The regex string to be compiled into a Pattern.
     * @param input The input string to be matched against the regex string.
     *
     * @return A Matcher instance that can be used to match the input string against the regex string.
     */
    public Matcher createMatcher(String regexString, String input) {
        String windowsDirectoryRegex = regexString;
        Pattern windowsDirectoryPattern = Pattern.compile(windowsDirectoryRegex);
        return windowsDirectoryPattern.matcher(input);
    }

    /**
     * This method checks if the given filename ends with the ".csv" extension.
     *
     * @param filename The name of the file to validate.
     *
     * @throws IllegalArgumentException if the filename does not end with ".csv".
     */
    public void validateCsvFileName(String filename) throws IllegalArgumentException {
        if (!filename.endsWith(".csv")) {
            throw new IllegalArgumentException("File name must end in .csv");
        }
    }
}