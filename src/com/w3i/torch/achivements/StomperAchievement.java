package com.w3i.torch.achivements;

public class StomperAchievement extends ProgressAchievement {

	public StomperAchievement() {
		super(AchievementConstants.STOMPER_GOAL);
		setName(AchievementConstants.STOMPER_NAME);
		setDescription(AchievementConstants.STOMPER_DESCRIPTION);
		setType(Type.STOMP);
	}

}
