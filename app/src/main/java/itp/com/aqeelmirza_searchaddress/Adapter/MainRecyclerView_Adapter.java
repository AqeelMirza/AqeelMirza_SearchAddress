package itp.com.aqeelmirza_searchaddress.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import itp.com.aqeelmirza_searchaddress.R;
import itp.com.aqeelmirza_searchaddress.Utils.List_DataModel;


public class MainRecyclerView_Adapter extends RecyclerView.Adapter<MainRecyclerView_Adapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<List_DataModel> locList;
    private LayoutInflater mInflater;

    public MainRecyclerView_Adapter(Context c, int resource, ArrayList<List_DataModel> locList) {
        this.context = c;
        this.resource = resource;
        this.locList = locList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(locList.get(position).getAddressName());
        holder.pickup.setText("Pickup : " + locList.get(position).getPickup());

    }

    @Override
    public int getItemCount() {
        return locList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, pickup;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.listitem_name_tv);
            pickup = itemView.findViewById(R.id.listitem_pickup_tv);

        }
    }
}
