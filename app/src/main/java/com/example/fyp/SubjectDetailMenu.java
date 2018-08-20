package com.example.fyp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ListView;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.example.fyp.DbHelper.COLUMN_TYPE;


public class SubjectDetailMenu extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ActionBar actionBar;
    Subject subject;
    public static String EXTRA_IMAGE;
    Bitmap myBitmap;
    Uri picUri;
    private ListView listView;
    ImagesAdapter imagesAdapter;
    DbHelper dbHelper;
    byte[] imageData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_subject_detail);

        dbHelper = new DbHelper(getApplicationContext());
        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor)adapterView.getItemAtPosition(position);

                Intent intent = new Intent(SubjectDetailMenu.this, SubjectDetailEditMenu.class);
                EXTRA_IMAGE = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_TYPE));
                SubjectDetailMenu.this.startActivity(intent);
            }
        });

        Intent intent = getIntent();
        long id = intent.getLongExtra(SubjectFragment.EXTRA_ID, 0);

        SubjectDbQueries dbq = new SubjectDbQueries(new DbHelper(getApplicationContext()));

        final String[] columns = {
                SubjectContract.SubjectEntry._ID,
                SubjectContract.SubjectEntry.COLUMN_TITLE,
        };

        String selection = SubjectContract.SubjectEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        Cursor cursor = dbq.query(columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToNext()) {
            subject = new Subject(
                    cursor.getLong(cursor.getColumnIndex(SubjectContract.SubjectEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(SubjectContract.SubjectEntry.COLUMN_TITLE))
            );

            actionBar = getSupportActionBar();
            actionBar.setTitle(subject.getTitle());

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(getPickImageChooserIntent(), 200);
                }
            });
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                COLUMN_TYPE,
        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                ImagesProvider.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        imagesAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        imagesAdapter.swapCursor(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    myBitmap = getResizedBitmap(myBitmap, 1000);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    imageData = baos.toByteArray();
                    dbHelper.addToDb(subject.getTitle(), imageData);
                    finish();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageDbQueries dbq = new ImageDbQueries(new DbHelper(this.getApplicationContext()));
        Cursor cursor = dbq.query(null, "type = \""+subject.getTitle()+"\"", null, null, null, null);
        ImageCursorAdapter adapter = new ImageCursorAdapter(this, cursor, 0);
        listView.setAdapter(adapter);
    }
}
