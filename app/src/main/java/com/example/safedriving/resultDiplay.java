package com.example.safedriving;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class resultDiplay extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_result_diplay, container, false);

        TextView output = v.findViewById(R.id.resultsOutput);

        String n_output = "";

        if (Results.output_print_bac >= 0.08) {
            n_output = String.format("Your BAC is: %2f" +
                    "\n\n According to state guidelines, you shouldn't " +
                    "drive and should choose from the options below\n\n", Results.output_print_bac);
        }
        else {
            n_output = String.format("Your BAC is: %2f" +
                            "\n\n According to state guidelines, you can Drive.\n" +
                    "Following is the Google Map to find your home\n", Results.output_print_bac);
        }

        output.setText(n_output);

        return v;
    }
}