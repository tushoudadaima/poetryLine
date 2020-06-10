package com.example.poetryline.detail.Send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.poetryline.R;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BlankFragment2 extends Fragment {
    private aaApapter pinAdapter;
    private List<pinn> pinlun=new ArrayList<>();
    private SmartRefreshLayout srl;
    private String biaoaaa;
    private String bibb;
    private String res;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container,false);
        srl =view.findViewById(R.id.srl);
        srl.setRefreshHeader(new PhoenixHeader(this.getContext()));
        srl.setRefreshFooter(new ClassicsFooter(this.getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        srl.setReboundDuration(300);

        ListView listView = view.findViewById(R.id.dingdans);
        pinAdapter = new aaApapter(getActivity(), pinlun, R.layout.items);
        listView.setAdapter(pinAdapter);

        return view;
    }

}
