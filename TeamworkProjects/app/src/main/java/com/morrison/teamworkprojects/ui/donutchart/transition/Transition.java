package com.morrison.teamworkprojects.ui.donutchart.transition;

import com.morrison.teamworkprojects.ui.donutchart.transition.interpolator.Interpolator;
import com.morrison.teamworkprojects.ui.donutchart.transition.interpolator.LinearInterpolator;

public abstract class Transition<T>
{
	private int localStartTimeMillis;
	private int frameCount;
	protected Interpolator interpolator = new LinearInterpolator();
	private int currentFrame = 0;
	private boolean isStarted;
	private boolean isFinished;

	public int getLocalStartTime()
	{
		return localStartTimeMillis;
	}

	public void setLocalStartTime(final int localStartTimeMillis)
	{
		this.localStartTimeMillis = localStartTimeMillis;
	}
	
	public void setDuration(final int durationMillis, final int preferredFPS)
	{
		frameCount = durationMillis * preferredFPS / 1000;
	}
	
	public void setInterpolator(final Interpolator interpolator)
	{
		this.interpolator = interpolator;
	}

	public void tick(final int framesToAdvance)
	{
		if(!isStarted)
		{
			transitionWillStart();
			isStarted = true;
		}
		currentFrame += framesToAdvance;
		if(currentFrame < frameCount)
		{
			doUpate(getInterpolatedValue((float)currentFrame / (float)frameCount));
		}
		else
		{
			currentFrame = frameCount - 1; // probably best to keep this, in case external code calls draw
			isFinished = true;
		}
	}

	protected void transitionWillStart()
	{

	}

	protected abstract T getInterpolatedValue(final float progressFraction);

	protected abstract void doUpate(final T value);
	
	public boolean isFinished()
	{
		return isFinished;
	}
	
	public void reset()
	{
		currentFrame = 0;
		isFinished = false;
	}

}
