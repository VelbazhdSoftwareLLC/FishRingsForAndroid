package eu.veldsoft.fish.rings;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InstructionActivity extends Activity {

	static final String EXTRA_RESULT_FORMULA_KEY = "eu.veldsoft.fish.rings.resultFormulaKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instruction);

		((Button) findViewById(R.id.formula_entered)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String formula = ((EditText) findViewById(R.id.formula)).getText().toString();
				setResult(Activity.RESULT_OK, (new Intent().putExtra(EXTRA_RESULT_FORMULA_KEY, formula)));

				formula = formula.toUpperCase();

				if (Pattern.compile("([+-]{1}[0-9]{1}[ABC]{1})+").matcher(formula).matches() == true) {
					finish();
				} else {
					Toast.makeText(InstructionActivity.this,
							getResources().getString(R.string.incorrect_formula_warning), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
