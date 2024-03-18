package com.axiagroups.contactmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.axiagroups.contactmanager.data.DatabaseHandler;
import com.axiagroups.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> contactArrayList;
    private ArrayAdapter<String> contactArrayAdapter;

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
//        db.addContact(new Contact("Shakil", "0111"));
//        db.addContact(new Contact("Tusar", "0122"));
//        db.addContact(new Contact("Sorowar", "0133"));
//        db.addContact(new Contact("Masum", "0144"));
//        db.addContact(new Contact("billa", "0155"));
//        db.addContact(new Contact("Ahsan", "0166"));
//        db.addContact(new Contact("kodu", "0177"));

        listView = findViewById(R.id.listView);
        contactArrayList = new ArrayList<>();
        List<Contact> contactList = db.getAllContacts();

        for (Contact contact: contactList) {
            Log.d("TAG", "onCreate: " + contact.getName());
            contactArrayList.add(contact.getName());
        }

        //create array adapter
        contactArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactArrayList
        );
        // add to listview
        listView.setAdapter(contactArrayAdapter);

        // add event Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = listView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this,clickedItem, Toast.LENGTH_SHORT).show();
            }
        });



    }
}