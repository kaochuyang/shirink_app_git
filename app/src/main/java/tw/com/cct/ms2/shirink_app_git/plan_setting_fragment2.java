package tw.com.cct.ms2.shirink_app_git;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by user on 2018/4/17.
 */

public class plan_setting_fragment2 extends plan_setting_content {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_setting_fragment2, container, false);
        Spinner plan_spin_num[]=new Spinner[16];
        ArrayAdapter<CharSequence> arrayAdapter_plan_spinner=ArrayAdapter.createFromResource(this.getActivity(),R.array.plan,R.layout.myspinner_style);
        //  arrayAdapter_tod_spinner

        plan_spin_num_view_init(rootView, plan_spin_num);

        plan_spin_adapter_init(plan_spin_num, arrayAdapter_plan_spinner);
        return rootView;
    }

    private void plan_spin_num_view_init(View rootView, Spinner[] plan_spin_num) {
        plan_spin_num[0]=rootView.findViewById(R.id.plan_spin_num_33);
        plan_spin_num[1]=rootView.findViewById(R.id.plan_spin_num_34);
        plan_spin_num[2]=rootView.findViewById(R.id.plan_spin_num_35);
        plan_spin_num[3]=rootView.findViewById(R.id.plan_spin_num_36);
        plan_spin_num[4]=rootView.findViewById(R.id.plan_spin_num_37);
        plan_spin_num[5]=rootView.findViewById(R.id.plan_spin_num_38);
        plan_spin_num[6]=rootView.findViewById(R.id.plan_spin_num_39);
        plan_spin_num[7]=rootView.findViewById(R.id.plan_spin_num_40);
        plan_spin_num[8]=rootView.findViewById(R.id.plan_spin_num_41);
        plan_spin_num[9]=rootView.findViewById(R.id.plan_spin_num_42);
        plan_spin_num[10]=rootView.findViewById(R.id.plan_spin_num_43);
        plan_spin_num[11]=rootView.findViewById(R.id.plan_spin_num_44);
        plan_spin_num[12]=rootView.findViewById(R.id.plan_spin_num_45);
        plan_spin_num[13]=rootView.findViewById(R.id.plan_spin_num_46);
        plan_spin_num[14]=rootView.findViewById(R.id.plan_spin_num_47);
        plan_spin_num[15]=rootView.findViewById(R.id.plan_spin_num_48);
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
