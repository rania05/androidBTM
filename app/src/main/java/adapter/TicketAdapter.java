package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entity.Ticket;
import Entity.Transport;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.activies.AddTickets;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {

    private List<Ticket> ticketList;
    private Ticket ticket;



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView htv, stv, vtv, ltv, ntv,Atv,ptv;
        public ImageView navigationBtn;

        public MyViewHolder(View view) {
            super(view);
            Atv = view.findViewById(R.id.TicketStationArrive);
            htv = view.findViewById(R.id.TicketDate);
            stv = view.findViewById(R.id.TicketStationDepart);
            vtv = view.findViewById(R.id.TicketVille);
            ltv = view.findViewById(R.id.TicketLigne_tv);
            ntv = view.findViewById(R.id.TicketNumero);
            ptv = view.findViewById(R.id.TicketPrix);
        }


    }


    public TicketAdapter(List<Ticket> ticketList) {
        this.ticketList = ticketList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_list_item, parent, false);

        return new MyViewHolder(itemView);
    }
    //stacktrace = logcat = terminal eli fih el errors = console = logcat
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ticket = ticketList.get(position);
        holder.htv.setText(ticket.getDate());
        holder.ltv.setText(ticket.getLigne()); //get ligne s7i7a ? mawjouda ?
        holder.ntv.setText(String.valueOf(ticket.getId()));
        holder.vtv.setText(ticket.getGouv());
        holder.stv.setText(ticket.getStationDepart());
        holder.Atv.setText(ticket.getStationArrive());
        holder.ptv.setText(String.valueOf(ticket.getPrix()));


    }



    @Override
    public int getItemCount() {
        return ticketList.size();
    }

}