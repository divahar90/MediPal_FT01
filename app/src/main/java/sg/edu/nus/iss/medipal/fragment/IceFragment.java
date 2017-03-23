package sg.edu.nus.iss.medipal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sg.edu.nus.iss.medipal.R;
import sg.edu.nus.iss.medipal.pojo.Ice;
import sg.edu.nus.iss.medipal.activity.IceActivity;
import sg.edu.nus.iss.medipal.adapter.IceAdapter;
import sg.edu.nus.iss.medipal.manager.IceManager;

import java.util.List;

/**
 * Created by levis on 3/16/2017.
 */

public class IceFragment extends Fragment {

    private RecyclerView _iceView;
    private List<Ice> _iceList;
    private IceAdapter _iceAdapter;
    private IceManager _iceManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ice_list, container, false);

        _iceManager = new IceManager();
        _iceList = _iceManager.getIces(getContext());

        if(_iceList.isEmpty()) {
            TextView txtView = (TextView) view.findViewById(R.id.icelist_placeholder);
            txtView.setText("No Contact(ICE)s found");
            txtView.setVisibility(View.VISIBLE);
        } else {
            _iceView = (RecyclerView) view.findViewById(R.id.icerecycler_view);
            populateRecyclerView();
        }

        FloatingActionButton aFab = (FloatingActionButton)view.findViewById(R.id.ice_fab);
        aFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iceIntent = new Intent(getContext(), IceActivity.class);
                iceIntent.putExtra("isEdit", false);
                getActivity().startActivityForResult(iceIntent, 404);
            }
        });

        return view;
    }

    public void onResume()
    {
        super.onResume();

        _iceList = _iceManager.getIces(getContext());

        this.populateRecyclerView();
    }


    private void populateRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        _iceView.setLayoutManager(layoutManager);

        _iceAdapter = new IceAdapter(getContext(), _iceList);
        _iceView.setAdapter(_iceAdapter);
    }

}
