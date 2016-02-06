package www.prog_01;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CrunchTime extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    public static int [] exerciseImages = {};

    private Switch switch1;

    private Button button1, back_button;
    private TextView textView6, calResult;
    private String selected_exercise;

    private Button button2, button3;
    private TextView select_exercise, textView4, switchStatus;
    private Boolean option_cal = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crunch_time);

        //for the formula
        final Map<String, Double> calories = new HashMap<String, Double>();
        final String[] exercise_types = getResources().getStringArray(R.array.exercise_names);
        final Double[] calorie_values = {100.0/350, 100.0/200, 100.0/225, 100.0/25, 100.0/25, 100.0/10, 100.0/100, 100.0/12, 100.0/20, 100.0/12, 100.0/13, 100.0/15};
        for (int i = 0; i < exercise_types.length; i++){
            calories.put(exercise_types[i], calorie_values[i]);
        }


        final Typeface myTypeface = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
        TextView myTextView = (TextView) findViewById(R.id.textView);
        final TextView myTextView2 = (TextView) findViewById(R.id.textView4); //How much?
        final TextView myTextView4 = (TextView) findViewById(R.id.textView6);

        final GridView gv = (GridView) findViewById(R.id.gridView);

        TextView question = (TextView) findViewById(R.id.select_exercise);
        final TextView reps = (TextView) findViewById(R.id.switchStatus); //reps
        textView6 = (TextView) findViewById(R.id.textView6);
        calResult = (TextView) findViewById(R.id.calResult);
        final EditText editText = (EditText) findViewById(R.id.editText);


        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.exercise_names, R.layout.spinner_layout);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(spinnerAdapter);

        TextView[] change_text = {myTextView, myTextView2, myTextView4, textView6, question, reps};
        for (int i = 0; i < change_text.length; i++){
            change_text[i].setTypeface(myTypeface);
        }

        button1 = (Button) findViewById(R.id.button1);
        final Random rand = new Random();

        back_button = (Button) findViewById(R.id.back_button);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option_cal = false;
                TextView myTextView5 = (TextView) findViewById(R.id.select_exercise); //What exercise?
                myTextView5.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                myTextView2.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                reps.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                back_button.setVisibility(View.VISIBLE);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option_cal = true;
                TextView cal_question = (TextView) findViewById(R.id.cal_question); //How many calories?
                cal_question.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                TextView cals = (TextView) findViewById(R.id.cals);
                cals.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                back_button.setVisibility(View.VISIBLE);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setVisibility(View.INVISIBLE);

                TextView cal_question = (TextView) findViewById(R.id.cal_question); //How many calories?
                cal_question.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                TextView cals = (TextView) findViewById(R.id.cals);
                cals.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                back_button.setVisibility(View.GONE);

                TextView myTextView5 = (TextView) findViewById(R.id.select_exercise); //What exercise?
                myTextView5.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.INVISIBLE);
                myTextView2.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                reps.setVisibility(View.INVISIBLE);
                textView6.setVisibility(View.INVISIBLE);
                gv.setVisibility(View.INVISIBLE);
                calResult.setVisibility(View.INVISIBLE);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //put what the button should do here
                TextView inputText = (TextView) findViewById(R.id.editText);
                final int input = Integer.parseInt(inputText.getText().toString());


                gv.setVisibility(View.VISIBLE);
                calResult.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.VISIBLE);

                final int n = rand.nextInt(100);
                String[] motivations = getResources().getStringArray(R.array.motivations);
                int len = motivations.length;
                textView6.setText(motivations[n % len]);
                double calBurned = 0;
                if (option_cal == true){
                    calBurned = input;
                } else {
                    calBurned = calories.get(selected_exercise) * input;
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    calResult.setText("You burned " + df.format(calBurned) + " calories!");
                    calResult.setTypeface(myTypeface);
                }


//                GridView gv = (GridView) findViewById(R.id.gridView);
                gv.setAdapter(new CustomGridAdapter(CrunchTime.this, calBurned));

            }
        });



            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                                              {
                                                  @Override
                                                  public void onItemSelected(AdapterView<?> parent, View view, int position,
                                                                             long id) {
//                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show()
                                                      //what do you want to do when they select a certain exercise
                                                      selected_exercise = (String) parent.getItemAtPosition(position);
                                                      TextView descr = (TextView) findViewById(R.id.switchStatus);
                                                      descr = (TextView) findViewById(R.id.switchStatus);

                                                      String[] reps = {"Push Up", "Sit Up", "Squats", "Pull Up"};
                                                      if (Arrays.asList(reps).contains(selected_exercise)) {
                                                          descr.setText("reps");
                                                      } else {
                                                          descr.setText("minutes");
                                                      }

                                                  }

                                                  @Override
                                                  public void onNothingSelected(AdapterView<?> parent) {
                                                      TextView descr = (TextView) findViewById(R.id.switchStatus);
                                                      descr = (TextView) findViewById(R.id.switchStatus);
                                                      descr.setText("reps");

                                                  }
                                              }

            );
        }
    }




