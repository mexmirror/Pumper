package ch.chiodo.pumper.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import ch.chiodo.pumper.R;
import ch.chiodo.pumper.controller.ExerciseController;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final FragmentManager fragmentManager = getFragmentManager();
    private ExerciseController exerciseController = new ExerciseController();
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSupportActionBar();
        setUpNavigationDrawer();
        setBackStackChangedListener();
        addFragment(new HomeFragment());
        loadTrainings();
    }
    private void setUpSupportActionBar(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO: handle nullpointer
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setUpNavigationDrawer(){
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void loadTrainings(){
        exerciseController.newExercise("A2", 22.4, 10);
        exerciseController.newExercise("E1", 70.0, 8);
        exerciseController.newExercise("B3", 100.0, 9);
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
    private void addFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }
    private void replaceFragment(Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.drawerHome:
                replaceFragment(new HomeFragment());
                break;
            case R.id.drawerEdit:
                replaceFragment(new EditTrainingFragment());
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
