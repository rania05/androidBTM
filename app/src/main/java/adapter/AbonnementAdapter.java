package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entity.Abonnement;
import Entity.Transport;
import tn.esprit.btm.R;
import tn.esprit.btm.UI.activies.AddTickets;

public class AbonnementAdapter extends RecyclerView.Adapter<AbonnementAdapter.MyViewHolder> {

    private List<Abonnement> abonnementsList;
    private Abonnement abonnement;



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView htv, stv, vtv, ltv, ntv,atv,ptv,dtv;


        public MyViewHolder(View view) {
            super(view);
            htv = view.findViewById(R.id.AbonnementDateDebut);
            stv = view.findViewById(R.id.AbonnementStationDepart);
            atv = view.findViewById(R.id.AbonnementStationArrive);
            vtv = view.findViewById(R.id.AbonnementVille);
            ltv = view.findViewById(R.id.AbonnementLigne_tv);
            ntv = view.findViewById(R.id.AbonnementNumero);
            ptv = view.findViewById(R.id.AbonnementPrix);
            dtv = view.findViewById(R.id.AbonnementDuree);

        }


    }


    public AbonnementAdapter(List<Abonnement>abonnementsList) {
        this.abonnementsList = abonnementsList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.abonnement_list_item, parent, false);

        return new MyViewHolder(itemView);
    }
    //stacktrace = logcat = terminal eli fih el errors = console = logcat
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        abonnement = abonnementsList.get(position);
        holder.htv.setText(abonnement.getDateDebut());
        holder.ltv.setText(abonnement.getLigne());
        holder.ntv.setText(String.valueOf(abonnement.getId()));
        holder.vtv.setText(abonnement.getVille());
        holder.stv.setText(abonnement.getDepart());
        holder.ptv.setText(String.valueOf(abonnement.getPrix()));
        holder.dtv.setText(abonnement.getDuree());
        holder.atv.setText(abonnement.getDestination());

    }



    @Override
    public int getItemCount() {
        return abonnementsList.size();
    }

}