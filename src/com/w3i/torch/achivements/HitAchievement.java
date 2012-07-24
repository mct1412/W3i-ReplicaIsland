package com.w3i.torch.achivements;

public class HitAchievement extends ProgressAchievement {

	public HitAchievement() {
		super(AchievementConstants.HIT_GOAL);
		setName(AchievementConstants.HIT_NAME);
		setDescription(AchievementConstants.HIT_DESCRIPTION);
		setType(Type.HIT);
	}

}