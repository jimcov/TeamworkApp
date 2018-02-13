package com.morrison.teamworkprojects.ui.donutchart.transition.interpolator;

public class LinearInterpolator implements Interpolator
{
	public float interpolate(final float startValue, final float endValue, final float progressFraction)
	{
		return startValue + ((endValue - startValue) * progressFraction);
	}
	
}
