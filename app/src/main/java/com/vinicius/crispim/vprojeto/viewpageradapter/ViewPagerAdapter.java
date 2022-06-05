package com.vinicius.crispim.vprojeto.viewpageradapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.vinicius.crispim.vprojeto.fragment.HistoricoFragment;
import com.vinicius.crispim.vprojeto.fragment.HomeFragment;
import com.vinicius.crispim.vprojeto.fragment.PerfilFragment;
import com.vinicius.crispim.vprojeto.fragment.SuasHorasFragment;
import com.vinicius.crispim.vprojeto.view.Menu1Activity;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SuasHorasFragment();
            case 2:
                return new PerfilFragment();
                case 3:
                return new HistoricoFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
