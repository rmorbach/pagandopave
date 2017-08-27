package hackathon.com.pagandopave.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.adapters.PrizesAdapter;
import hackathon.com.pagandopave.interfaces.OnFragmentPromotionInteractListener;

public class PrizesFragment extends Fragment {
    public PrizesFragment() {
        // Required empty public constructor
    }

    public static PrizesFragment newInstance() {
        PrizesFragment fragment = new PrizesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_prizes, container, false);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new PrizesAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

        return mRecyclerView;
    }
}
