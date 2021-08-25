package created.by.yomibass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import created.by.yomibass.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.RequestBody;

public class StaffProfileUpdate extends AppCompatActivity {

    LinearLayout select;
    ImageView picture;
    String firstname, lastname, email_ad, employee_no;
    String passport, pic;
    String file_path = null;
    TextView nam, e_mail;
    Button upload;
    ProgressBar progressBar;
    Bitmap bitmap = null;
    File f;
    public static final int GET_FROM_GALLERY = 3;

    String attachmentName = "bitmap";
    String attachmentFileName = "bitmap.bmp";
    String crlf = "\r\n";
    String twoHyphens = "--";
    String boundary =  "*****";

    public static final String STAFF_UPDATE = "https://arrearskdsg.com.ng/mobile/employees_update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profile_update);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        f = new File("mypic");
//        try {
//            f.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        progressBar = findViewById(R.id.progressBar);
        picture = findViewById(R.id.pics);

        Intent i = getIntent();
        firstname = i.getExtras().getString("firstname");
        lastname = i.getExtras().getString("lastname");
        email_ad = i.getExtras().getString("email");
        employee_no = i.getExtras().getString("employee_no");

        nam = findViewById(R.id.name);
        e_mail = findViewById(R.id.dept);

        nam.setText(firstname+" "+lastname);
        e_mail.setText(email_ad);

        upload = findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






//                progressBar.setVisibility(View.VISIBLE);
//                Toast.makeText(getApplicationContext(), "File path = "+file_path, Toast.LENGTH_LONG).show();
//                if (file_path!=null){
//                    UploadFile();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Please select file first", Toast.LENGTH_LONG).show();
//                }

//                StringRequest stringRequest = new StringRequest(Request.Method.POST, STAFF_UPDATE,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                progressBar.setVisibility(View.GONE);
//
//                                System.out.println("Response = "+response);
//
//                                try{
//                                    JSONObject jsonObject = new JSONObject(response);
//                                    passport = jsonObject.getString("passport");
////                                    employee_email = jsonObject.getString("email");
//
//                                    if(!passport.equals("")){
//                                        Toast.makeText(StaffProfileUpdate.this, "Upload successful", Toast.LENGTH_LONG).show();
//
//                                    }else{
//                                        Toast.makeText(StaffProfileUpdate.this, "Upload failed. Please try again", Toast.LENGTH_LONG).show();
//                                    }
//
//                                }
//                                catch (JSONException e){
//                                    e.printStackTrace();
//                                    Toast.makeText(StaffProfileUpdate.this, "Upload Failed.", Toast.LENGTH_LONG).show();
//                                    System.out.println("Error "+e.getMessage());
//                                }
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError volleyError) {
//                                progressBar.setVisibility(View.GONE);
//
//                                Toast.makeText(StaffProfileUpdate.this, "Error! Please try again", Toast.LENGTH_LONG).show();
//                            }
//                        }){
//                    @Override
//                    protected Map<String, String> getParams(){
//                        Map<String, String> params2 = new HashMap<>();
//                        Map<String, File> params = new HashMap<>();
//                        params2.put("uid", employee_no);
//                        params.put("passport", f);
//                        return params2;
//                    }
//                };
//
//                RequestQueue requestQueue = Volley.newRequestQueue(StaffProfileUpdate.this);
//                requestQueue.add(stringRequest);
            }
        });

        select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });
    }

//    private void UploadFile() {
//        UploadTask uploadTask=new UploadTask();
//        uploadTask.execute(new String[]{file_path});
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String filepath = getRealPathFromUri(data.getData(), StaffProfileUpdate.this);



            this.file_path = filepath;
            Log.d("file path : ", " "+filepath);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
//                pic = getStringImage(bitmap);
                picture.setImageBitmap(bitmap);
//
//
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
//                byte[] bitmapdata = bos.toByteArray();
//                FileOutputStream fos = new FileOutputStream(f);
//                fos.write(bitmapdata);
//                fos.flush();
//                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private String getRealPathFromUri(Uri uri, StaffProfileUpdate staffProfileUpdate) {
        Cursor cursor = staffProfileUpdate.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null){
            return uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int id = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(id);
        }
    }

    private class AsyncUploadBitmaps extends AsyncTask<Bitmap, Void, String>{

        @Override
        protected String doInBackground(Bitmap... bitmaps) {

            //setup the request
            HttpURLConnection httpUrlConnection = null;
            URL url = null;
            try {
                url = new URL(STAFF_UPDATE+"/?uid="+employee_no+"passport="+bitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                httpUrlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            try {
                httpUrlConnection.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);

            //start content wrapper
            DataOutputStream request = null;
            try {
                request = new DataOutputStream(
                        httpUrlConnection.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                request.writeBytes(twoHyphens + boundary + crlf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                request.writeBytes("Content-Disposition: form-data; name=\"" +
                        attachmentName + "\";filename=\"" +
                        attachmentFileName + "\"" + crlf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                request.writeBytes(crlf);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert bitmap to bytebuffer
            byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
            for (int i = 0; i < bitmap.getWidth(); ++i) {
                for (int j = 0; j < bitmap.getHeight(); ++j) {
                    //we're interested only in the MSB of the first byte,
                    //since the other 3 bytes are identical for B&W images
                    pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                }
            }

            try {
                request.write(pixels);
            } catch (IOException e) {
                e.printStackTrace();
            }


            //end content wrapper
            try {
                request.writeBytes(crlf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                request.writeBytes(twoHyphens + boundary +
                        twoHyphens + crlf);
            } catch (IOException e) {
                e.printStackTrace();
            }


            //flush output buffer
            try {
                request.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                request.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //get response
            InputStream responseStream = null;
            try {
                responseStream = new
                        BufferedInputStream(httpUrlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader responseStreamReader =
                    new BufferedReader(new InputStreamReader(responseStream));

            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            while (true) {
                try {
                    if (!((line = responseStreamReader.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuilder.append(line).append("\n");
            }
            try {
                responseStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String response = stringBuilder.toString();
            Toast.makeText(getApplicationContext(), "Response = "+response, Toast.LENGTH_LONG).show();


            //close response stream
            try {
                responseStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //close connection
            httpUrlConnection.disconnect();

            return null;
        }
    }


//    public class UploadTask extends AsyncTask<String,String,String> {
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            progressBar.setVisibility(View.GONE);
//            if(s.equalsIgnoreCase("true")){
//                Toast.makeText(StaffProfileUpdate.this, "File uploaded", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(StaffProfileUpdate.this, "Failed Upload", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            if(uploadFile(strings[0])){
//                return "true";
//            }
//            else{
//                return "failed";
//            }
//        }
//
//        private boolean uploadFile(String path){
//            File file=new File(path);
//            try{
//                RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM)
//                        .addFormDataPart("passport",file.getName(),RequestBody.create(MediaType.parse("image/*"),file))
//                        .addFormDataPart("uid", employee_no)
//                        .build();
//
//                okhttp3.Request request=new okhttp3.Request.Builder()
//                        .url(STAFF_UPDATE)
//                        .post(requestBody)
//                        .build();
//
//                OkHttpClient client = new OkHttpClient();
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//                    }
//
//                });
//                return true;
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                return false;
//            }
//        }
//    }

}
