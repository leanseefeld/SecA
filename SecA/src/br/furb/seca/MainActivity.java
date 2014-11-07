package br.furb.seca;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in
     * {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(
		R.id.navigation_drawer);
	mTitle = getTitle();

	// Set up the drawer.
	mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
	FragmentManager fragmentManager = getFragmentManager();

	Fragment newFrag;

	switch (position) {
	case 1:
	    newFrag = new GradeHorariaFragment(position);
	    break;
	case 2:
	    newFrag = new DisciplinasFragment(position);
	    break;
	case 3:
	    newFrag = new NotasFragment(position);
	    break;
	case 4:
	    newFrag = new CompromissosFragment(position);
	    break;
	default:
	    newFrag = new DashboardFragment(position);
	}

	fragmentManager.beginTransaction().replace(R.id.container, newFrag).commit();
    }

    public void onSectionAttached(int number) {
	switch (number) {
	case 0:
	    mTitle = getString(R.string.title_dashboard);
	    break;
	case 1:
	    mTitle = getString(R.string.title_grade_horaria);
	    break;
	case 2:
	    mTitle = getString(R.string.title_disciplinas);
	    break;
	case 3:
	    mTitle = getString(R.string.title_notas);
	    break;
	case 4:
	    mTitle = getString(R.string.title_compromissos);
	    break;
	}
	ActionBar actionBar = getActionBar();
	actionBar.setTitle(mTitle);
    }
}
