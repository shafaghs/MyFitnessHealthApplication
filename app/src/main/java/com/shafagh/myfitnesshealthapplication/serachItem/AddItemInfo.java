package com.shafagh.myfitnesshealthapplication.serachItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import com.shafagh.myfitnesshealthapplication.Meal;
import com.shafagh.myfitnesshealthapplication.R;
import com.shafagh.myfitnesshealthapplication.Recent;
import java.util.ArrayList;
import java.util.List;

public class AddItemInfo extends AppCompatActivity {
    private String searchKey;
    private SearchView searchView;
    private View rootView;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar;
        TabLayout tabLayout;
        ViewPager viewPager;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_info);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //searchView complete
        rootView = findViewById(android.R.id.content);
        ctx = this.getApplicationContext();
        searchView = (SearchView) findViewById(R.id.search_food);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener( ) {
            @Override
            public boolean onQueryTextChange( String newText ) {
                searchKey = searchView.getQuery().toString();
                Log.i("searchKey",searchKey);
                if (searchKey != null) {
                    SearchBgTask bgTask = new SearchBgTask(ctx, rootView);
                    bgTask.execute(searchKey);
                }
                return false;
            }

            @Override
            public boolean   onQueryTextSubmit(String query) {
                return true;
            }
        });
        //search End

    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Frequent(), "Search Result");
        adapter.addFragment(new Recent(), "Recent");
        adapter.addFragment(new Meal(), "Frequent");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent parent = NavUtils.getParentActivityIntent(this);
            NavUtils.shouldUpRecreateTask(this,parent);
        }
        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
