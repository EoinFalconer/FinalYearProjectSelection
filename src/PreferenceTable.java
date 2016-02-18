/*
 * Eoin Falconer
 * 13331016
 * Assignment One - File I/O
 */

import java.io.*;
import java.util.*;

public class PreferenceTable {
	Hashtable<Integer,StudentEntry> studentLookUp = new Hashtable<Integer,StudentEntry>();
	public PreferenceTable(String file){
		Vector<Vector<String>> v = this.loadContentFromFile(file);
		this.createStudentEntries(v);	//makes all the studentEntry objects and puts them in the hashtable
	}
	public PreferenceTable(){
		//empty constructor
	}
	private Vector<Vector<String>> loadContentFromFile(String file){
		Vector<Vector<String>> v = new Vector<Vector<String>>();	//make the vector to hold the other vectors
		try{
			FileInputStream inputFile = new FileInputStream(file); //create the stream from the file
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputFile)); //make the buffered reader to read in each line
			String line;
			while((line = inputReader.readLine()) != null){ 	//go through the lines until there is no line left
				String[] tempArray = line.split("\t");			//split the array, don't like tokens.
				Vector<String> tempVect = new Vector<String>(Arrays.asList(tempArray));	//changing from String[] to Vector<String> type
				v.add(tempVect);							//add that new vector as an element in v
			}
			inputReader.close();	//close the stream.
		}catch(Exception e){
			System.out.println(e.getMessage());		//tell me the exception should it occur
		}
		return v;		//return my vector
	}	
	
	private void createStudentEntries(Vector<Vector<String>> v){
		for(int i=1;i<v.size();i++){ 	//i set to 1 as the first row is the row which tells you what the column means
			Vector<String> tempVect = v.get(i);
			StudentEntry tempStu = new StudentEntry(tempVect.get(0));
			tempStu.setHasPreassigned(tempVect.get(1));
			String[] arrayOfPref = new String[tempVect.size() - 2];
			int arrayIterator = 0;
			for(int j=2;j<tempVect.size();j++){
				arrayOfPref[arrayIterator] = (String)tempVect.get(j);
				arrayIterator++;
			}
			tempStu.setOriginalPreferences(arrayOfPref);
			
			//TestingCode
			/*System.out.println(tempStu.getOrderedPreferences());
			System.out.println(tempStu.getNumberOfStatedPreferences());
			System.out.println(tempStu.hasPreassignedProject());
			System.out.println("");*/
			
			studentLookUp.put(i-1,tempStu); 
		}
	}
	public StudentEntry[] getAllStudentEntries(){
		StudentEntry[] allEntries = new StudentEntry[studentLookUp.size()];
		for(int i=0;i<studentLookUp.size();i++){
			allEntries[i] = studentLookUp.get(i);
		}
		return allEntries;
	}
	public StudentEntry getEntryFor(int studentID){		//We were told to use integers as IDs to avoid key duplication collisions
		StudentEntry studentRequested = studentLookUp.get(studentID);
		if(studentRequested != null){
			return studentRequested;
		}else{
			return null;
		}
	}
	/*
	 * Testing Code (main)
	 */
	public static void main(String[] args){
		PreferenceTable p = new PreferenceTable("Project_allocation_data.txt");
		System.out.println(p.getEntryFor(0).getStudentName()); //Loki Laufeyson
		System.out.println(p.getEntryFor(0).getOrderedPreferences() + "\n");
		System.out.println(p.getEntryFor(1).getStudentName()); //Richard B. Riddick
		System.out.println(p.getEntryFor(1).getOrderedPreferences() + "\n");
		System.out.println(p.getEntryFor(2).getStudentName()); //Alan Turing
		System.out.println(p.getEntryFor(2).getOrderedPreferences() + "\n");
		System.out.println(p.getEntryFor(3).getStudentName()); //Conan the Barbarian
		System.out.println(p.getEntryFor(3).getOrderedPreferences() + "\n");
		System.out.println(p.getEntryFor(4).getStudentName()); //Red Sonja
		System.out.println(p.getEntryFor(4).getOrderedPreferences() + "\n");
	}
}
