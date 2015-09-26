package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import agents.School;
import agents.Student;

public class BootStrap {
	public static LinkedList<Student> generateStudentList(String studentFile) throws IOException {
		LinkedList<Student> studentList = new LinkedList<Student>();
		File studentDataFile = new File(studentFile);
		BufferedReader buffer = new BufferedReader(new FileReader(studentDataFile));
		String line;
		StringTokenizer st;
		Student student;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			st = new StringTokenizer(line, "\t");
			student = new Student(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
			while (st.hasMoreTokens()) {
				student.addSchool(Integer.parseInt(st.nextToken()));
			}
			// System.out.println(student.getSchoolRankList());
			studentList.add(student);
		}
		return studentList;
	}

	public static HashMap<Integer, School> generateSchoolMap(String schoolFile) throws IOException {
		HashMap<Integer, School> schoolMap = new HashMap<Integer, School>();
		File schoolDataFile = new File(schoolFile);
		BufferedReader buffer = new BufferedReader(new FileReader(schoolDataFile));
		String line;
		StringTokenizer st;
		School school;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			st = new StringTokenizer(line, "\t");
			school = new School(Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken()));
			// System.out.println(school);
			schoolMap.put(school.getId(), school);
		}
		return schoolMap;
	}
}
