package com.w3i.torch.achivements;

public class KyleDefeatedAchievement extends Achievement {

	public KyleDefeatedAchievement() {
		setName(AchievementConstants.KYLE_NAME);
		setDescription(AchievementConstants.KYLE_DESCRIPTION);
		setType(Type.KYLE_DEFEATED);
		setLocked(true);
	}
}