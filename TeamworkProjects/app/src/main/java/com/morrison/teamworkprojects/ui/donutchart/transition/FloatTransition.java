package com.morrison.teamworkprojects.ui.donutchart.transition;

public class FloatTransition extends Transition<Float>
{
	private float startValue;
	private float endValue;

	public FloatTransition(final float startValue, final float endValue)
	{
		this.startValue = startValue;
		this.endValue = endValue;
	}
	
	@Override
	protected void doUpate(final Float value)
	{
		
	}
	
	@Override
	protected Float getInterpolatedValue(final float progressFraction)
	{
		return interpolator.interpolate(startValue, endValue, progressFraction);
	}

}
