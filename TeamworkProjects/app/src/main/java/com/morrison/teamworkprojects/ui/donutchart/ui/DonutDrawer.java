package com.morrison.teamworkprojects.ui.donutchart.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.morrison.teamworkprojects.ui.donutchart.model.AnnularSector;
import com.morrison.teamworkprojects.ui.donutchart.model.Donut;

import java.util.List;

public class DonutDrawer
{
    private static final float INSET = 5.0f;

    private Paint paint;
    private float smallestDimension;

    public DonutDrawer(final float width, final float height)
    {
        smallestDimension = (width > height) ? height : width;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void drawDonut(final Donut donut, final Canvas canvas)
    {
        canvas.save(); // first save the state of the canvas
        canvas.rotate(360f * donut.getRotationUnit(), smallestDimension / 2.0f, smallestDimension / 2.0f); // rotate it
        drawAnnularSectors(donut, canvas);
        canvas.restore(); // restore previous state (rotate it back)
    }

    private void drawAnnularSectors(final Donut donut, final Canvas canvas) {
        List<AnnularSector> annularSectors = donut.getAnnularSectors();
        for (AnnularSector annularSector : annularSectors) {
            if(annularSector.getSweepAngleUnit() > 0.0f) {
                drawAnnularSector(donut, annularSector, canvas);
            }
        }
    }

    private void drawAnnularSector(final Donut donut, final AnnularSector annularSector, final Canvas canvas) {
        paint.setColor(annularSector.getColor());

        float diameter = (smallestDimension - (2.0f * INSET)) * donut.getDiameterUnit();
        float currentInset = (smallestDimension - diameter) / 2.0f;
        float coreDiameter = diameter * donut.getCoreDiameterFractionOfDonutDiameter();
        float donutThickness = diameter - coreDiameter;
        float startAngleDegrees = (annularSector.getStartAngleUnit() * 360.0f) - 90.0f;
        float sweepAngleDegrees = annularSector.getSweepAngleUnit() * 360.0f;

        Path path = new Path();
        path.arcTo(currentInset, currentInset, smallestDimension - currentInset, smallestDimension - currentInset, startAngleDegrees, sweepAngleDegrees, false);
        path.arcTo(currentInset + donutThickness, currentInset + donutThickness, smallestDimension - currentInset - donutThickness, smallestDimension - currentInset - donutThickness, startAngleDegrees + sweepAngleDegrees, -sweepAngleDegrees, false);

        canvas.drawPath(path, paint);
    }

}
