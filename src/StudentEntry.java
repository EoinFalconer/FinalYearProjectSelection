import java.util.*;
import java.util.Random;
public class StudentEntry {
	String studentName = "";
	boolean hasPreassigned = false;
	boolean originalPreferencesEntered = false;
	int originalNumberOfPreferences = 0;
	ArrayList<String> preferences = new ArrayList<String>();
	public StudentEntry(String sname){
		studentName = sname;
	}
	public void setHasPreassigned(String flag){
		if(flag.equalsIgnoreCase("yes")){
			hasPreassigned = true;
		}else{
			hasPreassigned = false;
		}
	}
	public void setOriginalPreferences(String[] originalPreferences){
		if(originalPreferencesEntered == false){
			for(int i=0;i<originalPreferences.length;i++){
				preferences.add(originalPreferences[i]);
			}
			originalNumberOfPreferences = preferences.size();
			originalPreferencesEntered = true;
		}else{
			System.out.println("Can't enter original preferences more than once.");
		}
	}
		
	public String getStudentName(){
		return studentName;
	}
	public String getOrderedPreferences(){
		String orderedPreferences = preferences.get(0);
		for(int i=1;i<preferences.size();i++){
			orderedPreferences+= ", " + preferences.get(i);
		}
		return orderedPreferences;
	}
	public void preassignProject(String pname){
		if(preferences.size() <= 1){
			preferences.add(0, pname);
			hasPreassigned = true;
		}
		else{
			System.out.println("Student has more than one preference");
		}
	}
	public boolean hasPreassignedProject(){
		return hasPreassigned;
	}
	public int getNumberOfStatedPreferences(){
		return originalNumberOfPreferences;
	}
	public void addProject(String pname){
		//System.out.println(preferences.size());
			preferences.add(pname.intern());		//adding to the end of preferences.
		
	}
	public String getRandomPreference(){
		Random randint = new Random();
		int randomInt = randint.nextInt(preferences.size()) + 0;
		return preferences.get(randomInt);
	}
	public boolean hasPreference(String preference){
		return this.getOrderedPreferences().contains(preference);
	}
	public int numberOfPreferences(){
		return preferences.size();
	}
	public int getRanking(String pref){
		for(int i=0;i<preferences.size();i++){
			if(pref.equals(preferences.get(i))){
				return i;
			}
		}
		return -1;
	}
}
