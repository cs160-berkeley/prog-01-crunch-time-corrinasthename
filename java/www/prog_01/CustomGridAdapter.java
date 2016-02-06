package www.prog_01;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



/**
 * Created by corrinasthename on 2/4/16.
 */
public class CustomGridAdapter extends BaseAdapter {


    Context context;
    String [] items;
    final Map<String, Double> calories = new HashMap<String, Double>();
    Double[] calorie_values = {350.0, 200.0, 225.0, 25.0, 25.0, 10.0, 100.0, 12.0, 20.0, 12.0, 13.0, 15.0};
//    Double[] formula_values = {350/100.0, 200/100.0, 100.0/225, 100.0/25, 100.0/25, 100.0/10, 100.0/100, 100.0/12, 100.0/20, 100.0/12, 100.0/13, 100.0/15};

    final Map<String, Double> conversions = new HashMap<String, Double>();

//    String[] items = {"Push Up", "Sit Up", "Squats"};

    public CustomGridAdapter(Context c, double num_cal) {
        context = c;
        items = context.getResources().getStringArray(R.array.exercise_names);
        for (int i = 0; i < items.length; i++){
            conversions.put(items[i], (num_cal*calorie_values[i])/100.0);
        }
    }

    @Override
    public int getCount() {
        return items.length;

    }

    @Override
    public Object getItem(int position) {
        return ""+items[position]+"\n"+conversions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer[] color_palette = {context.getResources().getColor(R.color.aqua), context.getResources().getColor(R.color.yellow), context.getResources().getColor(R.color.green), context.getResources().getColor(R.color.pink), context.getResources().getColor(R.color.purple)};
        TextView tv = new TextView(context);
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "GeosansLight.ttf");
        tv.setTypeface(myTypeface, Typeface.BOLD);
        tv.setTextSize(25);
        Random rand = new Random();
        int n = rand.nextInt(100);
        int len = color_palette.length;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String end = "";

        String[] reps = {"Push Up", "Sit Up", "Squats", "Pull Up"};
        if (Arrays.asList(reps).contains(items[position])) {
            end = " reps";
        } else {
            end = " minutes";
        }

        tv.setText(String.valueOf("" + items[position] + " : " + df.format(conversions.get(items[position])) + end));
        tv.setTextColor(color_palette[n%len]);

        return tv;
    }
}
