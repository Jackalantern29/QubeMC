package com.Jackalantern29.QCParkour;

public class ParkourStage {
	private final String difficulty;
	private final int checkpoint;
	
	public ParkourStage(String difficulty, int checkpoint) {
		this.difficulty = difficulty;
		this.checkpoint = checkpoint;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	public int getCheckpoint() {
		return checkpoint;
	}
}
