package br.furb.seca;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import br.furb.seca.controller.Controller;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private MyFragment fragmentAtual;
    private Controller controller;

    private MyFragment[] appFragments = { new DashboardFragment(0),  
	    new GradeHorariaFragment(1), 
	    new DisciplinasFragment(2), 
	    new CompromissosFragment(4) };
    private int lastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(
		R.id.navigation_drawer);
	mTitle = getTitle();

	// Set up the drawer.
	mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	controller = new Controller(this);
	if (savedInstanceState == null) {
	    // ensure this callback ignores this transaction
	    this.lastPosition = 0;
	    getFragmentManager().beginTransaction().replace(R.id.container, appFragments[0]).commit();

	    controller.sincronizarWebService((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
	}
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
	if (position == this.lastPosition) {
	    return;
	}
	FragmentManager fragmentManager = getFragmentManager();

	if (position < 0 || position >= appFragments.length) {
	    return;
	}
	this.fragmentAtual = appFragments[position];

	boolean goingToDash = position == 0;
	int enterAnim = goingToDash ? R.animator.pop_in : R.animator.slide_in_left;
	int exitAnim = goingToDash ? R.animator.slide_out_right : R.animator.pop_out;

	fragmentManager.beginTransaction() //
		.setCustomAnimations(enterAnim, exitAnim) //
		.replace(R.id.container, this.fragmentAtual) //
		.commit();

	this.lastPosition = position;
    }

    @Override
    public void onBackPressed() {
	if (this.lastPosition == 0) {
	    super.onBackPressed();
	} else {
	    mNavigationDrawerFragment.selectItem(0);
	    updateDashBoardTitle();
	}
    }

    private void updateDashBoardTitle() {
	getActionBar().setTitle(R.string.title_dashboard);
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

    @Override
    public void onAtualizar() {
	controller.sincronizarWebService((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
	if (this.fragmentAtual != null)
	    this.fragmentAtual.Atualizar();
    }

}
