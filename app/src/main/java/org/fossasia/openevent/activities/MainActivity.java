package org.fossasia.openevent.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import org.fossasia.openevent.NavigationDrawerFragment;
import org.fossasia.openevent.R;
import org.fossasia.openevent.fragments.MapFragment;
import org.fossasia.openevent.fragments.SpeakerlistFragment;
import org.fossasia.openevent.fragments.SponsorsFragment;
import org.fossasia.openevent.fragments.TracksFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final int FRAGMENT_TRACKS = 0;
    public static final int FRAGMENT_SPEAKERS = 1;
    public static final int FRAGMENT_SPONSORS = 2;
    public static final int FRAGMENT_MAP = 3;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment newFragment;
        switch (position) {
            case FRAGMENT_MAP: //Map
                newFragment = MapFragment.newInstance();
                break;
            case FRAGMENT_SPONSORS: //Sponsors
                newFragment = SponsorsFragment.newInstance();
                break;
            case FRAGMENT_SPEAKERS: //Speakers
                newFragment = SpeakerlistFragment.newInstance();
                break;
            case FRAGMENT_TRACKS: //Tracks
            default:
                newFragment = TracksFragment.newInstance();
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number-1) {
            case FRAGMENT_TRACKS:
                mTitle = getString(R.string.title_section_tracks);
                break;
            case FRAGMENT_SPEAKERS:
                mTitle = getString(R.string.title_section_speakerlist);
                break;
            case FRAGMENT_SPONSORS:
                mTitle = getString(R.string.title_section_sponsors);
                break;
            case FRAGMENT_MAP:
                mTitle = getString(R.string.title_section_map);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
}
