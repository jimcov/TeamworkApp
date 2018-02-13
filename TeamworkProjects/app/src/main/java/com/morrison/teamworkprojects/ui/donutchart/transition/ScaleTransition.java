package com.morrison.teamworkprojects.ui.donutchart.transition;

import com.morrison.teamworkprojects.ui.donutchart.model.Donut;

public class ScaleTransition extends FloatTransition implements DonutTransition
{
	private Donut donut;

	public ScaleTransition(final float startDiameterUnit, final float endDiameterUnit)
	{
		super(startDiameterUnit, endDiameterUnit);
	}

	@Override
	public void setDonut(final Donut donut)
	{
		this.donut = donut;
	}

	@Override
	protected void doUpate(final Float diameterUnit)
	{
		donut.setDiameterUnit(diameterUnit);
	}

}
