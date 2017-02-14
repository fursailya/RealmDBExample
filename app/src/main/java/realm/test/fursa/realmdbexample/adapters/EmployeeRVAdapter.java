package realm.test.fursa.realmdbexample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import realm.test.fursa.realmdbexample.R;
import realm.test.fursa.realmdbexample.models.EmployeeObject;

/**
 * Created by Fursa Ilya on 14.02.2017.
 * @author Fursa Ilya
 * Simple RecyclerView adapter for work with Realm database!
 */

public class EmployeeRVAdapter extends RecyclerView.Adapter<EmployeeRVAdapter.EmployeeViewHolder> implements RealmChangeListener {
    public RealmResults<EmployeeObject> mEmployees;

    public EmployeeRVAdapter(RealmResults<EmployeeObject> employee) {
        this.mEmployees = employee;
        mEmployees.addChangeListener(this);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        EmployeeObject employee = mEmployees.get(position);
        holder.name.setText(employee.getName());
        holder.surname.setText(employee.getSurname());
        holder.phone.setText(String.valueOf(employee.getPhone()));
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname, phone;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            surname = (TextView) itemView.findViewById(R.id.surname);
            phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }

    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }
}
