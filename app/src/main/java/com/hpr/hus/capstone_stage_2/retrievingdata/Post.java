package com.hpr.hus.capstone_stage_2.retrievingdata;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 class Post {

    // Get a reference to our posts
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String author;
    public String title;
    DatabaseReference ref = database.getReference("server/saving-data/fireblog/posts");

    public Post(String author, String title) {
        // ...
    }

    /*    // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange (DataSnapshot dataSnapshot){
            Post post = dataSnapshot.getValue(Post.class);
            System.out.println(post);
        }

        @Override
        public void onCancelled (DatabaseError databaseError){
            System.out.println("The read failed: " + databaseError.getCode());
        }
    });*/
}