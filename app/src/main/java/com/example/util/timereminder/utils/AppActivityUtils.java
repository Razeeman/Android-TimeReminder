package com.example.util.timereminder.utils;

import com.example.util.timereminder.main.MainFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AppActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, MainFragment mainFragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, mainFragment);
        transaction.commit();
    }

}
