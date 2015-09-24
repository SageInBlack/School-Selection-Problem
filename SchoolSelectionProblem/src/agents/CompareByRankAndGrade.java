package agents;

import java.util.Comparator;

public class CompareByRankAndGrade implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {
		int rankDif = student1.getCurrentRankListing() - student2.getCurrentRankListing();
		int scoreDif = student1.getScore() - student2.getScore();
		if(rankDif != 0) return rankDif;
		else return scoreDif*-1;
		
	}

}
