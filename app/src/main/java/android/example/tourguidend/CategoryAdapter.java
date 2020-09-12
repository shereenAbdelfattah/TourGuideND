package android.example.tourguidend;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;


public class CategoryAdapter extends FragmentPagerAdapter {

    /**
     * Context of the app
     */
    private Context mContext;


    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        transaction.commit();

        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new TouristFragment();
        } else if (position == 2) {
            return new ParksFragment();
        }else {
            return new ResturantFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
           // return tabTitles[0];
            return mContext.getString(R.string.home);
        } else if (position == 1) {
          //  return tabTitles[1];

             return mContext.getString(R.string.Tourist);
        } else if (position == 2) {
//            return tabTitles[2];

            return mContext.getString(R.string.parks);
        } else {
//            return tabTitles[3];

            return mContext.getString(R.string.resturants);
        }
    }


}
