package agents;

import java.util.Comparator;

public class CompareByGrade implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {
		return (student1.getScore()-student2.getScore())*-1;
	}

}
