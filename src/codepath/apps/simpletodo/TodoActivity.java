package codepath.apps.simpletodo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import codepath.apps.simpletodo.utils.FileUtilities;

public class TodoActivity extends Activity {

	List<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	private static final String FILENAME = "todo.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		lvItems = (ListView) findViewById(R.id.lvItems);
		items = FileUtilities.readItems(getApplicationContext(), FILENAME);
		itemsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);
		setUpListViewListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

	public void addTodoItem(View v) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		String toDoItem = etNewItem.getText().toString();
		//Restrict adding empty todo items
		if(!(toDoItem == null || toDoItem.isEmpty())){
			itemsAdapter.add(etNewItem.getText().toString());
			etNewItem.setText("");
			FileUtilities.saveItems(getApplicationContext(), FILENAME, items);
		}else{
			Toast.makeText(this, "TO DO items must contain text", Toast.LENGTH_SHORT).show();
		}
	}

	private void setUpListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> aView, View item,
					int pos, long id) {
				items.remove(pos);
				itemsAdapter.notifyDataSetInvalidated();
				FileUtilities.saveItems(getApplicationContext(), FILENAME, items);
				return true;
			}
		});
	}
}
