
public class CandidateAssignment {
	private StudentEntry student;
	private String assignment;
	private String prevAssignment;
	public CandidateAssignment(StudentEntry stu){
		student = stu;
		this.randomizeAssignment();
		/*
		 * Testing code to see whether it works
		 */
		//System.out.println("Name: " + student.getStudentName() + " \nAssignment: " + assignment + "\n");
	}
	public void randomizeAssignment(){
		if(assignment != null){
			prevAssignment = assignment;
		}
		assignment = student.getRandomPreference();
	}
	public void undoChange(){
		assignment = prevAssignment;
	}
	public String getStudentAssignment(){
		return assignment;
	}
	public StudentEntry getStudentEntry(){
		return student;
	}
	public int getEnergy(){
		int ranking = student.getRanking(assignment);
		return (ranking + 1) * (ranking * 1);
	}
	public void changeAssignment(String ass){
		prevAssignment = assignment;
		assignment = ass;
	}
}	
