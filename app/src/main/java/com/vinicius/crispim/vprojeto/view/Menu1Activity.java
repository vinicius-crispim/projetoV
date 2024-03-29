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

public class Menu1Activity extends AppCompatActivity {
    private long con;
    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private Aluno aluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.menu1_activity);
        Intent intentreceptor = getIntent();
        Bundle parametros = intentreceptor.getExtras();
        aluno = new Aluno();
        Curso curso = new Curso();
        aluno.setNome(parametros.getString("nome"));
        aluno.setSenha(parametros.getString("senha"));
        aluno.setCelular(parametros.getString("celular"));
        aluno.setCPF(parametros.getString("CPF"));
        aluno.setEmail(parametros.getString("email"));
        aluno.setMatricula(parametros.getInt("matricula"));
        aluno.setHorasFeitas(parametros.getInt("horasfeitas"));
        aluno.setHorasFaltando(parametros.getInt("horasfaltando"));
        curso.setHorasnecessarias(parametros.getInt("horasnecessariascurso"));
        curso.setId(parametros.getInt("idcurso"));
        curso.setNome(parametros.getString("nomecurso"));
        Coordenador coordenador = new Coordenador();
        coordenador.setNome(parametros.getString("nomecoordenador"));
        coordenador.setSenha(parametros.getString("senhacoordenador"));
        coordenador.setCelular(parametros.getString("celularcoordenador"));
        coordenador.setCPF(parametros.getString("CPFcoordenador"));
        coordenador.setEmail(parametros.getString("emailcoordenador"));
        coordenador.setId(parametros.getInt("idcoordenador"));
        curso.setCoordenador(coordenador);
        aluno.setCurso(curso);
        Log.i(AppUtil.TAG, "onCreate: Aluno:"+aluno);
        mNavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setVisibility(View.INVISIBLE);
        setUpViewPager();

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_suashoras:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_perfil:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_historico:
                        mViewPager.setCurrentItem(3);
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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_suashoras).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_perfil).setChecked(true);
                        break;
                        case 3:
                        mNavigationView.getMenu().findItem(R.id.action_historico).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public Aluno getAluno(){
        return aluno;
    }
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}