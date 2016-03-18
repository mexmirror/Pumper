package ch.chiodo.pumper.presentation.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.R;
import ch.chiodo.pumper.persistence.PumperService;
import ch.chiodo.pumper.presentation.model.Training;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final FragmentManager fragmentManager = getFragmentManager();

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSupportActionBar();
        setUpNavigationDrawer();
        setBackStackChangedListener();
        addFragment(new HomeFragment());
        debug();
    }
    private void debug(){
        PumperApplication app = (PumperApplication)getApplicationContext();
        PumperService s = app.getPumperService();
        List<Training> list = s.getTrainings();
        for (Training t : list) {
            Log.d("TrainingList", Long.toString(t.getId()));
        }
    }
    private void setUpSupportActionBar(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setUpNavigationDrawer(){
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        if(navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }
    private void makeSimpleSnackbar(String message){
        View position = findViewById(R.id.snackbarPosition);
        Snackbar.make(position, message, Snackbar.LENGTH_LONG).show();
    }
    private void makeSnackbarWithAction(String message, String actionMessage, View.OnClickListener action){
        //TODO: implement
    }
    private void setBackStackChangedListener(){
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // Update UI
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() < 0){
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    private void addFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }
    private void replaceFragment(Fragment fragment, boolean popBack){
         FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        if(popBack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                replaceFragment(new SettingsFragment(), true);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.drawerHome:
                replaceFragment(new HomeFragment(), false);
                break;
            case R.id.drawerEdit:
                replaceFragment(new EditTrainingFragment(), false);
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
