package css.cis3334.heartratetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    HeartRateList heartRateList;        // The list of heart rate objects
    ArrayAdapter<HeartRate> hrAdapter;  // The custom array adapter for displaying the heart rates in the list view
    ListView lvHeartRates;
    TextView tvSelect;
    public  static final String HEARTRATE_KEY ="HEARTRATE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvHeartRates = (ListView)  findViewById(R.id.listViewHeartRate);

        tvSelect = (TextView) findViewById(R.id.textViewSelect);

        heartRateList = new HeartRateList();
        heartRateList.InitRandomElderly();
        hrAdapter = new HeartRateAdapter(this, R.layout.heart_rate_row, R.id.textViewPulse, heartRateList);
        hrAdapter.setDropDownViewResource(R.layout.heart_rate_row);
        lvHeartRates.setAdapter(hrAdapter);

        lvHeartRates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            HeartRate hr = (HeartRate) parent.getItemAtPosition(position);
            tvSelect.setText("You selected: " + hr.toString()+ "\n" + "Description: " + hr.getRangeDescrtiption());
            Intent detailActIntent = new Intent(parent.getContext(), HeartDetailActivity.class);
            detailActIntent.putExtra(HEARTRATE_KEY, hr);
            startActivity(detailActIntent);
        }
    });
    }
}
