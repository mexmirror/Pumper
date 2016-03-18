package ch.chiodo.pumper.presentation.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.chiodo.pumper.R;
import ch.chiodo.pumper.presentation.Constants;
import ch.chiodo.pumper.presentation.viewmodel.EditExerciseViewModel;

public class EditExerciseFragment extends Fragment {
    private EditExerciseViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_exercise, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Edit Exercise");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        appCompatActivity.getSupportActionBar().show();
        List<String> extras = getValuesFromBundle();
        setFieldValues(view, extras.get(0), extras.get(1), extras.get(2));
        Button saveButton = (Button)view.findViewById(R.id.edit_ex_save_button);
        saveButton.setOnClickListener(onSaveClick(view));
        viewModel = new EditExerciseViewModel(view.getContext().getApplicationContext(), Long.parseLong(extras.get(0)));
        return view;
    }
    private List<String> getValuesFromBundle(){
        Bundle extra = getArguments();
        final String trainingId = extra.getString(Constants.TRAINING_ID);
        final String device = extra.getString(Constants.DEVICE_ID);
        final String weight = extra.getString(Constants.WEIGHT);
        final String rep = extra.getString(Constants.REPETITION);
        return new ArrayList<String>() {{
            add(trainingId);
            add(device);
            add(weight);
            add(rep);
        }};
    }
    private void setFieldValues(View view, String device, String weight, String repetition){
        TextView deviceField = (TextView)view.findViewById(R.id.edit_ex_id_value);
        deviceField.setText(device);
        TextView deviceView = (TextView)view.findViewById(R.id.edit_ex_id);
        deviceField.addTextChangedListener(onDeviceTextChanged(deviceView, deviceField));
        TextView weightField =(TextView)view.findViewById(R.id.edit_ex_weight_value);
        weightField.setText(weight);
        TextView repetitionField = (TextView)view.findViewById(R.id.edit_ex_repetition_value);
        repetitionField.setText(repetition);
    }
    private View.OnClickListener onSaveClick(final View rootView){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ((TextView)rootView.findViewById(R.id.edit_ex_id_value)).getText().toString();
                String weight = ((TextView)rootView.findViewById(R.id.edit_ex_weight_value)).getText().toString();
                String repetition = ((TextView)rootView.findViewById(R.id.edit_ex_repetition_value)).getText().toString();
                viewModel.addExercise(Double.parseDouble(weight), Integer.parseInt(repetition), id);
                getFragmentManager().popBackStack();
            }
        };
    }
    private TextWatcher onDeviceTextChanged(final TextView deviceView, final TextView deviceField){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0 && s.length() < 3){
                    deviceView.setText(s.toString());
                } else {
                    deviceView.setText("ID");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 2){
                    deviceField.setError(getString(R.string.id_too_long_error));
                } else {

                }
            }
        };
    }
}
