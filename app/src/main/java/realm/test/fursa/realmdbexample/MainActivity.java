package realm.test.fursa.realmdbexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmResults;
import realm.test.fursa.realmdbexample.adapters.EmployeeRVAdapter;
import realm.test.fursa.realmdbexample.dialogs.EmployeeDialog;
import realm.test.fursa.realmdbexample.models.EmployeeObject;

public class MainActivity extends AppCompatActivity {
    private static final String DIALOG_TAG = "EmployeeDialog";
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showAlertDialog();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        EmployeeRVAdapter adapter = new EmployeeRVAdapter(initEmployees());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
    }

    private RealmResults<EmployeeObject> initEmployees() {
        Realm.init(getBaseContext());
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<EmployeeObject> employees = mRealm.where(EmployeeObject.class).findAll();
        mRealm.commitTransaction();
        return employees;
    }

    private void showAlertDialog() {
        EmployeeDialog dialog = new EmployeeDialog();
        dialog.show(getSupportFragmentManager(), DIALOG_TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
