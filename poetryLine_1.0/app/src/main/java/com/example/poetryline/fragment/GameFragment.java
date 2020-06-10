package com.example.poetryline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.poetryline.FlyActivity;
import com.example.poetryline.GameActivity;
import com.example.poetryline.R;
import com.example.poetryline.TitleActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {
    private ImageView btn_show;
    private ImageView btn_title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment,container,false);
        btn_show = view.findViewById(R.id.btn_show);
        btn_title = view.findViewById(R.id.btn_title);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FlyActivity.class);
                startActivity(intent);
            }
        });

        btn_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TitleActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
