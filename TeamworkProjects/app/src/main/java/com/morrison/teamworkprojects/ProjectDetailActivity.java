package com.morrison.teamworkprojects;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.morrison.teamworkprojects.model.ItemCount;
import com.morrison.teamworkprojects.model.Project;
import com.morrison.teamworkprojects.model.TimeSlot;
import com.morrison.teamworkprojects.resource.AppConstants;
import com.morrison.teamworkprojects.resource.ProjectsProvider;
import com.morrison.teamworkprojects.ui.donutchart.manager.Ticker;
import com.morrison.teamworkprojects.ui.donutchart.model.Donut;
import com.morrison.teamworkprojects.model.TimeSlotColorProvider;
import com.morrison.teamworkprojects.ui.donutchart.transition.OpacityTransition;
import com.morrison.teamworkprojects.ui.donutchart.transition.RotateTransition;
import com.morrison.teamworkprojects.ui.donutchart.transition.ScaleTransition;
import com.morrison.teamworkprojects.ui.donutchart.transition.interpolator.DecelerateInterpolator;
import com.morrison.teamworkprojects.ui.donutchart.transition.interpolator.ElasticOutInterpolator;
import com.morrison.teamworkprojects.ui.donutchart.ui.DonutView;
import com.morrison.teamworkprojects.ui.itemcountssummary.model.ItemCountsSummary;
import com.morrison.teamworkprojects.ui.itemcountssummary.ui.ItemCountsSummaryView;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Project project = ProjectsProvider.INSTANCE.getSelectedProject();

        List<ItemCount<TimeSlot>> myTimeSlotCounts = getMyTimeSlotCountsForProject(project.getId());
        List<ItemCount<TimeSlot>> allTimeSlotCounts = getAllTimeSlotCountsForProject(project.getId());

        TextView projectNameView = (TextView)findViewById(R.id.project_name_text_view);
        projectNameView.setText(project.getName());

        ItemCountsSummary myItemCountsSummary = new ItemCountsSummary("My Tasks", myTimeSlotCounts, new TimeSlotColorProvider());
        ItemCountsSummaryView myItemCountsSummaryView = (ItemCountsSummaryView)findViewById(R.id.my_tasks_summary);
        myItemCountsSummaryView.setItemCountsSummary(myItemCountsSummary);

        ItemCountsSummary allItemCountsSummary = new ItemCountsSummary("All Tasks", allTimeSlotCounts, new TimeSlotColorProvider());
        ItemCountsSummaryView allItemCountsSummaryView = (ItemCountsSummaryView)findViewById(R.id.all_tasks_summary);
        allItemCountsSummaryView.setItemCountsSummary(allItemCountsSummary);

        Donut myTasksDonut = new Donut(myTimeSlotCounts, new TimeSlotColorProvider());
        DonutView myTasksDonutView = (DonutView)findViewById(R.id.my_tasks_donut);
        myTasksDonutView.setDonut(myTasksDonut);

        Donut allTasksDonut = new Donut(allTimeSlotCounts, new TimeSlotColorProvider());
        DonutView allTasksDonutView = (DonutView)findViewById(R.id.all_tasks_donut);
        allTasksDonutView.setDonut(allTasksDonut);

        doDonutTransition(myTasksDonutView);
        doDonutTransition(allTasksDonutView);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#82C882")));
        String title = AppConstants.APP_NAME;
        SpannableString string = new SpannableString(title);
        string.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(string);
    }

    private void doDonutTransition(final DonutView donutView) {
        final Donut donut = donutView.getDonut();

        final RotateTransition rotateTransition = new RotateTransition(0.5f, 1.0f);
        rotateTransition.setDonut(donut);
        rotateTransition.setLocalStartTime(0);
        rotateTransition.setDuration(2000, 50);
        rotateTransition.setInterpolator(new ElasticOutInterpolator(0.2f));

        final ScaleTransition scaleTransition = new ScaleTransition(0.0f, 1.0f);
        scaleTransition.setDonut(donut);
        scaleTransition.setLocalStartTime(0);
        scaleTransition.setDuration(2000, 50);
        scaleTransition.setInterpolator(new DecelerateInterpolator(3.0f));

        final OpacityTransition opacityTransition = new OpacityTransition(0.0f, 1.0f);
        opacityTransition.setDonut(donut);
        opacityTransition.setLocalStartTime(0);
        opacityTransition.setDuration(2000, 50);

        final Ticker ticker = new Ticker(50);
        ticker.addTickListener(new Ticker.TickListener() {
            @Override
            public void tick(int ticksToAdvance) {
                if(!rotateTransition.isFinished()) {
                    rotateTransition.tick(ticksToAdvance);
                }
                if(!scaleTransition.isFinished()) {
                    scaleTransition.tick(ticksToAdvance);
                }
                if(!opacityTransition.isFinished()) {
                    opacityTransition.tick(ticksToAdvance);
                    donutView.setAlpha(donut.getOpacity());
                }
                donutView.invalidate();
                if(rotateTransition.isFinished() && scaleTransition.isFinished() && opacityTransition.isFinished()) {
                    ticker.stop();
                }
            }
        });
        ticker.start();
    }

    private List<ItemCount<TimeSlot>> getMyTimeSlotCountsForProject(final String projectID) {
        // should get this list from the server, based on the project ID, but ran out of time
        // so will return a hard-coded list for each of the two known project IDs

        List<ItemCount<TimeSlot>> myTimeSlotCounts = new ArrayList<ItemCount<TimeSlot>>();

        if(projectID.equals("339988")) { // this is the Adamantium project
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.UPCOMING, 0));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.LATE, 2));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.STARTED, 2));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.TODAY, 1));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.NO_DATE, 2));
        } else { // this is the Cool project
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.UPCOMING, 0));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.LATE, 7));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.STARTED, 1));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.TODAY, 0));
            myTimeSlotCounts.add(new ItemCount<>(TimeSlot.NO_DATE, 2));
        }

        return myTimeSlotCounts;
    }

    private List<ItemCount<TimeSlot>> getAllTimeSlotCountsForProject(final String projectID) {
        // should get this list from the server, based on the project ID, but ran out of time
        // so will return a hard-coded list for each of the two known project IDs

        List<ItemCount<TimeSlot>> allTimeSlotCounts = new ArrayList<ItemCount<TimeSlot>>();

        if(projectID.equals("339988")) { // this is the Adamantium project
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.UPCOMING, 0));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.LATE, 34));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.STARTED, 3));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.TODAY, 1));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.NO_DATE, 315));
        } else { // this is the Cool project
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.UPCOMING, 0));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.LATE, 56));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.STARTED, 1));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.TODAY, 0));
            allTimeSlotCounts.add(new ItemCount<>(TimeSlot.NO_DATE, 153));
        }

        return allTimeSlotCounts;
    }

}
