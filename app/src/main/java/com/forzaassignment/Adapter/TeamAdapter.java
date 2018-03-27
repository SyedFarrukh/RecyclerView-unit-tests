package com.forzaassignment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forzaassignment.Model.TeamModel;
import com.forzaassignment.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Farrukh on 2/10/2018.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private ArrayList<TeamModel> teamModels;
    private Context context;
    private LayoutInflater inflater;

    public TeamAdapter(ArrayList<TeamModel> teamModels, Context  context){
        this.teamModels = teamModels;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate (R.layout.team_row_items, parent, false);
        ButterKnife.bind(this,view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder viewHolder, int position) {
        TeamModel teamModel = teamModels.get(position);
        viewHolder.tv_name.setText(teamModel.getName());
        viewHolder.tv_national.setText(teamModel.getNational());
        viewHolder.tv_country_name.setText(teamModel.getCountryName());
    }

    @Override
    public int getItemCount() {
        return teamModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_national, tv_country_name;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_national = (TextView)itemView.findViewById(R.id.tv_national);
            tv_country_name = (TextView)itemView.findViewById(R.id.tv_country_name);
        }
    }
}
