package com.morrison.teamworkprojects.ui.donutchart.transition.interpolator;

public class ElasticOutInterpolator implements Interpolator
{
	private final float factor;

	public ElasticOutInterpolator()
	{
		this(0.3f);
	}

	public ElasticOutInterpolator(final float factor)
	{
		this.factor = factor;
	}

	public float interpolate(final float startValue, final float endValue, final float progressFraction)
	{
		return startValue + (float)((endValue - startValue) * (float)(Math.pow(2.0f, -8.0f * progressFraction) * Math.sin((progressFraction - factor / 4.0f) * (2.0f * Math.PI) / factor) + 1.0f));
	}

}
