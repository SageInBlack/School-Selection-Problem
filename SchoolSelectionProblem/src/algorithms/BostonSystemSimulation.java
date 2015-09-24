package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import agents.School;
import agents.Student;

public class BostonSystemSimulation {
	public static void main (String[] args) throws IOException {
		File studentDataFile = new File(args[0]);
		BufferedReader buffer0 = new BufferedReader(new FileReader(studentDataFile));
		
		LinkedList<Student> waitingLine = new  LinkedList<Student>();
		HashMap<Integer,School> schoolMap = new HashMap<Integer,School>();
		StringTokenizer st;

		String line;
		Student student;
		School school;
		int maxRanking = 20;
		
		//Read in Student Data
		while ((line = buffer0.readLine()) != null) {
			line = line.trim();
			st = new StringTokenizer(line,"\t");
			student = new Student(st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken()));
			while(st.hasMoreTokens()){
				student.addSchool(Integer.parseInt(st.nextToken()));
			}
			//System.out.println(student.getSchoolRankList());
			waitingLine.add(student);
		}
		//System.out.println(waitingLine.toString());

		//Read in School Data
		File schoolDataFile = new File(args[1]);
		BufferedReader buffer1 = new BufferedReader(new FileReader(schoolDataFile));
		while ((line = buffer1.readLine()) != null) {
			line = line.trim();
			st = new StringTokenizer(line,"\t");
			school = new School(Integer.parseInt(st.nextToken()),st.nextToken(),Integer.parseInt(st.nextToken()));
			//System.out.println(school);
			schoolMap.put(school.getId(), school);
		}
		//System.out.println(schoolMap.toString());
		for(int iteration = 1; iteration <= maxRanking; iteration++){
			while(!waitingLine.isEmpty()){
				student = waitingLine.pop();
				school = schoolMap.get(student.getCurrentRankSchool());
				school.addApplicant(student);
			}
			//System.out.println(schoolMap);

			//Elimination Round
			for(School s: schoolMap.values()){
				s.sortByRankAndGrades();
				for(int count = s.getSeats(); count < 0 ; count++){
					waitingLine.add(s.removeBottomStudent());
				}
			}

			//Set Current Student rank to next school
			for(Student s: waitingLine){
				s.nextRank();
			}
			//Display Result
			for(School s: schoolMap)
			System.out.println(schoolMap);
			System.out.println(waitingLine.size());
			System.out.println(waitingLine);
		}
		//Start Rounds
	}
}