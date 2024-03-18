package com.axiagroups.contactmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.axiagroups.contactmanager.data.DatabaseHandler;
import com.axiagroups.contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler db = new DatabaseHandler(this);
//        db.addContact(new Contact("Shehanuk", "01755215173"));
//        db.addContact(new Contact("Jodu", "01755215111"));
//        db.addContact(new Contact("Modu", "01755215222"));

        List<Contact> contactList = db.getAllContacts();

        for (Contact contact: contactList) {
            Log.d("TAG", "onCreate: " + contact.getName());
        }
    }
}