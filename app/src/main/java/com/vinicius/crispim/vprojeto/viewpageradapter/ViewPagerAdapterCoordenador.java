package com.vinicius.crispim.vprojeto.viewpageradapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.vinicius.crispim.vprojeto.fragment.HomeCoordenadorFragment;
import com.vinicius.crispim.vprojeto.fragment.HomeFragment;
import com.vinicius.crispim.vprojeto.fragment.PerfilFragment;
import com.vinicius.crispim.vprojeto.fragment.SolicitacoesFragment;
import com.vinicius.crispim.vprojeto.fragment.SuasHorasCoordenadorFragment;
import com.vinicius.crispim.vprojeto.fragment.SuasHorasFragment;

public class ViewPagerAdapterCoordenador extends FragmentStatePagerAdapter {

    public ViewPagerAdapterCoordenador(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeCoordenadorFragment();
            case 1:
                return new SuasHorasCoordenadorFragment();
            case 2:
                return new SolicitacoesFragment();
            default:
                return new HomeCoordenadorFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
