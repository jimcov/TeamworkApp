package com.morrison.teamworkprojects.ui.donutchart.transition.interpolator;

public interface Interpolator
{
	float interpolate(final float startValue, final float endValue, final float progressFraction);
	
}
