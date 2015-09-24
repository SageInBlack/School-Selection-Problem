package agents;

import java.util.LinkedList;

public class Student {
	private String id;
	private String name;
	private LinkedList<Integer> schoolRankList;
	private int currentRankListing;
	private int score;

	public Student(String id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
		schoolRankList = new LinkedList<Integer>();
		currentRankListing = 1;
	}

	public String toString() {
		return name + " | " + score + " | " + currentRankListing;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Integer> getSchoolRankList() {
		return schoolRankList;
	}

	public void setSchoolRankList(LinkedList<Integer> schoolRankList) {
		this.schoolRankList = schoolRankList;
	}

	public int getCurrentRankListing() {
		return currentRankListing;
	}

	public void setCurrentRankListing(int currentRankListing) {
		this.currentRankListing = currentRankListing;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void nextRank() {
		currentRankListing++;
	}

	public void addSchool(int schoolID) {
		schoolRankList.add(schoolID);
	}

	public Integer getCurrentRankSchool() {
		return schoolRankList.get(currentRankListing - 1);
	}
}