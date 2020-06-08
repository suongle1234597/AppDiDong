package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.net.Network;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity<item> extends AppCompatActivity {
    DrawerLayout drawer_layout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameHome, new HomeFragment()).commit();
    }

    private void setControl() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nv = (NavigationView)  findViewById(R.id.nv);
        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open, R.string.drawer_close);
        drawer_layout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nv);
        Menu menu = nv.getMenu();

        if(Login.idNguoiDung != 0  && !Login.tenNguoiDung.equals("")) {
            MenuItem item = menu.findItem(R.id.item_login).setTitle(Login.tenNguoiDung);
            menu.findItem(R.id.item_register).setVisible(false);
            menu.findItem(R.id.item_love).setVisible(true);
            menu.findItem(R.id.item_logout).setVisible(true);
        }
        else {
            MenuItem item = menu.findItem(R.id.item_login).setTitle("Đăng nhập");
            menu.findItem(R.id.item_register).setVisible(true);
            menu.findItem(R.id.item_love).setVisible(false);
            menu.findItem(R.id.item_logout).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void selectItemDrawer(MenuItem item) {
        Fragment myFragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.item_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.item_category:
                fragmentClass = CategoryFragment.class;
                break;
            case R.id.item_search:
                fragmentClass = SearchFragment.class;
                break;
            case R.id.item_love:
                fragmentClass = LoveFragment.class;
                break;
            case R.id.item_login:
                fragmentClass = Login.class;
                break;
            case R.id.item_register:
                fragmentClass = Register_Fragment.class;
                break;
            case R.id.item_about:
                fragmentClass = AboutAppFragment.class;
                break;
            case R.id.item_logout:
                Login.idNguoiDung = 0;
                Login.tenNguoiDung = "";
                Menu menu = nv.getMenu();
                menu.findItem(R.id.item_login).setTitle("Đăng nhập");
                menu.findItem(R.id.item_register).setVisible(true);
                menu.findItem(R.id.item_love).setVisible(false);
                menu.findItem(R.id.item_logout).setVisible(false);
                fragmentClass = HomeFragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }
        try{
            myFragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameHome, myFragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawer_layout.closeDrawers();
    }

    private void setupDrawerContent (NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
