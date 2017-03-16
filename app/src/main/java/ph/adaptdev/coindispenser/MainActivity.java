package ph.adaptdev.coindispenser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StateSingleton stateSingleton;
    private DatabaseHelper databaseHelper;

    private EditText editText;
    private TextView textView0, textView1, textView2, textView3, textView4, textView5, textView6,
            textView7, textView8, textView9, textViewCA;
    private LinearLayout linearLayoutBackSpace, linearLayoutDispense;

    private ListView listView;

    private TextView textViewSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateSingleton = StateSingleton.getInstance();
        databaseHelper = new DatabaseHelper(this);

        String apiHost = databaseHelper.readApiHost();
        while (apiHost == null) {
            databaseHelper.createApiHost("http://adaptdev.ph");
            apiHost = databaseHelper.readApiHost();
        }
        stateSingleton.setApiHost(apiHost);
        String port = databaseHelper.readPort();
        while (port == null) {
            databaseHelper.createPort("8080");
            port = databaseHelper.readPort();
        }
        stateSingleton.setPort(port);

        listView = (ListView) findViewById(R.id.listView);

        editText = (EditText) findViewById(R.id.editText);
        textView0 = (TextView) findViewById(R.id.textView0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textViewCA = (TextView) findViewById(R.id.textViewCA);
        linearLayoutBackSpace = (LinearLayout) findViewById(R.id.linearLayoutBackSpace);
        linearLayoutDispense = (LinearLayout) findViewById(R.id.linearLayoutDispense);

        textView0.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);
        textViewCA.setOnClickListener(this);
        linearLayoutBackSpace.setOnClickListener(this);
        linearLayoutDispense.setOnClickListener(this);

        editText.setText("");

        textViewSettings = (TextView) findViewById(R.id.textViewSettings);
        textViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsDialog settingsDialog = new SettingsDialog();
                settingsDialog.show(getFragmentManager(), "SettingsDialog");
            }
        });

    }

    @Override
    public void onClick(View v) {

        String text = editText.getText().toString();

        if (text.length() == 1 && text.equalsIgnoreCase("0")) {
            text = "";
        }

        switch (v.getId()) {
            case R.id.textViewCA:
                text = "";
                editText.setText(text);
                return;
            case R.id.linearLayoutBackSpace:
                if (text.length() != 0) {
                    text = text.substring(0, text.length() - 1);
                }
                editText.setText(text);
                return;
            case R.id.linearLayoutDispense:
                String apiHost = stateSingleton.getApiHost();
                String port = stateSingleton.getPort();
                String url = apiHost + ":" + port + "/dispense/" + text;
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("qwerty", "response: " + response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("qwerty", "error: " + error.toString());
                            }
                        }
                );
                VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest);
                Log.e("qwerty", "qwerty");
                return;
        }

        if (text.length() == 3) {
            text = "999";
            editText.setText(text);
            return;
        }

        switch (v.getId()) {
            case R.id.textView0:
                editText.setText(text+"0");
                break;
            case R.id.textView1:
                editText.setText(text+"1");
                break;
            case R.id.textView2:
                editText.setText(text+"2");
                break;
            case R.id.textView3:
                editText.setText(text+"3");
                break;
            case R.id.textView4:
                editText.setText(text+"4");
                break;
            case R.id.textView5:
                editText.setText(text+"5");
                break;
            case R.id.textView6:
                editText.setText(text+"6");
                break;
            case R.id.textView7:
                editText.setText(text+"7");
                break;
            case R.id.textView8:
                editText.setText(text+"8");
                break;
            case R.id.textView9:
                editText.setText(text+"9");
                break;
        }

    }

}
