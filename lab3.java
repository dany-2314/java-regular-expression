import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lab3 {
	
	public static void main(String[] args) {
		//getting tweets path
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter path folder which contains tweets");
		String Tweets_path = obj.nextLine();
		
		//getting hashtag path
		Scanner obj2 = new Scanner(System.in);
		System.out.println("\nEnter path folder which contains tweets");
		String hashtag_path = obj2.nextLine();
		
		//exeception handling
		try {
		 	File f1 = new File(Tweets_path); // tweets file(directory)
	 
		 	if (f1.exists() && f1.isDirectory()) {
		 		
			 	File [] files = f1.listFiles();
			 	//looping through each file now
			 	for (File i : files) {
			 		// for each file create a new file (for each person)
			 		File f2 = new File(hashtag_path + "\\" + "Tags" + i.getName()); // file to store hashtags
			 		
				 	if (f2.createNewFile()){
//				 		System.out.println("Creating File...\n");
				 	} else {
					 	System.out.println("File Already Exists, Storing data in the specified file...");
					 }
				 	// making file writeable
				 	FileWriter hash_file = new FileWriter(hashtag_path + "\\" + "Tags" + i.getName(),true);
				 	// making file readable
				 	BufferedReader tweet_file = new BufferedReader(new FileReader(i));
				 	
				 	// Using Regex to extract data
				 	String line;
				 	 while ((line = tweet_file.readLine()) != null) {
				 	       Pattern pattern = Pattern.compile("#([0-9]|\\w|_)*");
				 	       Matcher matcher = pattern.matcher(line);   
				 	      while (matcher.find()) {   
				 	    	   // write it to file
				 	    	hash_file.write(matcher.group() + "\n");
				 	     }
				 	  }
				      tweet_file.close();
				      hash_file.close();
			 	}
		 	}
		 	else 
		 		System.out.println("either file DNE or file is not a directory to read text files");
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();  
			} 
		
	}
}
