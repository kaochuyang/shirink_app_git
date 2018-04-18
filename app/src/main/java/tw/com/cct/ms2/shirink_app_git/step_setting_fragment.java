package tw.com.cct.ms2.shirink_app_git;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2018/4/17.
 */

public class step_setting_fragment extends android.support.v4.app.Fragment{

    private int page;
    public static View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

System.out.println("hello  1");
        System.out.println("hello page="+page);
        switch(page)
        {
            case 1:
            rootView=inflater.inflate(R.layout.step_setting_fragment1, container, false);
                System.out.println("hello  2");
                return rootView;
            case 2:
                rootView=inflater.inflate(R.layout.step_setting_fragment2, container, false);
                return rootView;
            case 3:
                rootView=inflater.inflate(R.layout.step_setting_fragment3, container, false);
                return rootView;
            case 4:
                rootView=inflater.inflate(R.layout.step_setting_fragment4, container, false);
                return rootView;
            case 5:
                rootView=inflater.inflate(R.layout.step_setting_fragment5, container, false);
                return rootView;
            case 6:
                rootView=inflater.inflate(R.layout.step_setting_fragment6, container, false);
                return rootView;
            case 7:
                rootView=inflater.inflate(R.layout.step_setting_fragment7, container, false);
                return rootView;
            case 8:
                rootView=inflater.inflate(R.layout.step_setting_fragment8, container, false);
                System.out.println("hello  3");
                return rootView;
            default:
                return null;

        }

    }

    public int getPagenum(int page_num)
    {
        page=page_num;
        System.out.println("hello  4");
        return page;

    }


}

