package com.w3i.torch.achivements;

public class PearlsAchievement extends ProgressAchievement {

	public PearlsAchievement() {
		super(AchievementConstants.PEARLS_GOAL);
		setName(AchievementConstants.PEARLS_NAME);
		setDescription(AchievementConstants.PEARLS_DESCRIPTION);
		setType(Type.PEARLS);
	}
}