package algorithms;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import agents.CompareByGrade;
import agents.School;
import agents.Student;

public class GradeSerialDictatorship {
	public static void main(String[] args) throws IOException {

		LinkedList<Student> waitingLine = new LinkedList<Student>();
		HashMap<Integer, School> schoolMap = new HashMap<Integer, School>();
		Student student;
		LinkedList<Integer> studentPreferences;
		School school;
		int maxRanking = 10;
		int count = 1, studentSize, pointer;
		int happinessSum = 0;
		int[] happiness = { 30, 24, 21, 18, 15, 13, 11, 9, 7, 5, 0 };

		waitingLine = BootStrap.generateStudentList(args[0]);
		studentSize = waitingLine.size();
		schoolMap = BootStrap.generateSchoolMap(args[1]);
		// Sort by grade
		// TODO: Break ties by randomly select with in the same grades
		Collections.sort(waitingLine, new CompareByGrade());
		for (Student s : waitingLine) {
			System.out.println(s.toString());
		}
		// Distribution Process
		while (count < studentSize) {
			student = waitingLine.pop();
			studentPreferences = student.getSchoolRankList();
			// Try to add student to school, if it doesn't add, go to the next
			// one and try again
			for (pointer = 0; pointer < maxRanking; pointer++) {
				school = schoolMap.get(studentPreferences.get(pointer));
				if (school.addApplicant(student)) {
					System.out.println(student.getName() + " has entered: " + school.getName());
					break;
				}
			}
			//If pointer gets to max ranking, then the student has not getten into any school
			if (pointer == maxRanking) {
				waitingLine.add(student);
				System.out.println(student.getName() + " has no school.");
			}
			count++;
		}
		//Students without Schools
		System.out.println(waitingLine.size());
	}
}
