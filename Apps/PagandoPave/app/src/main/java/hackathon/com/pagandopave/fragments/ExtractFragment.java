package hackathon.com.pagandopave.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.adapters.ExtractAdapter;
import hackathon.com.pagandopave.model.Extract;

public class ExtractFragment extends Fragment {
    private static final String ARG_SALDO = "saldo";
    private static final String ARG_EXTRACT = "extract";

    private float saldo;
    private List<Extract> extracts;

    public ExtractFragment() {
        // Required empty public constructor
    }

    public static ExtractFragment newInstance(float saldo, ArrayList<Extract> extracts) {
        ExtractFragment fragment = new ExtractFragment();
        Bundle args = new Bundle();
        args.putFloat(ARG_SALDO, saldo);
        args.putParcelableArrayList(ARG_EXTRACT, extracts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            saldo = getArguments().getFloat(ARG_SALDO);
            extracts = getArguments().getParcelableArrayList(ARG_EXTRACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_extract, container, false);

        TextView saldoTextView = v.findViewById(R.id.saldo);

        String saldoString = getString(R.string.saldo, saldo);
        saldoTextView.setText(saldoString);

        // Inflate the layout for this fragment
        RecyclerView mRecyclerView = v.findViewById(R.id.extract_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new ExtractAdapter(extracts);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

}
