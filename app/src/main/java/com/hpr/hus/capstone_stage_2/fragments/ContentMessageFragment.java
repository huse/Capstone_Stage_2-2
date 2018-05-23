package com.hpr.hus.capstone_stage_2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.hpr.hus.capstone_stage_2.R;
import com.hpr.hus.capstone_stage_2.messaging.GetSetMessage;
import com.hpr.hus.capstone_stage_2.recycler.RecyclerViewAdapterMessages;

import java.util.ArrayList;

import butterknife.BindView;

public class ContentMessageFragment  extends Fragment implements RecyclerViewAdapterMessages.ItemClickListener,View.OnClickListener{
    public ContentMessageFragment(){

    }

    @BindView(R.id.edit_query)
    EditText editTextQuery;
    @BindView(R.id.send_button)
    ImageButton sendButton;
    ImageButton sendingButton;
    ListView listOfMessages;
    RecyclerViewAdapterMessages recyclerViewAdapterMessages;
    View rootView;
    private FirebaseListAdapter<GetSetMessage> firebaseListAdapter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {


         rootView = layoutInflater.inflate(R.layout.fragment_message_content, viewGroup, false);


        sendingButton = (ImageButton) rootView.findViewById(R.id.send_button);
         listOfMessages = (ListView)rootView.findViewById(R.id.list_of_messages_in_activity);
        sendingButton.setOnClickListener(this);
        LinearLayout linearLayout = rootView.findViewById(R.id.linear_message_list);
        // RecyclerViewAdapter adapter;

        // populate  RecyclerView
        ArrayList<String> placeHolder = new ArrayList<>();
        placeHolder.add("Message 1");
        placeHolder.add("Message 2" +
                "\n" +
                "This is an example that how the content should be in final product.");
        placeHolder.add("Message 3");
        placeHolder.add("Message 4");
        placeHolder.add("Message 5");
        placeHolder.add("Message 6");
        placeHolder.add("Message 7");
        placeHolder.add("Message 8");
        placeHolder.add("Message 9");


        // setting up RecyclerView
        // Log.v("hhh",getView().toString());
        RecyclerView recyclerView = rootView.findViewById(R.id.rvMessageMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapterMessages = new RecyclerViewAdapterMessages(this,getActivity(),placeHolder);
        //adapter.setClickListener(new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(recyclerViewAdapterMessages);

        // setup layoutManager

        LinearLayoutManager  layoutManager = new LinearLayoutManager (getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);


        //Adding divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        displayChatMessages();




        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "You clicked content " + recyclerViewAdapterMessages.getItem(position) + 1+ " on row number " + position, Toast.LENGTH_SHORT).show();

        int i = view.getId();
        if (i == R.id.send_button) {
            Toast.makeText(getActivity(), "You clicked " + "send_button", Toast.LENGTH_SHORT).show();
            Log.v("hhh","send pressed");
            sendMessage();
        } else if (i == R.id.send_button) {
        }

    }




    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.send_button) {
            Toast.makeText(getActivity(), "You clicked " + "send_button", Toast.LENGTH_SHORT).show();
            Log.v("hhh","send pressed");
            sendMessage();
        } else if (i == R.id.send_button) {
        }

    }

    public void sendMessage(){
        EditText input = (EditText)rootView.findViewById(R.id.edit_query);

        // Read the input field and push a new instance
        // of ChatMessage to the Firebase database
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(new GetSetMessage(input.getText().toString(),
                        FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName(),FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getEmail(),FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getPhotoUrl().toString())
                );

        // Clear the input
        input.setText("");
    }

    private void displayChatMessages() {

        firebaseListAdapter = new FirebaseListAdapter<GetSetMessage>(this.getActivity(), GetSetMessage.class,
                R.layout.messageformat, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, GetSetMessage model, int position) {
                // Get references to the views of messageformat.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_content);
                TextView messageUser = (TextView)v.findViewById(R.id.sender_user);
                TextView messageTime = (TextView)v.findViewById(R.id.date_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(firebaseListAdapter);
    }
}
