import java.util.*;
public class CandidateSolution {
	int currentEnergy;
	ArrayList<CandidateAssignment> list = new ArrayList<CandidateAssignment>();
	public CandidateSolution(PreferenceTable pref){
		StudentEntry[] entries = pref.getAllStudentEntries();
		for(int i=0;i<entries.length;i++){
			list.add(new CandidateAssignment(entries[i]));
		}
	}
	public String getAssignmentFor(String name){
		for(int i=0;i<list.size();i++){
			StudentEntry currStu = list.get(i).getStudentEntry();
			if(currStu.getStudentName() == name){
				return list.get(i).getStudentAssignment();
			}
		}
		return "No student found of that name";
	}
	public String getRandomAssignment(){
		Random randObj = new Random();
		int random = randObj.nextInt(list.size()) + 0;
		return list.get(random).getStudentAssignment();
	}
	public int getEnergy(Vector<String> allPrefs){
		Vector<String> canBeAdded = allPrefs;
		
		int collisions = 0;
		boolean flag = false;
		for(int i=0;i<list.size();i++){
			flag = false;
			while(!flag){
				if(canBeAdded.contains(list.get(i).getStudentAssignment())){
					//System.out.println(canBeAdded.size());
					canBeAdded.remove(list.get(i).getStudentAssignment());
					flag = true;
				}else{
					list.get(i).randomizeAssignment();
					if(canBeAdded.contains(list.get(i).getStudentAssignment())){
						flag = true;
					}
					collisions = collisions + 1000;
				}
			}
		}
		int energyTotal = 0;
		for(int i=0;i<list.size();i++){
			energyTotal = energyTotal + list.get(i).getEnergy();
		}
		currentEnergy = energyTotal + collisions;
		return energyTotal + collisions;
	}
	public int getFitness(){
		return currentEnergy * -1;
	}
	public static void main(String[] args){
		PreferenceTable p = new PreferenceTable("Project_allocation_data.txt");
		
			CandidateSolution sol = new CandidateSolution(p);
			System.out.println("Finding Energy...");
			System.out.println("Energy: " + sol.getEnergy(p.getAllPrefs()));
			System.out.println("Fitness: " + sol.getFitness());
		
	}
}	

