package com.landesa.forms;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.landesa.forms.model.Form1a;
import com.landesa.forms.model.Form1aDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Form1aActivity extends ActionBarActivity {
    private static final String TAG = "Form1aActivity";
    public static final int ADD_DETAIL_REQUEST = 0;

    Map<View, Form1aDetails> mForm1aDetailsMap = new HashMap<View, Form1aDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form1a_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.form1a, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddDetail(View view) {
        startActivityForResult(new Intent(this, Form1aDetailsActivity.class), ADD_DETAIL_REQUEST);
    }

    public void onSubmit(View view) {
        Form1a form = new Form1a();
        form.village = ((EditText) findViewById(R.id.onea_village)).getText().toString();
        form.revenueCircle = ((EditText) findViewById(R.id.onea_revenue_circle)).getText().toString();
        form.tahasil = ((EditText) findViewById(R.id.onea_tahasil)).getText().toString();
        form.sourceOfData = ((EditText) findViewById(R.id.onea_source_of_data)).getText().toString();
        form.detailsList = new ArrayList<Form1aDetails>();
        // TODO: Get the date.
        for (View v : mForm1aDetailsMap.keySet()) {
            form.detailsList.add(mForm1aDetailsMap.get(v));
        }
        String json = new GsonBuilder().create().toJson(form);
        // Append to a file and clear the data.
        addToFile(json);
        TextView statusMsg = (TextView) findViewById(R.id.onea_status_message);
        statusMsg.setText(R.string.status_success_form1a);
        statusMsg.setTextColor(Color.RED);
        clearData();
    }

    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == ADD_DETAIL_REQUEST) {
            if (resultCode == RESULT_OK) {
                final TableLayout tableLayout = (TableLayout) findViewById(R.id.onea_details_table);
                final TableRow row = new TableRow(this);
                mForm1aDetailsMap.put(row, new Form1aDetails(
                        data.getStringExtra(Form1aDetails.NAME_OF_HOUSEHOLD_KEY),
                        data.getStringExtra(Form1aDetails.NAME_OF_WIFE_KEY),
                        data.getStringExtra(Form1aDetails.NAME_OF_FATHER_OR_HUSBAND_KEY),
                        data.getStringExtra(Form1aDetails.SEX_KEY),
                        data.getStringExtra(Form1aDetails.CASTE_KEY),
                        data.getIntExtra(Form1aDetails.WIDOW_KEY, 0),
                        data.getIntExtra(Form1aDetails.ABANDONED_KEY, 0),
                        data.getIntExtra(Form1aDetails.DIVORCEE_KEY, 0),
                        data.getIntExtra(Form1aDetails.UW_KEY, 0),
                        data.getIntExtra(Form1aDetails.DISABLE_KEY, 0)));
                TextView textView = new TextView(this);
                textView.setText("Added " + data.getStringExtra(Form1aDetails.NAME_OF_HOUSEHOLD_KEY));
                row.addView(textView);
                Button button = new Button(this);
                button.setText("Remove");
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tableLayout.removeView(row);
                        mForm1aDetailsMap.remove(row);
                    }
                });
                row.addView(button);
                tableLayout.addView(row);
            }
        }
    }

    private void addToFile(String json) {
        try {
            FileOutputStream fo = openFileOutput("form1.json", MODE_APPEND);
            PrintStream printStream = new PrintStream(fo);
            printStream.println(json);
            printStream.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "Could not open file.", e);
        }
    }

    private void clearData() {
        mForm1aDetailsMap.clear();
        ((EditText) findViewById(R.id.onea_village)).setText("");
        ((EditText) findViewById(R.id.onea_revenue_circle)).setText("");
        ((EditText) findViewById(R.id.onea_tahasil)).setText("");
        ((EditText) findViewById(R.id.onea_source_of_data)).setText("");
    }
}
