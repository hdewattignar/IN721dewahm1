package bit.dewahm1.photomosiac;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String mPhotoFileName;
    File mPhotoFile;
    Uri mPhotoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_photo = (Button)findViewById(R.id.btn_takePhoto);
        TakePhotoButtonHandler takePhotoButtonHandler = new TakePhotoButtonHandler();
        btn_photo.setOnClickListener(takePhotoButtonHandler);

    }

    public class TakePhotoButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            takePhoto();
        }
    }

    public File getFile()
    {
        //Fetch file image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //makesubdirectory
        File imageStorageDirectory = new File(imageRootPath, "PhotoMosaic");
        if(!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //make file name
        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);

        return photoFile;
    }

    public void takePhoto()
    {
        //make the file
        mPhotoFile = getFile();

        //make the URI for the file
        mPhotoURI = Uri.fromFile(mPhotoFile);

        //intent
        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //attach uri to intent
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);

        startActivityForResult(imageCaptureIntent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String realFilePath = mPhotoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);
                displayImages(userPhotoBitmap);
            }
            else
            {
                Toast.makeText(this,"didnt work :(",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void displayImages(Bitmap photo)
    {
        //get the image views
        ImageView imageView1 = (ImageView)findViewById(R.id.imageView_1);
        ImageView imageView2 = (ImageView)findViewById(R.id.imageView_2);
        ImageView imageView3 = (ImageView)findViewById(R.id.imageView_3);
        ImageView imageView4 = (ImageView)findViewById(R.id.imageView_4);

        imageView1.setImageBitmap(photo);
        imageView2.setImageBitmap(photo);
        imageView3.setImageBitmap(photo);
        imageView4.setImageBitmap(photo);
    }

}
