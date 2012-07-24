package com.w3i.torch.achivements;

import com.w3i.common.Log;

public class ProgressAchievement extends Achievement {
	private int achievementProgress;
	private int goal;
	private float achievementUpdateRate = DEFAULT_UPDATE_RATE;
	private int nextUpdate;

	private static final float DEFAULT_UPDATE_RATE = 25;

	protected ProgressAchievement() {
		setProgressAchievement(true);
	}

	protected ProgressAchievement(int goal) {
		this();
		this.goal = goal;
		calculateNextUpdate();
	}

	private void calculateNextUpdate() {
		if (this instanceof AllLevelsAchievement) {
			new String();
		}
		float updateIntervalFloat = ((achievementUpdateRate * goal) / 100f) + 0.5f;
		int updateInterval = (int) updateIntervalFloat;
		if (updateInterval > 1) {
			nextUpdate = ((achievementProgress / updateInterval) + 1) * updateInterval;
		} else {
			updateInterval = goal;
		}
	}

	public void setProgress(
			int progress) {
		if (!isDone()) {
			Log.i("Achievement " + getName() + " (" + getDescription() + ") updated : " + progress + "/" + getGoal());
			this.achievementProgress = progress;
			if (progress > 0) {
				setLocked(false);
				if (progress < goal) {
					if (progress >= nextUpdate) {
						calculateNextUpdate();
						int percentDone = (progress * 100) / goal;
						AchievementManager.notifyAchievementProgressUpdated(this, percentDone);
					}
				}
			}
			if (progress >= goal) {
				setDone(true);
			}
		}
	}

	public int getProgress() {
		return achievementProgress > goal ? goal : achievementProgress;
	}

	public String getProgressString() {
		return convertProgress(getProgress()) + "/" + convertProgress(goal);
	}

	public void setGoal(
			int goal) {
		this.goal = goal;
		calculateNextUpdate();
	}

	public int getGoal() {
		return goal;
	}

	public void increaseProgress(
			int increment) {
		setProgress(achievementProgress + increment);
	}

	protected void setAchievementUpdateRate(
			float newUpdateRate) {
		achievementUpdateRate = newUpdateRate;
		calculateNextUpdate();
	}

	protected String convertProgress(
			int i) {
		return Integer.toString(i);
	}

	@Override
	public void reset() {
		super.reset();
		setProgress(0);
	}
}