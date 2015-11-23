package com.habitrpg.android.habitica;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicmicky.habitrpgwrapper.lib.models.HabitRPGUser;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by admin on 18/11/15.
 */
public abstract class BaseFragment extends Fragment {

    public MainActivity activity;
    public TabLayout tabLayout;
    APIHelper mAPIHelper;
    protected HabitRPGUser user;
    public boolean usesTabLayout;
    public int fragmentSidebarPosition;

    public void setUser(HabitRPGUser user) { this.user = user; }
    public void updateUserData(HabitRPGUser user) { this.user = user; }
    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }
    public void setActivity(MainActivity activity) {this.activity = activity; }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.usesTabLayout) {
            tabLayout.setVisibility(View.VISIBLE);
        } else {
            tabLayout.setVisibility(View.GONE);
        }

        // Receive Events
        EventBus.getDefault().register(this);

        setHasOptionsMenu(true);

        activity.setActiveFragment(this);

        return null;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);

        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
