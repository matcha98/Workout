package com.matcha.workout;


import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {

    private int seconds=0;
    private boolean running;
    private boolean wasRunning;

    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState !=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
            if(wasRunning)
            {
                running=true;
            }
        }
        View layout=inflater.inflate(R.layout.fragment_stopwatch,container,false);
        runTimer(layout);
        Button btStart=layout.findViewById(R.id.start_button);
        btStart.setOnClickListener(this);
        Button btStop=layout.findViewById(R.id.stop_button);
        btStop.setOnClickListener(this);
        Button btReset=layout.findViewById(R.id.reset_button);
        btReset.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasRunning)
        {
            running=true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void runTimer(View view)
    {
        final TextView timeView=view.findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds / 3600;
                int minutes=(seconds % 3600) / 60;
                int secs=seconds % 60;
                String time=String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
    public void onClickStart(View v)
    {
        running=true;
    }
    public void onClickStop(View v)
    {
        running=false;
    }
    public void onClickReset(View v)
    {
        running=false;
        seconds=0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.start_button:
                onClickStart(view);
                break;
            case R.id.stop_button:
                onClickStop(view);
                break;
            case R.id.reset_button:
                onClickReset(view);
                break;
        }
    }
}
