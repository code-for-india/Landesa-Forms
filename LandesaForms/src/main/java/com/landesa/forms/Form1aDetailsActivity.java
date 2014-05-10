package com.landesa.forms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.landesa.forms.model.Form1aDetails;

public class Form1aDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form1a_details_layout);

        Spinner spinner = (Spinner) findViewById(R.id.onea_sex);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.onea_caste);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.caste_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ((EditText) findViewById(R.id.onea_widow)).setText("0");
        ((EditText) findViewById(R.id.onea_abandoned)).setText("0");
        ((EditText) findViewById(R.id.onea_divorcee)).setText("0");
        ((EditText) findViewById(R.id.onea_uw)).setText("0");
        ((EditText) findViewById(R.id.onea_disabled)).setText("0");

    }

    public void onSubmit(View view) {
        Intent returnIntent = new Intent();
        EditText textView = (EditText) findViewById(R.id.onea_name_of_household_head);
        returnIntent.putExtra(Form1aDetails.NAME_OF_HOUSEHOLD_KEY, textView.getText().toString());
        textView = (EditText) findViewById(R.id.onea_name_of_wife);
        returnIntent.putExtra(Form1aDetails.NAME_OF_WIFE_KEY, textView.getText().toString());
        textView = (EditText) findViewById(R.id.onea_name_of_father_husband);
        returnIntent.putExtra(Form1aDetails.NAME_OF_FATHER_OR_HUSBAND_KEY, textView.getText().toString());
        Spinner spinner = (Spinner) findViewById(R.id.onea_sex);
        String val = "";
        if (spinner.getSelectedItem() != null) {
            val = spinner.getSelectedItem().toString();
        }
        returnIntent.putExtra(Form1aDetails.SEX_KEY, val);
        spinner = (Spinner) findViewById(R.id.onea_caste);
        val = "";
        if (spinner.getSelectedItem() != null) {
            val = spinner.getSelectedItem().toString();
        }
        returnIntent.putExtra(Form1aDetails.CASTE_KEY, val);
        textView = (EditText) findViewById(R.id.onea_widow);
        val = "0";
        if (!TextUtils.isEmpty(textView.getText())) {
            val = textView.getText().toString();
        }
        returnIntent.putExtra(Form1aDetails.WIDOW_KEY, Integer.parseInt(val));
        textView = (EditText) findViewById(R.id.onea_abandoned);
        val = "0";
        if (!TextUtils.isEmpty(textView.getText())) {
            val = textView.getText().toString();
        }
        returnIntent.putExtra(Form1aDetails.ABANDONED_KEY, Integer.parseInt(val));
        textView = (EditText) findViewById(R.id.onea_divorcee);
        val = "0";
        if (!TextUtils.isEmpty(textView.getText())) {
            val = textView.getText().toString();
        }
        returnIntent.putExtra(Form1aDetails.DIVORCEE_KEY, Integer.parseInt(val));
        textView = (EditText) findViewById(R.id.onea_uw);
        val = "0";
        if (!TextUtils.isEmpty(textView.getText())) {
            val = textView.getText().toString();
        }
        returnIntent.putExtra(Form1aDetails.UW_KEY, Integer.parseInt(val));
        textView = (EditText) findViewById(R.id.onea_disabled);
        val = "0";
        if (!TextUtils.isEmpty(textView.getText())) {
            val = textView.getText().toString();
        }
        returnIntent.putExtra(Form1aDetails.DISABLE_KEY, Integer.parseInt(val));

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
