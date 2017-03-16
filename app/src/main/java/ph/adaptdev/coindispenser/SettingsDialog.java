package ph.adaptdev.coindispenser;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsDialog extends DialogFragment implements View.OnClickListener {

    private TextView save, cancel;
    private EditText editTextApiHost, editTextPort;

    private StateSingleton stateSingleton;
    private DatabaseHelper databaseHelper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateSingleton = StateSingleton.getInstance();
        databaseHelper = new DatabaseHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_settings, null);

        setCancelable(false);

        editTextApiHost = (EditText) view.findViewById(R.id.editTextApiHost);
        editTextPort = (EditText) view.findViewById(R.id.editTextPort);
        save = (TextView) view.findViewById(R.id.save);
        cancel = (TextView) view.findViewById(R.id.cancel);

        editTextApiHost.setText(stateSingleton.getApiHost());
        editTextPort.setText(stateSingleton.getPort());

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.save:
                String apiHost = editTextApiHost.getText().toString();
                String port = editTextPort.getText().toString();
                if (apiHost.length() <= 0) {
                    Toast.makeText(getActivity(), "Enter Api Host.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (port.length() <= 0) {
                    Toast.makeText(getActivity(), "Enter Port.", Toast.LENGTH_LONG).show();
                    break;
                }
                Toast.makeText(getActivity(), "apiHost: " + apiHost + ", port: " + port,
                        Toast.LENGTH_LONG).show();
                databaseHelper.updateApiHost(apiHost);
                stateSingleton.setApiHost(databaseHelper.readApiHost());
                databaseHelper.updatePort(port);
                stateSingleton.setPort(databaseHelper.readPort());
                dismiss();
                break;

        }
    }
}
