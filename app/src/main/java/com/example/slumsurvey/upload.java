package com.example.slumsurvey;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.nio.file.FileVisitOption;

public class upload extends AppCompatActivity {

Button upload;
ImageView img;
    File file;
    Uri fileUri;
    Uri imageUri=null;
    File imageFolder=null;
    Uri myuri;
private static final int CAMERA_REQUEST_COSE=1;
private StorageReference mStorage;
Uri uri;
private ProgressDialog mprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        upload=findViewById(R.id.uploadbutton);
        img= findViewById(R.id.imageView11);
        mStorage= FirebaseStorage.getInstance().getReference();
        mprogress= new ProgressDialog(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values=new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"s");
                imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
                 Intent c=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 c.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                 startActivityForResult(c,CAMERA_REQUEST_COSE);

                }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST_COSE&& resultCode==RESULT_OK )
        {

            mprogress.setMessage("uploading image");
//            @SuppressWarnings("VisibleForTests") Uri uri=data.getData();
          StorageReference filepath=mStorage.child("photos").child(imageUri.getLastPathSegment().toString());
            //Toast.makeText(this, imageUri.toString(), Toast.LENGTH_SHORT).show();

            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mprogress.dismiss();

                    Toast.makeText(upload.this, imageUri.getLastPathSegment().toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

}
