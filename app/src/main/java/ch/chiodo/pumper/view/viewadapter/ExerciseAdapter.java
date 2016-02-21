package ch.chiodo.pumper.view.viewadapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.chiodo.pumper.R;
import ch.chiodo.pumper.controller.TrainingController;
import ch.chiodo.pumper.model.Exercise;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{
    private List<Exercise> exerciseList;
    private TrainingController trainingController;
    public ExerciseAdapter(){
        trainingController = new TrainingController();
        exerciseList = trainingController.getExercises();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.edit_rowlayout, parent, false);
        TextView device = (TextView)v.findViewById(R.id.row_edit_device);
        TextView weight = (TextView)v.findViewById(R.id.row_edit_weight_value);
        TextView repetition = (TextView)v.findViewById(R.id.row_edit_repetition_value);
        return new ViewHolder(v, device, weight, repetition);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Exercise exercise = exerciseList.get(position);
        holder.device.setText(exercise.getDevice().getDeviceName());
        holder.weight.setText(Double.toString(exercise.getWeight()));
        holder.repetition.setText(Integer.toString(exercise.getRepetition()));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
    public void add(Exercise exercise){
        exerciseList.add(exercise);
        trainingController.addExerciseToTraining(exercise);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View parent;
        public TextView device;
        public TextView weight;
        public TextView repetition;
        public ViewHolder(View parent, TextView device, TextView weight, TextView repetition) {
            super(parent);
            this.parent = parent;
            this.device = device;
            this.weight = weight;
            this.repetition = repetition;
        }
    }
}
