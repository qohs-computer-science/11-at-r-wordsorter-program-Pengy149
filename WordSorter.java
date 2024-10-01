/*
 * Name: Kevin Zheng 
 * Date: 10/1/2024 
 * Class Period: 3 
 * Class Name: WordSorter 
 * Program Description: This program first gets input in the form of an article text file. Next, the 
 * program goes through the article and removes punctuation and other symbols, leaving only words 
 * and then adds unique words that aren't in the list yet to the list of words. 
 * Then, the list is sorted and the program asks the user for an input in the form of a 
 * number with the number corresponding to an action. The program only continues if the user enters
 * a valid number. After that, the action is fulfilled and the program asks the user for an input 
 * again, which creates this loop that continues until the exit action is done, which is when the 
 * program ends. The user can do 5 actions other than exit the loop - print out all words in the 
 * list with a specific letter, print out all words in the list, print out the total number of 
 * unique words in the list, determine if a specific word is in the list, and remove a specific word 
 * from the list. 
 */

// File is imported in order to be able to use the article text file. 
import java.io.File;

// ArrayList is imported in order to be able to create an "infinite" list. 
import java.util.ArrayList;

// Scanner is imported in order to be able to get user input. 
import java.util.Scanner;

public class WordSorter 
{
	public static void main(String[] args) 
	{
		Scanner userIn = new Scanner(System.in); 
		ArrayList <String> listW = new ArrayList<String>();
		String letter;
		boolean ifEmpty = true, exit = false, added = false;
		int number = 0;

		try
		{
			userIn = new Scanner(new File("article.txt"));	
		} // end try 
		catch(Exception e)
		{
			System.out.println("Cannot find file... Exiting Program");
			return;
		} // end catch 
		String word = "";
		while(userIn.hasNext())
		{
			word = userIn.next();
			word = word.replace(",", "");
			word = word.replace("(tm)", "");
			word = word.replace(")", "");
			word = word.replace("(", "");
			word = word.replace(".", "");
			word = word.replace("'s", "");
			word = word.replace(":", "");
			word = word.replace("!", "");
			word = word.replace("?", "");
			word = word.replace("'", "");
			
			for (int i = 0; i < listW.size(); i++) {
				if(word.toLowerCase().equals(listW.get(i)))
					added = true;
			} // end for loop 
			if(added == false)
				listW.add(word.toLowerCase());
			added = false;
			
		} // end while loop
		
		listW.sort(null);
		userIn = new Scanner(System.in); 
		
		while (exit == false) {
			while (number <= 0 || number > 6) {
				System.out.println("Choose an option (input the number for the corresponding action): ");
				System.out.println ("1. Print out all words starting with a specific letter");
				System.out.println ("2. Print out all words");
				System.out.println ("3. Print out the total number of unique words"); 
				System.out.println ("4. Search and determine if a word is in the article");
				System.out.println ("5. Remove a word from the data structure");
				System.out.println ("6. Exit ");
				number = userIn.nextInt();
				userIn.nextLine();
			} // end while loop 
			
			if(number == 1) {
				System.out.println("Enter a specific letter: ");
				letter = userIn.nextLine();

				for (int i = 0; i < listW.size(); i++) {
				if ((listW.get(i).charAt(0) + "").equals(letter)) {
					System.out.print(listW.get(i) + " ");
					ifEmpty = false;
				} // end if statement
				} // end for loop
				if(ifEmpty == true)
				System.out.print("No words start with this letter");
				System.out.println("");
			} // end if statement 

			else if (number == 2) {
				if(listW.size() == 0) 
				System.out.println("Empty List");
				else {
				letter = listW.get(0).charAt(0) + "";
				System.out.print(letter + ": " + listW.get(0) + " ");
				} // end else statement 

				for(int i = 0; i < listW.size()-1; i++) {
				if(listW.get(i).charAt(0) == listW.get(i+1).charAt(0)) 
					System.out.print(listW.get(i+1) + " ");
				else {
					System.out.println("");
					letter = listW.get(i+1).charAt(0) + "";
					System.out.print(letter + ": " + listW.get(i+1) + " ");
				} // end else statement 
				} // end for loop 
				System.out.println("");
			} // end else if statement

			else if (number == 3) {
				if(listW.size() > 0)
				System.out.println("There are " + listW.size() + " unique words in the article");
				else 
				System.out.println("Empty List");
			} // end else if statement 

			else if (number == 4) {
				System.out.println("Enter a specific word: ");
				word = userIn.nextLine();

				for (int i = 0; i < listW.size(); i++) 
				if (listW.get(i).equals(word)) 
					ifEmpty = false;

				if(ifEmpty == true)
				System.out.println("Word NOT found in the article");
				else
				System.out.println("Word found in the article");
			} // end else if statement 

			else if (number == 5) {
				System.out.println("Enter a specific word to remove: ");
				word = userIn.nextLine();

				for (int i = 0; i < listW.size(); i++) {
				if (listW.get(i).equals(word)) {
					listW.remove(i);
					ifEmpty = false;
				} // end if statement
				} // end for loop 

				if(ifEmpty == true)
				System.out.println("Word NOT found in the article");
				else 
				System.out.println("Word successfully removed from the list");
			} // end else if statement 

			else 
				exit = true;
			number = 0;
			ifEmpty = true;
		
		} // end while loop 
	} // end main 
} // end class 