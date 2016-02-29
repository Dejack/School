/**
 *
 * @author A00937960 : Manben Chen
 */

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;;

public class SpellChecker {
    
    private static final String wordFileName = "lab4_wordlist.txt";
    private static final String testFileName = "lab4_testdata.txt";
    
    public static void main(String[] args) {
        
        
    	ArrayList<String> wordFile = new ArrayList<String>(), 
    					  testFile = new ArrayList<String>();
    	
        try {
        	// Open files
	        Scanner wordListScanner = new Scanner(new File(wordFileName));
	        Scanner testFileScanner = new Scanner(new File(testFileName));
	        
	        // Populate arraylists with file content
	        while(wordListScanner.hasNext()) {
	        	wordFile.add(wordListScanner.next());
	        }
	        while(testFileScanner.hasNext()) {
	        	testFile.add(testFileScanner.next());
	        }
	        wordListScanner.close();
	        testFileScanner.close();
	        
        } catch(Exception e) {
        	
        	System.out.println("Error with file.");
        }
        
        long seqSearchTime = System.nanoTime();
        System.out.print("Sequential Search: " + seqSearch(wordFile, testFile) + " words ");
        long seqSearchDuration = System.nanoTime() - seqSearchTime;
        System.out.println(seqSearchDuration/1000 + " microseconds");
        
        long binarySearchTime = System.nanoTime();
        int words_not_found = 0;
        for (String x : testFile) {
        	if(!binarySearch(wordFile, x)) {
        		words_not_found++;
        	}
        }
        long binarySearchDuration = System.nanoTime() - binarySearchTime;
        System.out.println("Binary Search: " + words_not_found + " words " + binarySearchDuration/1000 + " microseconds");
        
    }
    public static int seqSearch(ArrayList<String> wordList, ArrayList<String> testFile) {
        int words_not_found = 0;      
        for (int i = 0; i < testFile.size(); i++) {
        	boolean wordNotFound = true;
        	String testWord = testFile.get(i);
        	for (int k = 0; k < wordList.size(); k++) {
        		if (wordList.get(k).equalsIgnoreCase(testWord)) {
        			wordNotFound = false;
        			break;
        		}
        	}
        	if (wordNotFound) {
        		words_not_found++;
        	}
        
        }
        return words_not_found;
    }
    
    public static boolean binarySearch(ArrayList<String> wordList, String key) {
    	int index = 0;
    	int size = wordList.size();
    	while (index <= size) {
    		int middle = (index + size)/2;
    		if (wordList.get(middle).equalsIgnoreCase(key)) {
    			return true;
    		} else if (wordList.get(middle).compareToIgnoreCase(key) < 0) {
    			index = middle + 1;
    		} else {
    			size = middle - 1;
    		}
    	}
    	return false;
    }
    
}


