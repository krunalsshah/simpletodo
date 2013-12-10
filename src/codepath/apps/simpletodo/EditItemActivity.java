package codepath.apps.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
	EditText etEditItem;
	int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		String toDoItemOld = getIntent().getStringExtra("todo_item_text");
		pos = getIntent().getIntExtra("todo_item_pos", 0);
		etEditItem = (EditText) findViewById(R.id.etEditItem);
		// Populate Edit Form
		etEditItem.setText(toDoItemOld);
		// cursor in the text field is at the end of the current text value
		etEditItem.setSelection(toDoItemOld.length());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

	public void saveToDoItem(View v) {
		// Send Back Result on Save
		Intent returnToMain = new Intent();
		returnToMain.putExtra("todo_item_update", etEditItem.getText()
				.toString());
		returnToMain.putExtra("todo_item_pos", pos);
		setResult(RESULT_OK, returnToMain);
		// finish
		finish();
	}
}
