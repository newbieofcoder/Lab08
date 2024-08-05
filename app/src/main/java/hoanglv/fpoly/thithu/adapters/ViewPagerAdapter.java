package hoanglv.fpoly.thithu.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import hoanglv.fpoly.thithu.ui.fragment1;
import hoanglv.fpoly.thithu.ui.fragment2;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return fragment2.newInstance();
        }
        return fragment1.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
