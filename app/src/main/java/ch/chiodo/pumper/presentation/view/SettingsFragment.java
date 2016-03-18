package ch.chiodo.pumper.presentation.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ch.chiodo.pumper.R;
import ch.chiodo.pumper.presentation.viewmodel.SettingsViewModel;

public class SettingsFragment extends Fragment {
    private static SettingsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        viewModel = new SettingsViewModel(view.getContext().getApplicationContext());
        Button saveButton = (Button) view.findViewById(R.id.setting_add_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text =  (EditText)view.findViewById(R.id.setting_training_name);
                String trainingName = text.getText().toString();
                try {
                    viewModel.addTraining(trainingName);
                    Snackbar.make(v, "Added training " + trainingName + " successfully", Snackbar.LENGTH_LONG);
                    getFragmentManager().popBackStack();

                } catch (IllegalAccessException e) {
                    Snackbar.make(v, "Error while adding training", Snackbar.LENGTH_LONG);
                }
            }
        });
        return view;
    }

}
