package com.morrison.teamworkprojects.ui.donutchart.transition;

import com.morrison.teamworkprojects.ui.donutchart.model.Donut;

public class OpacityTransition extends FloatTransition implements DonutTransition
{
	private Donut donut;

	public OpacityTransition(final float startOpacity, final float endOpacity)
	{
		super(startOpacity, endOpacity);
	}

	@Override
	public void setDonut(final Donut donut)
	{
		this.donut = donut;
	}

	@Override
	protected void doUpate(final Float opacityUnit)
	{
		donut.setOpacity(opacityUnit);
	}

}
