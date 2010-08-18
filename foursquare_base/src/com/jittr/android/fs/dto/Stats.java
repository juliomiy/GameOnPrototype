package com.jittr.android.fs.dto;

public class Stats {

    private String checkins;

	public String getCheckins() {
		return checkins;
	}

	public void setCheckins(String checkins) {
		this.checkins = checkins;
	}

	@Override
	public String toString() {
		return "Stats [checkins=" + checkins + "]";
	}
    
    
}
