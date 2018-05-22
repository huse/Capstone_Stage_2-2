package com.hpr.hus.capstone_stage_2.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "hhh MessageActivity";
    ImageButton sendingButton;
    ListView listOfMessages;
    ImageView userImage;
    private String userEmail = "Welcome    ";
    FirebaseUser mFirebaseRef;


    private FirebaseListAdapter<GetSetMessage> firebaseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendingButton = (ImageButton) findViewById(R.id.send_button);
        listOfMessages = (ListView) findViewById(R.id.list_of_messages_in_activity);
        userImage = findViewById(R.id.action_image);
        sendingButton.setOnClickListener(this);
        mFirebaseRef = FirebaseAuth.getInstance().getCurrentUser();

        userEmail = userEmail + mFirebaseRef.getEmail();

        displayChatMessages();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.send_button) {
            Toast.makeText(this, "You clicked " + "send_button", Toast.LENGTH_SHORT).show();
            Log.v("hhh", "send pressed");
            sendMessage();
        } else if (i == R.id.send_button) {
        }

    }

    public void sendMessage() {
        EditText input = (EditText) findViewById(R.id.edit_query);

        // Read the input field and push a new instance
        // of ChatMessage to the Firebase database
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                /*.setValue(new ChatMessage("hi",
                        "me",
                        "h@h.com",
                        FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                );*/
                .setValue(new GetSetMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                        FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                );

        // Clear the input
        input.setText("");
    }


    private void displayChatMessages() {

        firebaseListAdapter = new FirebaseListAdapter<GetSetMessage>(this, GetSetMessage.class,
                R.layout.messageformat, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, GetSetMessage model, int position) {
                TextView messageContent =  v.findViewById(R.id.message_content);
                TextView senderUser =  v.findViewById(R.id.sender_user);
                TextView dsteTime =  v.findViewById(R.id.date_time);

                // Set their text
                messageContent.setText(model.getMessageText());
                senderUser.setText(model.getEmailUser());
                dsteTime.setText(DateFormat.format("HH:mm:ss    \nMM-dd-yy ",
                        model.getMessageTime()));
                if (model.getUriPhoto() != null) {
                    userImage.setImageBitmap(getBitmapFromURL(model.getUriPhoto().toString()));
                }
            }
        };

        listOfMessages.setAdapter(firebaseListAdapter);
        //listOfMessages.setSelection(listOfMessages.getAdapter().getCount()-1);
        listOfMessages.scrollTo(0,listOfMessages.getAdapter().getCount()-1);

    }

    public void updateProfile(String userUriString) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Husi")
                .setPhotoUri(Uri.parse("https://ibb.co/ew4uO8"))
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
        Log.d(TAG, "User PhotoUrl:   " + user.getPhotoUrl());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.welcome).setTitle(userEmail);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d(TAG, "User profile updated.");
            updateProfile("userUriString");

            return true;
        }
        if (id == R.id.action_sign_out) {
            /*LoginActivity3 loginActivity3 =new LoginActivity3();
            loginActivity3.signOut();*/
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
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
}

