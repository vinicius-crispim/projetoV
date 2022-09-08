package com.vinicius.crispim.vprojeto.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vinicius.crispim.vprojeto.R;
import com.vinicius.crispim.vprojeto.api.AppUtil;
import com.vinicius.crispim.vprojeto.model.Aluno;
import com.vinicius.crispim.vprojeto.model.Coordenador;
import com.vinicius.crispim.vprojeto.model.Curso;
import com.vinicius.crispim.vprojeto.viewpageradapter.ViewPagerAdapter;
import com.vinicius.crispim.vprojeto.viewpageradapter.ViewPagerAdapterCoordenador;

public class MenuCoordenadorActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private Coordenador coordenador;
    private long con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menucoordenador);
        Intent intentreceptor = getIntent();
        Bundle parametros = intentreceptor.getExtras();
        coordenador= new Coordenador();
        coordenador.setNome(parametros.getString("nome"));
        coordenador.setSenha(parametros.getString("senha"));
        coordenador.setCelular(parametros.getString("celular"));
        coordenador.setCPF(parametros.getString("CPF"));
        coordenador.setEmail(parametros.getString("email"));
        coordenador.setId(parametros.getInt("id"));
        Log.i(AppUtil.TAG, "onCreate: Coordenador:"+coordenador.getNome());
        mNavigationView = findViewById(R.id.bottom_nav_coordenador);
        mViewPager = findViewById(R.id.view_pager_coordenador);
        ViewPager viewPager=findViewById(R.id.view_pager_coordenador);
        setUpViewPager();
        viewPager.setVisibility(View.VISIBLE);

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home_coordenadores:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_suashoras_coordenadores:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_perfil_coordenadores:
                        mViewPager.setCurrentItem(2);
                        break;


                }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (con + 2000 > System.currentTimeMillis()) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Pressione duas vezes para sair", Toast.LENGTH_SHORT).show();
        }
        con = System.currentTimeMillis();
    }
    private void setUpViewPager(){
        ViewPagerAdapterCoordenador viewPagerAdapter = new ViewPagerAdapterCoordenador(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_home_coordenadores).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_suashoras_coordenadores).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_perfil_coordenadores).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public Coordenador getCoordenador(){
        return coordenador;
    }
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}