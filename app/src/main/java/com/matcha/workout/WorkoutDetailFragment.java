package com.matcha.workout;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState !=null)
        {
            workoutId=savedInstanceState.getLong("workoutId");
        }
        else
        {
            FragmentTransaction ft=getChildFragmentManager().beginTransaction();
            StopwatchFragment sf=new StopwatchFragment();
            ft.replace(R.id.stopwatch_container,sf);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null)
        {
            TextView title=view.findViewById(R.id.textView);
            Workout workout=Workout.workouts[(int)workoutId];
            title.setText(workout.getName());
            TextView description = view.findViewById(R.id.textView2);
            description.setText(workout.getDescription());
        }
    }

    public void setWorkoutId(long id)
    {
        this.workoutId=id;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("workoutId",workoutId);
    }
}
