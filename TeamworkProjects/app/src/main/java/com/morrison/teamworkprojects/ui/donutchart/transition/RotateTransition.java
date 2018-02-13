package com.morrison.teamworkprojects.ui.donutchart.transition;

import com.morrison.teamworkprojects.ui.donutchart.model.Donut;

public class RotateTransition extends FloatTransition implements DonutTransition
{
	private Donut donut;
	
	public RotateTransition(final float startRotation, final float endRotation)
	{
		super(startRotation, endRotation);
	}

	@Override
	public void setDonut(final Donut donut)
	{
		this.donut = donut;
	}

	@Override
	protected void doUpate(final Float rotationUnit)
	{
		donut.setRotationUnit(rotationUnit);
	}

}
