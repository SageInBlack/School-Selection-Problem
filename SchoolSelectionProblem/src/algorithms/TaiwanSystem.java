package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import agents.School;
import agents.Student;
import agents.CompareByGradesWithRankPenalty;

public class TaiwanSystem {
	public static void main (String[] args) throws IOException {
		
		LinkedList<Student> waitingLine = new  LinkedList<Student>();
		HashMap<Integer,School> schoolMap = new HashMap<Integer,School>();
		Student student;
		School school;
		int maxRanking = 20;
		int happinessSum = 0;

		waitingLine = BootStrap.generateStudentList(args[0]);
		schoolMap = BootStrap.generateSchoolMap(args[1]);
		
		//Distribution Process
		for(int iteration = 1; iteration <= maxRanking; iteration++){
			while(!waitingLine.isEmpty()){
				student = waitingLine.pop();
				school = schoolMap.get(student.getCurrentRankSchool());
				school.addApplicant(student);
			}
			//System.out.println(schoolMap);
			//TODO: Find a way to eleminate random student from the lowest score
			//Elimination Round
			for(School s: schoolMap.values()){
				Collections.sort(s.getApplicants(), new CompareByGradesWithRankPenalty());
				for(int count = s.getSeats(); count < 0 ; count++){
					waitingLine.add(s.removeBottomStudent());
				}
			}

			//Set Current Student rank to next school
			for(Student s: waitingLine){
				s.nextRank();
			}
			//Display Result
			System.out.println("\nRound "+ iteration+ "\n");
			for(School s: schoolMap.values()){
				System.out.println(s.getName()+ " Seats:" + s.getSeats());
				for(Student applicant: s.getApplicants()){
					System.out.println(applicant.toString());
				}
			}
			System.out.println("Student Missed: "+ waitingLine.size());
			for(Student applicant: waitingLine){
				System.out.println(applicant.toString());
			}
			System.out.println("Round Ended");
		}
		//Evaluate Overall Happiness
		System.out.println("Overall Happiness" + "\n");
		for(School s: schoolMap.values()){
			for(Student applicant: s.getApplicants()){
				happinessSum += applicant.generateHappiness(maxRanking);
			}
		}
		System.out.println("Student with no school:"+ waitingLine.size());
		System.out.println("Taiwan System");
		System.out.println(happinessSum);
	}
}