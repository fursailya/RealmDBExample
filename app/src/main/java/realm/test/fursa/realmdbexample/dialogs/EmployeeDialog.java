package realm.test.fursa.realmdbexample.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import realm.test.fursa.realmdbexample.R;
import realm.test.fursa.realmdbexample.models.EmployeeObject;

public class EmployeeDialog extends DialogFragment {
    private View view;
    private LayoutInflater inflater;
    private Realm mRealm;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.employee_dialog, null);
        final EditText name = (EditText) view.findViewById(R.id.name);
        final EditText surname = (EditText) view.findViewById(R.id.surname);
        final EditText phone = (EditText) view.findViewById(R.id.phone);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.new_label);
        dialog.setView(view);
        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mRealm = Realm.getDefaultInstance();
                mRealm.beginTransaction();
                EmployeeObject employeeObject = mRealm.createObject(EmployeeObject.class);
                employeeObject.setName(name.getText().toString());
                employeeObject.setSurname(surname.getText().toString());
                employeeObject.setPhone(Integer.parseInt(String.valueOf(phone.getText())));
                mRealm.commitTransaction();
            }
        });
        return dialog.create();
    }


}
