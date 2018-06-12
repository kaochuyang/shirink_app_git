package tw.com.cct.ms2.shirink_app_git;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


/**
 * Created by user on 2018/4/17.
 */

public class step_setting_fragment extends android.support.v4.app.Fragment {

    private int page;
    public static View rootView;
    CheckBox[] checkBox_red_ = new CheckBox[6];
    CheckBox[] checkBox_yellow_ = new CheckBox[6];
    CheckBox[] checkBox_left_ = new CheckBox[6];
    CheckBox[] checkBox_green_ = new CheckBox[6];
    CheckBox[] checkBox_stright_ = new CheckBox[6];
    CheckBox[] checkBox_right_ = new CheckBox[6];
    CheckBox[] checkBox_ped_red_ = new CheckBox[6];
    CheckBox[] checkBox_ped_flash_ = new CheckBox[6];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        System.out.println("hello  1");
        System.out.println("hello page=" + page);
        rootView = inflater.inflate(R.layout.step_setting_fragment1, container, false);
        checkbox_findview();




        return getView(inflater, container);
    }

    private void checkbox_findview() {
        for (int i = 1; i < 7; i++) {
            String idname_red = "checkBox_red_" + String.format("%d", i);
            int resID_red = getResources().getIdentifier(idname_red, "id", getActivity().getPackageName());
            checkBox_red_[i - 1] = rootView.findViewById(resID_red);
            String idname_yellow = "checkBox_yellow_" + String.format("%d", i);
            int resID_yellow = getResources().getIdentifier(idname_yellow, "id", getActivity().getPackageName());
            checkBox_yellow_[i - 1] = rootView.findViewById(resID_yellow);
            String idname_left = "checkBox_left_" + String.format("%d", i);
            int resID_left = getResources().getIdentifier(idname_left, "id", getActivity().getPackageName());
            checkBox_left_[i - 1] = rootView.findViewById(resID_left);
            String idname_green = "checkBox_green_" + String.format("%d", i);
            int resID_green = getResources().getIdentifier(idname_green, "id", getActivity().getPackageName());
            checkBox_green_[i - 1] = rootView.findViewById(resID_green);
            String idname_stright = "checkBox_stright_" + String.format("%d", i);
            int resID_stright = getResources().getIdentifier(idname_stright, "id", getActivity().getPackageName());
            checkBox_stright_[i - 1] = rootView.findViewById(resID_stright);
            String idname_right = "checkBox_right_" + String.format("%d", i);
            int resID_right = getResources().getIdentifier(idname_right, "id", getActivity().getPackageName());
            checkBox_right_[i - 1] = rootView.findViewById(resID_right);
            String idname_ped_red = "checkBox_ped_red_" + String.format("%d", i);
            int resID_ped_red = getResources().getIdentifier(idname_ped_red, "id", getActivity().getPackageName());
            checkBox_ped_red_[i - 1] = rootView.findViewById(resID_ped_red);
            String idname_ped_flash = "checkBox_ped_flash_" + String.format("%d", i);
            int resID_ped_flash = getResources().getIdentifier(idname_ped_flash, "id", getActivity().getPackageName());
            checkBox_ped_flash_[i - 1] = rootView.findViewById(resID_ped_flash);


        }
    }

    @Nullable
    private View getView(LayoutInflater inflater, ViewGroup container) {

        switch (page) {
            case 1:

                System.out.println("hello  2");

                return rootView;
//            case 2:
//                rootView = inflater.inflate(R.layout.step_setting_fragment2, container, false);
//                return rootView;
//            case 3:
//                rootView = inflater.inflate(R.layout.step_setting_fragment3, container, false);
//                return rootView;
//            case 4:
//                rootView = inflater.inflate(R.layout.step_setting_fragment4, container, false);
//                return rootView;
//            case 5:
//                rootView = inflater.inflate(R.layout.step_setting_fragment5, container, false);
//                return rootView;
//            case 6:
//                rootView = inflater.inflate(R.layout.step_setting_fragment6, container, false);
//                return rootView;
//            case 7:
//                rootView = inflater.inflate(R.layout.step_setting_fragment7, container, false);
//                return rootView;
//            case 8:
//                rootView = inflater.inflate(R.layout.step_setting_fragment8, container, false);
//                System.out.println("hello  3");
//                return rootView;
            default:
                return rootView;

        }
    }

    public int getPagenum(int page_num) {
        page = page_num;
        System.out.println("hello  4");
        return page;

    }


}

