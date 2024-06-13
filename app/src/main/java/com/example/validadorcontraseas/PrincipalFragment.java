package com.example.validadorcontraseas;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.validadorcontraseas.databinding.FragmentPrincipalBinding;

import com.google.android.material.textfield.TextInputEditText;



public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;
    private static final int MIN_CHARACTERS = 5;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Limpia el binding para evitar fugas de memoria
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle("Validador de contraseñas");
        }

        TextInputEditText editTextPassword = binding.passwordEditText;
        Button buttonValidate = binding.showButton;

        editTextPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > MIN_CHARACTERS) {
                    buttonValidate.setEnabled(true);
                }
                if(charSequence.length() <= MIN_CHARACTERS) {
                    buttonValidate.setEnabled(false);
                    binding.hiddenText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonValidate.setOnClickListener(v -> {
            String password = editTextPassword.getText().toString();
            if (isPasswordValid(password)) {
                buttonValidate.setVisibility(View.INVISIBLE);
                editTextPassword.setVisibility(View.INVISIBLE);
                binding.hiddenText.setVisibility(View.INVISIBLE);
                binding.textInputLayoutEmail.setVisibility(View.INVISIBLE);
               Navigation.findNavController(view).navigate(R.id.action_principalFragment_to_resulFragment);
            } else {
                showMessage();
            }
        });
    }



    private boolean isPasswordValid(String password) {
        if (password.length() < MIN_CHARACTERS ) {
            return false;
        }
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }
 private void showMessage() {
        binding.hiddenText.setVisibility(View.VISIBLE);
               // Ocultar el texto de error después de 5 segundos y limpiar el TextInputEditText
     new Handler().postDelayed(() -> {
         binding.hiddenText.setVisibility(View.GONE);
         binding.passwordEditText.setText("");
     }, 3000);
    }
}