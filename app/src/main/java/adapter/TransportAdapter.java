package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entity.Transport;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.activies.AddTickets;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.MyViewHolder> {

    private List<Transport> transportsList;
    private Transport transport;



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView htv, stv, vtv, ltv, ntv;
        public ImageView navigationBtn;

        public MyViewHolder(View view) {
            super(view);
            htv = view.findViewById(R.id.TransportHeure);
            stv = view.findViewById(R.id.TransportStation);
            vtv = view.findViewById(R.id.TransportVille);
            ltv = view.findViewById(R.id.TransportLigne);
            ntv = view.findViewById(R.id.TransportNumero);
            navigationBtn = view.findViewById(R.id.btnAddTicket);
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
        holder.stv.setText(transport.getDepart());
        holder.navigationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(v.getContext(), "click detected", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), AddTickets.class);
                intent.putExtra("moyen",transportsList.get(position).getType());
                intent.putExtra("ligne",transportsList.get(position).getLigne());
                intent.putExtra("gouv",transportsList.get(position).getRegion());
                intent.putExtra("depart",transportsList.get(position).getDepart());
                v.getContext().startActivity(intent);
                //es2el
            }
        });
    }



    @Override
    public int getItemCount() {
        return transportsList.size();
    }

}