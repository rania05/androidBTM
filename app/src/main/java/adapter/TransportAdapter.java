package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entity.Transport;
import tn.esprit.btm.R;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.MyViewHolder> {

    private List<Transport> transportsList;
    private Transport transport;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView htv, stv, vtv, ltv, ntv;

        public MyViewHolder(View view) {
            super(view);
            htv = view.findViewById(R.id.TransportHeure);
            stv = view.findViewById(R.id.TransportStation);
            vtv = view.findViewById(R.id.TransportVille);
            ltv = view.findViewById(R.id.TransportLigne);
            ntv = view.findViewById(R.id.TransportNumero);

        }
    }


    public TransportAdapter(List<Transport> transportsList) {
        this.transportsList = transportsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transport_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        transport = transportsList.get(position);
        holder.htv.setText(transport.getHeure());
        holder.ltv.setText(transport.getLigne());
        holder.ntv.setText(String.valueOf(transport.getNumero()));
        holder.vtv.setText(transport.getRegion());
        holder.stv.setText(transport.getType());
    }

    @Override
    public int getItemCount() {
        return transportsList.size();
    }
}