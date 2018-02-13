package com.morrison.teamworkprojects.ui.donutchart.manager;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class Ticker
{
	private int tickIntervalMillis;
	private List<TickListener> tickListeners = new ArrayList<>();
	private boolean isActive = true;
	private boolean isTicking = false;
	private int ticksToAdvance = 1;

	public Ticker(final int preferredFPS)
	{
		tickIntervalMillis = 1000 / preferredFPS;
		final Handler handler = new Handler();
		new Thread()
		{
			public void run()
			{
				while(isActive)
				{
					try
					{
						Thread.sleep(tickIntervalMillis);
					}
					catch (final InterruptedException ex)
					{
						ex.printStackTrace();
					}

					handler.post(new Runnable()
					{
						public void run()
						{
							if(isActive)
							{
								if(!isTicking)
								{
									fireTick();
									ticksToAdvance = 0;
								}
								ticksToAdvance++;
							}
						}
					});
				}
			}
		}.start();
	}
	
	public void start()
	{
		isActive = true;
	}
	
	public void stop()
	{
		isActive = false;
	}
	
	private void fireTick()
	{
		isTicking = true;
		int ticksToAdvance = getTicksToAdvance();
		for (TickListener tickListener : tickListeners)
		{
			tickListener.tick(ticksToAdvance);
		}
		isTicking = false;
	}
	
	private int getTicksToAdvance()
	{
		return ticksToAdvance; // TODO this
	}

	public void addTickListener(final TickListener tickListener)
	{
		tickListeners.add(tickListener);
	}

	public void removeTickListener(final TickListener tickListener)
	{
		tickListeners.remove(tickListener);
	}

	public interface TickListener
	{
		void tick(final int ticksToAdvance);
	}
	
}
