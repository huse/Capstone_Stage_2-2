package com.hpr.hus.capstone_stage_2.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.hpr.hus.capstone_stage_2.R;
import com.hpr.hus.capstone_stage_2.messaging.GetSetMessage;
import com.hpr.hus.capstone_stage_2.widgets.WidgetIntentService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "hhh MessageActivity";
    private ImageButton sendingButton;
    private ListView listOfMessages;
    private ImageView userImage;
    private FirebaseUser mFirebaseRef;
    private ArrayList<String> messagesForWidgets;
    int counter = 0;
    int counter2 = 0;
    private String userEmail = "Welcome    ";
    private FirebaseListAdapter<GetSetMessage> firebaseListAdapter;

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.v("src", src);
            URL url = new URL(src);
            Log.v("url", url.toString());

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            Log.v("connection", connection.toString());

            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            Log.v(TAG, "LoadImageFromWebOperations: " + url);

            InputStream is = (InputStream) new URL(url).getContent();
            Log.v(TAG, "InputStream" + is);

            Drawable d = Drawable.createFromStream(is, "src name");
            Log.v(TAG, "Drawable" + d);


            return d;
        } catch (Exception e) {
            Log.e(TAG, "Drawable failed  " + e.getMessage());

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendingButton = findViewById(R.id.send_button);
        listOfMessages =  findViewById(R.id.list_of_messages_in_activity);
        sendingButton.setOnClickListener(this);
        mFirebaseRef = FirebaseAuth.getInstance().getCurrentUser();
        if(mFirebaseRef!=null)
        userEmail = userEmail + mFirebaseRef.getEmail();
        messagesForWidgets = new ArrayList<>();

        displayListOfMessages();


        //   WidgetIntentService.startWidget(MessageActivity.this,messagesForWidgets);
        listOfMessages.setAdapter(firebaseListAdapter);
        //listOfMessages.setSelection(listOfMessages.getAdapter().getCount()-1);
        // Log.v(TAG,"getCount   " + listOfMessages.getAdapter().getCount() );
        // listOfMessages.setItemChecked(20, true);

        // listOfMessages.scrollTo(10,10);

        /* listOfMessages. requestFocusFromTouch();
              //  listOfMessages.clearFocus();
                listOfMessages.post(new Runnable() {
                    @Override
                    public void run() {
                        listOfMessages.setSelection(listOfMessages.getAdapter().getCount()-1);
                    }
                });*/

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.send_button) {
            Toast.makeText(this, "You clicked " + "send_button", Toast.LENGTH_SHORT).show();
            Log.v("hhh", "send pressed");
            sendMessage();
        } /*else if (i == R.id.send_button) {
        }*/

    }

    private  void sendMessage() {
        EditText input =  findViewById(R.id.edit_query);

        // Read the input field and push a new instance
        // of ChatMessage to the Firebase database
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(new GetSetMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()
                        //   ,FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString())
                ));


        // Clear the input
        input.setText("");
    }

    private ArrayList<String> getMessagesForWidgets(ArrayList<String> list) {

        return list;
    }

    private ArrayList<String> displayListOfMessages() {

        firebaseListAdapter = new FirebaseListAdapter<GetSetMessage>(this, GetSetMessage.class,
                R.layout.messageformat, FirebaseDatabase.getInstance().getReference()) {

            @Override
            protected void populateView(View v, GetSetMessage model, int position) {
                TextView messageContent = v.findViewById(R.id.message_content);
                TextView senderUser = v.findViewById(R.id.sender_user);
                TextView dsteTime = v.findViewById(R.id.date_time);


                userImage = v.findViewById(R.id.user_profile_image);
                // Log.v(TAG, "USERIMAGE"  +userImage.toString());

                // Set their text
                messageContent.setText(model.getMessageText());
                senderUser.setText(model.getEmailUser());
                dsteTime.setText(DateFormat.format("HH:mm:ss    \nMM-dd-yy ",
                        model.getMessageTime()));

//listOfMessages

                // messagesForWidgets= new ArrayList<>();
                messagesForWidgets.add(model.getMessageText() + "\n" +
                        "User: " + model.getEmailUser() + "\n" +
                        "Time: " + DateFormat.format("HH:mm:ss MM-dd-yy ",
                        model.getMessageTime()) + "\n");


                WidgetIntentService.startWidget(MessageActivity.this, messagesForWidgets);

                /*if (model.getUriPhoto() != null && userImage!=null ) {
                   // userImage.setImageBitmap(getBitmapFromURL(model.getUriPhoto().toString()));
                    //Log.v(TAG,"draw"+LoadImageFromWebOperations(model.getUriPhoto().toString()).toString());
                    userImage.setImageDrawable(LoadImageFromWebOperations(model.getUriPhoto().toString()));


                }*/


                // listOfMessages.setAdapter(firebaseListAdapter);
                //listOfMessages.setSelection(listOfMessages.getAdapter().getCount()-1);
                //  Log.v(TAG,"getCount   " + listOfMessages.getAdapter().getCount() );
                // listOfMessages.setItemChecked(listOfMessages.getAdapter().getCount()-1, false);

                //listOfMessages.scrollTo(listOfMessages.getAdapter().getCount()-1,listOfMessages.getAdapter().getCount()-1);

               /* listOfMessages. requestFocusFromTouch();
              //  listOfMessages.clearFocus();
                listOfMessages.post(new Runnable() {
                    @Override
                    public void run() {
                        listOfMessages.setSelection(listOfMessages.getAdapter().getCount()-1);
                    }
                });*/
            }
        };

        Log.v(TAG, "End of Method");

        return messagesForWidgets;
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       *//* if (id == R.id.action_settings) {
            Log.d(TAG, "User profile updated.");
            updateProfile("userUriString");
            Log.v("jjj", "runnignIntentActivity");
            final Intent intent = new Intent(this, ProfileActivity.class);
           // intent.putExtras(selecteUserBundle);
            startActivity(intent);
            return true;
        }*//*
     *//* if (id == R.id.action_sign_out) {
     *//**//*LoginActivity3 loginActivity3 =new LoginActivity3();
            loginActivity3.signOut();*//**//*
            return true;
        }*//*
        return super.onOptionsItemSelected(item);
    }*/

    public void updateProfile(String userUriString) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Husi")
                .setPhotoUri(Uri.parse("https://s31.postimg.cc/grwg5hrhn/person_icon_-_Copy.png"))
                .build();
        Log.d(TAG, "User profile built.");
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
        Log.d(TAG, "User displayname:   " + user.getDisplayName());
        //  Log.d(TAG, "User PhotoUrl:   " + user.getPhotoUrl());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_menu, menu);
        menu.findItem(R.id.welcome).setTitle(userEmail);

        return true;
    }
}

