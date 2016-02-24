import java.util.*;
public class CandidateSolution {
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
	public static void main(String[] args){
		PreferenceTable p = new PreferenceTable("Project_allocation_data.txt");
		p.fillPreferencesOfAll(10);
		CandidateSolution sol = new CandidateSolution(p);
		System.out.println(sol.getRandomAssignment());
	}
}
