package tn.esprit.btm.UI.activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import tn.esprit.btm.R;
import tn.esprit.btm.UI.fragments.fragment_Hours;
import tn.esprit.btm.UI.fragments.fragment_Tickets;
import tn.esprit.btm.UI.fragments.fragment_abonnements;
import tn.esprit.btm.UI.fragments.fragment_home;
import tn.esprit.btm.UI.fragments.mapFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(new fragment_home());
    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }

    public void openFragHome(View view) {
        showFragment(new mapFragment());
    }
    public void openFragHours(View view) {
        showFragment(new fragment_Hours());
    }
    public void openFragTickets(View view) {
        showFragment(new fragment_Tickets());
    }
    public void openFragAbonnements(View view) {
        showFragment(new fragment_abonnements());
    }

}