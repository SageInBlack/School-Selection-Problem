package agents;

import java.util.Collections;
import java.util.LinkedList;

public class School {
	
	private int id;
	private String name;
	private int seats;
	private LinkedList<Student> applicants;
	
	public School(int id, String name, int seats){
		this.id = id;
		this.name = name;
		this.seats = seats;
		applicants = new LinkedList<Student>();
	}
	public String toString(){
		return name + " Avaliable Position: " + (seats)+ "\n"+ "Student List - "+ applicants.toString()+"\n";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public LinkedList<Student> getApplicants() {
		return applicants;
	}
	public void setApplicants(LinkedList<Student> applicants) {
		this.applicants = applicants;
	}
	public void addApplicant(Student student){
		applicants.add(student);
		seats--;
	}
	public void sortByGrades(){
		Collections.sort(this.applicants, new CompareByGrade());
	}
	public void sortByRankAndGrades(){
		Collections.sort(this.applicants, new CompareByRankAndGrade());
	}
	public Student removeBottomStudent() {
		seats++;
		return applicants.removeLast();		
	}
}
