package com.example.tugas8lagi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersAdapterVh> implements Filterable {

    private List<UsersModel> usersModelList;
    private List<UsersModel> getUsersModelListFiltered;
    private Context context;
    private SelectedUser selectedUser;

    public UsersAdapter(List<UsersModel> usersModelList, SelectedUser selectedUser) {
        this.usersModelList = usersModelList;
        this.getUsersModelListFiltered = usersModelList;
        this.selectedUser = selectedUser;
    }
    @NonNull
    @Override
    public UsersAdapter.UsersAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new UsersAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_users, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UsersAdapterVh holder, int position) {

        UsersModel usersModel = usersModelList.get(position);

        String username = usersModel.getUserName();
        int image = usersModel.getImage();

        holder.tvUsername.setText(username);
        holder.imageView.setImageResource(image);

    }

    @Override
    public int getItemCount() {
        return usersModelList.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if (charSequence == null | charSequence.length() == 0){
                    filterResults.count = getUsersModelListFiltered.size();
                    filterResults.values = getUsersModelListFiltered;
                }else {
                    String searchChr = charSequence.toString().toLowerCase();

                    List<UsersModel> resultData = new ArrayList<>();

                    for (UsersModel usersModel: getUsersModelListFiltered){
                        if (usersModel.getUserName().toLowerCase().contains(searchChr)){
                            resultData.add(usersModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                usersModelList = (List<UsersModel>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public interface SelectedUser{

        void selectedUser(UsersModel usersModel);
    }

    public class UsersAdapterVh extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvUsername;
        ImageView imgIcon;
        public UsersAdapterVh(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            tvUsername = itemView.findViewById(R.id.username);
            imgIcon = itemView.findViewById(R.id.imageIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(usersModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}
