package sg.edu.nus.iss.medipal.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.iss.medipal.R;
import sg.edu.nus.iss.medipal.activity.AddMedicineActivity;
import sg.edu.nus.iss.medipal.adapter.MedicineAdapter;
import sg.edu.nus.iss.medipal.adapter.MedicineRecyclerAdapter;
import sg.edu.nus.iss.medipal.interfaces.AdapterCallbackInterface;
import sg.edu.nus.iss.medipal.pojo.HealthManager;
import sg.edu.nus.iss.medipal.pojo.Medicine;

/**
 * Created by : Qin Zhi Guo on 10-03-2017.
 * Description : Activity Class to control and show the medicine info
 * Modified by :
 * Reason for modification :
 */

public class MedicineFragment extends Fragment implements AdapterCallbackInterface {

    RecyclerView lv;
    //MedicineAdapter m_adapter;
    private MedicineRecyclerAdapter medicineAdapter;
    private HealthManager medicineManager;
    private List<Medicine> medicineList;
    private Context mContext;
    private View medicineFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FloatingActionButton aFab;

        medicineFragment = inflater.inflate(R.layout.activity_medicine, container, false);
        lv= (RecyclerView) medicineFragment.findViewById(R.id.lv_medicine);
        //get reference to medicine manager
        medicineManager = new HealthManager();
        //get reference to the current context(
        mContext = medicineFragment.getContext();
        medicineList = medicineManager.getMedicines(mContext);
        medicineManager.getCategorys(mContext);
        medicineManager.getReminders(mContext);
        if(medicineList == null || medicineList.isEmpty()){
            //show the "no medicine" message
            refreshView("No medications found");
        }
        else {
            populateMedicineRecyclerView();
        }

        aFab = (FloatingActionButton)medicineFragment.findViewById(R.id.fab);
        aFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivityForResult(new Intent(mContext, AddMedicineActivity.class),201);
            }
        });

        return medicineFragment;
    }

    private void populateMedicineRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        lv.setLayoutManager(mLayoutManager);
        //populate the adapter with appointments lists
        medicineAdapter = new MedicineRecyclerAdapter(mContext,medicineManager,medicineList,false,this);
        lv.setAdapter(medicineAdapter);
    }

    @Override
    public void refreshView(String message) {
        //if no health are found then the following message will be shown
        LinearLayout linearLayout = (LinearLayout) medicineFragment.findViewById(R.id.med_layout);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        TextView txtView = (TextView) medicineFragment.findViewById(R.id.placeholdertext);
        txtView.setVisibility(View.VISIBLE);
        lv.setVisibility(View.GONE);
        txtView.setText(message);

    }


}
