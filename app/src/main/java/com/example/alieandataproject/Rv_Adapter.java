package com.example.alieandataproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Rv_Adapter extends RecyclerView.Adapter<Rv_Adapter.ViewHolder> {

  List<PostOffice> list;
  Context context;

  public Rv_Adapter(List<PostOffice> list, Context context) {
    this.list = list;
    this.context = context;
  }

  @Override
  public Rv_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.rv_layout_file, parent, false);
    return new Rv_Adapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(Rv_Adapter.ViewHolder holder, int position) {
    PostOffice postOffice = list.get(position);
    holder.tv.setText(
        "Name :- " + postOffice.getName() +"\n" +
        "Description :- " + postOffice.getDescription() + "\n" +
        "BranchType :- " + postOffice.getBranchType() + "\n" +
        "DeliveryStatus :- " + postOffice.getBranchType() + "\n" +
        "Circle :- " + postOffice.getCircle() + "\n" +
        "District :- " + postOffice.getDistrict() + "\n" +
        "Division :- " + postOffice.getDivision() + "\n" +
        "Region :- " + postOffice.getRegion() + "\n" +
        "Block :- " + postOffice.getBlock() + "\n" +
        "State :- " + postOffice.getState() + "\n" +
        "Country :- " + postOffice.getCountry() + "\n" +
        "Pincode :- " + postOffice.getPincode()
    );
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tv;

    public ViewHolder(View itemView) {
      super(itemView);
      tv = itemView.findViewById(R.id.tv_data);
    }
  }
}
