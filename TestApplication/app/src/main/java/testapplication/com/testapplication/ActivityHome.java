package testapplication.com.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.adapter.AdapterStudent;
import com.model.ModelTemp;
import com.servercall.servercall.HttpVolleyRequest;
import com.servercall.servercall.MyListener;

import java.util.ArrayList;


public class ActivityHome extends AppCompatActivity {
    ArrayList<ModelTemp> listRecords;
    Toolbar mToolbar;
    RecyclerView rView;
    AdapterStudent mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Log.e("Rex", "Home");
        new HttpVolleyRequest(ActivityHome.this, listener);
        setDataOnViews();
    }

    private void setDataOnViews() {
        listRecords = new ArrayList<>();
        ModelTemp record1=new ModelTemp();
        record1.setFname("Rex 1");
        record1.setLname("Suryawanshi 1");
        record1.setRollno("101");
        listRecords.add(record1);

        ModelTemp record2=new ModelTemp();
        record2.setFname("Rex 2");
        record2.setLname("Suryawanshi 2");
        record2.setRollno("102");
        listRecords.add(record2);

        ModelTemp record3=new ModelTemp();
        record3.setFname("Rex 3");
        record3.setLname("Suryawanshi 3");
        record3.setRollno("103");
        listRecords.add(record3);

        LinearLayoutManager manager=new LinearLayoutManager(ActivityHome.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rView.setLayoutManager(manager);
        mAdapter=new AdapterStudent(ActivityHome.this,listRecords);
        rView.setAdapter(mAdapter);
    }

    MyListener listener = new MyListener() {
        @Override
        public void success(Object obj) {
            Log.e("Rex",((String)obj) );

        }

        @Override
        public void failure(Object obj) {
            Log.e("Rex",((String)obj) );
        }
    };

    public void init() {
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Rex app");
        rView = (RecyclerView) findViewById(R.id.rView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
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
