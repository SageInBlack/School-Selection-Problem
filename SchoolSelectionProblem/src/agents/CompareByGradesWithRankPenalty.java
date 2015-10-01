package agents;

import java.util.Comparator;

public class CompareByGradesWithRankPenalty implements Comparator<Student> {

	public int compare(Student student1, Student student2) {
		int s1Score = student1.getScore();
		int s1CR = student1.getCurrentRankListing();
		int s2Score = student2.getScore();
		int s2CR = student2.getCurrentRankListing();
		return ((s1Score - (s1CR-1))-(s2Score - (s2CR-1)))*-1;
	}
}
