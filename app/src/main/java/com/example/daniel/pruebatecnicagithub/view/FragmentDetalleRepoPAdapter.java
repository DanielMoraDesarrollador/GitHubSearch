package com.example.daniel.pruebatecnicagithub.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentDetalleRepoPAdapter extends FragmentStatePagerAdapter {

    private List<FragmentDetalleRepo> fragmentDetalleRepos;

    public FragmentDetalleRepoPAdapter(FragmentManager fm, List<FragmentDetalleRepo> fragmentDetalleRepos) {
        super(fm);
        this.fragmentDetalleRepos = fragmentDetalleRepos;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentDetalleRepos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentDetalleRepos.size();
    }
}
