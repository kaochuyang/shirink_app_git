package tw.com.cct.ms2.shirink_app_git;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_fragment extends plan_setting_content {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_fragment, container, false);

        return rootView;
    }
}
