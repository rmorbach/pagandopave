package hackathon.com.pagandopave.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hackathon.com.pagandopave.adapters.PromotionAdapter;
import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.interfaces.OnFragmentPromotionInteractListener;
import hackathon.com.pagandopave.model.Promotion;

public class PromotionFragment extends Fragment {
    private static final String ARG_PROMOTIONS = "promotions";

    private List<Promotion> promotionList;

    private OnFragmentPromotionInteractListener mListener;

    public PromotionFragment() {
        // Required empty public constructor
    }

    public static PromotionFragment newInstance(ArrayList<Promotion> promotions) {
        PromotionFragment fragment = new PromotionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PROMOTIONS, promotions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            promotionList = getArguments().getParcelableArrayList(ARG_PROMOTIONS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_promotion, container, false);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new PromotionAdapter(this, promotionList);
        mRecyclerView.setAdapter(mAdapter);

        return mRecyclerView;
    }

    public void onButtonPressed(Promotion promotion) {
        if (mListener != null) {
            mListener.onPromotionClicked(promotion);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentPromotionInteractListener) {
            mListener = (OnFragmentPromotionInteractListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentPromotionInteractListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
