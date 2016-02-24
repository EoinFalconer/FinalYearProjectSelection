/*
 * Eoin Falconer
 * 13331016
 * Assignment One - File I/O
 */

import java.io.*;
import java.util.*;
import java.util.Random;

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
	public StudentEntry getRandomStudent(){
		Random randomObj = new Random();
		int randomInt = randomObj.nextInt(studentLookUp.size()) + 0;
		return studentLookUp.get(randomInt);
	}
	public String getRandomPreference(){
		StudentEntry randomStudent = this.getRandomStudent();
		return randomStudent.getRandomPreference();
	}
	public void fillPreferencesOfAll(int maxPrefs){
		for(int i=0;i<studentLookUp.size();i++){
			StudentEntry currStu = studentLookUp.get(i);
			if(!(currStu.hasPreassignedProject())){
			while(currStu.numberOfPreferences() < maxPrefs){
				String randPref = this.getRandomPreference();
				if(!(currStu.hasPreference(randPref))){
					currStu.addProject(randPref);
				}
			}		
		}
		}	
	}

}
