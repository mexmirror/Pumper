package ch.chiodo.pumper.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.chiodo.pumper.R;
import ch.chiodo.pumper.view.viewadapter.ExerciseAdapter;


public class EditTrainingFragment extends Fragment {
    private static ExerciseAdapter adapter;
    private ViewGroup noData;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_training, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Edit Training");
        appCompatActivity.getSupportActionBar().show();
        ((FloatingActionButton)view.findViewById(R.id.edit_fab)).setOnClickListener(addExerciseClickListener());
        recyclerView = (RecyclerView)view.findViewById(R.id.edit_recyclerview);
        recyclerView.setHasFixedSize(true);
        noData = (ViewGroup)view.findViewById(R.id.edit_no_data);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExerciseAdapter();
        if(adapter.getItemCount() > 0) {
            noData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            noData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(adapter);
        return view;
    }
    private View.OnClickListener addExerciseClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extra = new Bundle();
                extra.putString("device", "");
                extra.putString("weight", "");
                extra.putString("repetition", "");
                Fragment exerciseFragment = new EditExerciseFragment();
                exerciseFragment.setArguments(extra);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, exerciseFragment)
                        .addToBackStack(null)
                        .commit();
            }
        };
    }

}
