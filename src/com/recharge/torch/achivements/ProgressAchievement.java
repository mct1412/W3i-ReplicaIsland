package com.recharge.torch.achivements;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

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
		if (goal > 1000000) {
			this.goal = AchievementManager.getApplicationContext().getResources().getInteger(goal);
		} else {
			this.goal = goal;
		}
		calculateNextUpdate();
	}

	private void calculateNextUpdate() {
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
		setProgress(progress, true);
	}

	public void setProgress(
			int progress,
			boolean notify) {
		if (!isDone()) {
			this.achievementProgress = progress;
			if (progress > 0) {
				setLocked(false);
				if (progress < goal) {
					if (progress >= nextUpdate) {
						calculateNextUpdate();
						if (notify) {
							int percentDone = (progress * 100) / goal;
							AchievementManager.notifyAchievementProgressUpdated(this, percentDone);
						}
					}
				}
			}
			if (progress >= goal) {
				setDone(true, notify);
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
		replaceDescription();
		calculateNextUpdate();
	}

	protected void replaceDescription() {
		String description = getDescription();
		if ((description != null) && (goal > 0)) {
			description = makeDescriptionReplacement(description);
			super.setDescription(description);
		}
	}

	protected String makeDescriptionReplacement(
			String description) {
		return description.replace("#", Integer.toString(getGoal()));
	}

	@Override
	protected void setDescription(
			int description) {
		super.setDescription(description);
		replaceDescription();
	}

	@Override
	public void setDescription(
			String description) {
		super.setDescription(description);
		replaceDescription();
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

	@Override
	public void loadSharedPreferencesData(
			SharedPreferences preferences) {
		setProgress(preferences.getInt(getPreferencesProgress(), 0));
		super.loadSharedPreferencesData(preferences);
	}

	@Override
	public void storeSharedPreferencesData(
			Editor editor) {
		editor.putInt(getPreferencesProgress(), achievementProgress);
		super.storeSharedPreferencesData(editor);
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