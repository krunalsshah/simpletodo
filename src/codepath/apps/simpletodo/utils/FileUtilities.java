package codepath.apps.simpletodo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import android.content.Context;
import android.util.Log;

public class FileUtilities {

	private static final String TAG = "FileUtilities";

	public static List<String> readItems(Context context, String fileName) {
		File fileDir = context.getFilesDir();
		File toDoFile = new File(fileDir, fileName);
		try {
			return new ArrayList<String>(FileUtils.readLines(toDoFile, "UTF-8"));
		} catch (IOException ioe) {
			return new ArrayList<String>();
		}
	}

	public static void saveItems(Context context, String fileName,
			List<String> items) {
		File fileDir = context.getFilesDir();
		File toDoFile = new File(fileDir, fileName);
		try {
			FileUtils.writeLines(toDoFile, items);
		} catch (IOException ioe) {
			Log.e(TAG, ioe.getMessage());
		}
	}
}
