package css.cis3334.heartratetracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;

public class HeartRateAdapter extends ArrayAdapter<HeartRate> {

    private final Context context;      // The activity calling this adapter
    private HeartRateList hrList;       // The object holding the arraylist of hear rates

     // @param mainActivity
     // @param heart_rate_row
     // @param textViewPulse
     // @param heartRateList
     // @param context

    public HeartRateAdapter(Context context, int rowLayout, int dummyTV, HeartRateList hrList) {
        super(context, rowLayout, dummyTV, hrList.getList());
        this.context = context;
        this.hrList = hrList;
    }


    /**
     * This is called automatically to display each item in the list.
     *    Here you must fill the layout for one row or item in the list
     *
     * @param position index in the list that is being selected
     * @param convertView
     * @param parent The parent layout this list is in
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.heart_rate_row, null);
        //get the heart rate we are displaying
        HeartRate hr = hrList.getHeartRate(position);

        TextView tvPulse=(TextView)view.findViewById(R.id.textViewPulse);
        tvPulse.setText(hr.getPulse().toString());
       // get the range  to display
        TextView tvRange = (TextView) view.findViewById(R.id.textViewRangeName);
        tvRange.setText(hr.getRangeName().toString());
        //get description to display
        TextView tvDescription = (TextView) view.findViewById(R.id.textViewRangeDescription);
        tvDescription.setText("\"" + hr.getRangeDescrtiption().toString() +"\"");

        // get range selected
        // change textViewRangeName color based on range selected
        Integer range = hr.getRange();

        if (range == 1) { // Moderate
            tvRange.setTextColor(ContextCompat.getColor(context, R.color.colorZone1));
        }else if (range == 2) { // Endurance
            tvRange.setTextColor(ContextCompat.getColor(context, R.color.colorZone2));
        }else if (range == 3) { // Aerobic
            tvRange.setTextColor(ContextCompat.getColor(context, R.color.colorZone3));
        }else if (range == 4) { // Anaerobic
            tvRange.setTextColor(ContextCompat.getColor(context, R.color.colorZone4));
        }else if (range == 5) { // Red Zone
            tvRange.setTextColor(ContextCompat.getColor(context, R.color.colorZone5));
        }else { // Resting
            tvRange.setTextColor(ContextCompat.getColor(context, R.color.colorZone0));
        }

        return(view);
    }
}
