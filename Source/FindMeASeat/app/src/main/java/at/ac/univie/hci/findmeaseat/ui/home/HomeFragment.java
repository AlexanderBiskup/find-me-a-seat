package at.ac.univie.hci.findmeaseat.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import at.ac.univie.hci.findmeaseat.R;

public class HomeFragment extends Fragment {
    private boolean refreshOnResume = false;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(refreshOnResume){
            getActivity().finish();
            startActivity(getActivity().getIntent());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshOnResume = true;
    }

}
