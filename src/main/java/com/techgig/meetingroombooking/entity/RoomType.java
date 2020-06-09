package com.techgig.meetingroombooking.entity;

public enum RoomType {

	FOUR_SEATER("FOUR SEATER"),

	EIGHT_SEATER("EIGHT SEATER");

	private String displayName;

	RoomType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
