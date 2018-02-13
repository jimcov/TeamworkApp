package com.morrison.teamworkprojects.ui.donutchart.transition.interpolator;

public class DecelerateInterpolator implements Interpolator
{
	private final float factor;

	public DecelerateInterpolator()
	{
		this(1.5f);
	}

	public DecelerateInterpolator(final float factor)
	{
		this.factor = factor;
	}

	public float interpolate(final float startValue, final float endValue, final float progressFraction)
	{
		return startValue + (float)((endValue - startValue) * (1.0f - Math.pow(1.0f - progressFraction, (2.0f * factor))));
	}

}
