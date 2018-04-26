package tw.com.cct.ms2.shirink_app_git;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_content extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_content, container, false);



        Spinner plan_spin_num[]=new Spinner[16];
        ArrayAdapter<CharSequence> arrayAdapter_plan_spinner=ArrayAdapter.createFromResource(this.getActivity(),R.array.plan,R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner

        plan_spin_num_view_init(rootView, plan_spin_num);
        
        plan_spin_adapter_init(plan_spin_num, arrayAdapter_plan_spinner);
        




        return rootView;
    }

    private void plan_spin_num_view_init(View rootView, Spinner[] plan_spin_num) {
        plan_spin_num[0]=rootView.findViewById(R.id.plan_spin_num_1);
        plan_spin_num[1]=rootView.findViewById(R.id.plan_spin_num_2);
        plan_spin_num[2]=rootView.findViewById(R.id.plan_spin_num_3);
        plan_spin_num[3]=rootView.findViewById(R.id.plan_spin_num_4);
        plan_spin_num[4]=rootView.findViewById(R.id.plan_spin_num_5);
        plan_spin_num[5]=rootView.findViewById(R.id.plan_spin_num_6);
        plan_spin_num[6]=rootView.findViewById(R.id.plan_spin_num_7);
        plan_spin_num[7]=rootView.findViewById(R.id.plan_spin_num_8);
        plan_spin_num[8]=rootView.findViewById(R.id.plan_spin_num_9);
        plan_spin_num[9]=rootView.findViewById(R.id.plan_spin_num_10);
        plan_spin_num[10]=rootView.findViewById(R.id.plan_spin_num_11);
        plan_spin_num[11]=rootView.findViewById(R.id.plan_spin_num_12);
        plan_spin_num[12]=rootView.findViewById(R.id.plan_spin_num_13);
        plan_spin_num[13]=rootView.findViewById(R.id.plan_spin_num_14);
        plan_spin_num[14]=rootView.findViewById(R.id.plan_spin_num_15);
        plan_spin_num[15]=rootView.findViewById(R.id.plan_spin_num_16);
    }

    private void plan_spin_adapter_init(Spinner[] plan_spin_num, ArrayAdapter<CharSequence> arrayAdapter_plan_spinner) {
        plan_spin_num[0].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[1].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[2].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[3].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[4].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[5].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[6].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[7].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[8].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[9].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[10].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[11].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[12].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[13].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[14].setAdapter(arrayAdapter_plan_spinner);
        plan_spin_num[15].setAdapter(arrayAdapter_plan_spinner);
      
    
    }
}

