package qmb2.cs262.calvin.edu.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Locale;

/*
Quentin Barnes
Class that handles grabbing the values from the inputs and the selected operator and giving an output
 */

public class MainActivity extends AppCompatActivity {

    private EditText val1Text;
    private EditText val2Text;
    private Spinner opSelect;
    private TextView outputCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val1Text = findViewById(R.id.val1_input);
        val2Text = findViewById(R.id.val2_input);
        opSelect = findViewById(R.id.op_select);
        outputCalc = findViewById(R.id.calcOutputText);
    }

    public void performCalculation(View view) {
        String val1 = val1Text.getText().toString();
        String val2 = val2Text.getText().toString();
        String selectedOperator = opSelect.getSelectedItem().toString();
        int val1_num, val2_num;
        double answer = 0.0;

        if (!val1.isEmpty() & !val2.isEmpty()) {
            val1_num = Integer.parseInt(val1);
            val2_num = Integer.parseInt(val2);

            switch (selectedOperator) {
                case "+":
                    answer = val1_num + val2_num;
                    break;
                case "-":
                    answer = val1_num - val2_num;
                    break;
                case "*":
                    answer = val1_num * val2_num;
                    break;
                case "/":
                    answer = (double) val1_num / (double) val2_num;
                    break;
            }

            if (answer % 1 == 0) {
                outputCalc.setText(String.format(Locale.getDefault(), "%.0f", answer));
            } else {
                outputCalc.setText(String.format(Locale.getDefault(), "%.3f", answer));
            }

        } else {
            outputCalc.setText(getString(R.string.calc_error));
        }
        outputCalc.setVisibility(View.VISIBLE);
    }
}
