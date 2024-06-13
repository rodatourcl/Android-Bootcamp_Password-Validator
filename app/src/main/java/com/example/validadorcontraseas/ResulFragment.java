package com.example.validadorcontraseas;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.validadorcontraseas.databinding.FragmentResultBinding;


public class ResulFragment extends Fragment {

    private FragmentResultBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);

        if (getActivity() != null) {
            getActivity().setTitle("<-- Resultado");
        }
        return binding.getRoot();
    }
}