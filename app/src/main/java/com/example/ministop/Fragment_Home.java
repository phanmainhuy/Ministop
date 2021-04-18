package com.example.ministop;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment_Home extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.layout_options,container,false);
        ArrayList<Options> listContact = GetListContact();
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.recycleView_option);
        recyclerView.setAdapter(new OptionsAdapter_Recycle(getActivity(),listContact));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        return rootview;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private ArrayList<Options> GetListContact() {
        ArrayList<Options> contactlist = new ArrayList<Options>();
        contactlist.add(new Options(R.drawable.food_tteok,"Đồ ăn"));
        contactlist.add(new Options(R.drawable.drink,"Đồ uống"));
        contactlist.add(new Options(R.drawable.veggie,"Rau củ"));

        return contactlist;
    }
}
