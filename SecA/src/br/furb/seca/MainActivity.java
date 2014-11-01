package br.furb.seca;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

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

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		FragmentManager fragmentManager = getFragmentManager();
		
		Fragment newFrag;
		
		//TODO: substituir por switch
		if(position == 0)
		{
			newFrag = new DashboardFragment(position);
		}
		else if(position == 1)
		{
			newFrag = new GradeHorariaFragment(position);
		}
		else
		{
			newFrag = new DisciplinasFragment(position);
		}
		
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						newFrag).commit();
	}
	
	public void onSectionAttached(int number) {
		switch (number) {
		case 0:
			mTitle = getString(R.string.title_dashboard );
			break;
		case 1:
			mTitle = getString(R.string.title_grade_horaria);
			break;
		case 2:
			mTitle = getString(R.string.title_disciplinas);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}
}
