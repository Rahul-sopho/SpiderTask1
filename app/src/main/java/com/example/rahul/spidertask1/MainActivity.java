package com.example.rahul.spidertask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView L1,L2;
    EditText E1,E2;
    Button addB,removeB;
    int i=0,pos,j;
    String task;
    String[] str = new String[10000];
    int[] nos = new int[10000];
    boolean flag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        E1=(EditText)findViewById(R.id.edit);
        E2=(EditText)findViewById(R.id.edit1);
        addB=(Button)findViewById(R.id.add);
        removeB=(Button)findViewById(R.id.remove);
        L1 = (ListView)findViewById(R.id.list);
        L2 = (ListView)findViewById(R.id.list1);


        for(j=0;j<10000;j++)
        {
            str[j]="%";
        }
        for(j=0;j<10000;j++)
        {
            nos[j]=0;
        }


        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItems();
                displayItems();
                E1.setText(null);

            }
        });

        removeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeItems();
                displayItems();
                E2.setText(null);
            }
        });






            String[] S1 = new String[i];
            for (j = 0; j < i; j++) {
                S1[j] = str[j];
            }

            L1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent= new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("item",str[position]);
                    startActivity(intent);



                }
            });



        L2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent= new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("item",str[position]);
                startActivity(intent);



            }
        });


    }


    public void addItems()
    {
        task=E1.getText().toString();
        if(task.length()>0) {
            str[i] = task;
            nos[i]=i+1;
            i++;
            flag = true;
        }
        else
            Toast.makeText(getApplicationContext(), "Please Enter Task", Toast.LENGTH_LONG).show();
    }

    public void removeItems()
    {
        if(E2.getText().toString().length() > 0)
        {
        if(i>=1) {
            pos = Integer.parseInt(E2.getText().toString());
            if (pos <= i && pos > 0) {
                for (j = pos - 1; str[j + 1] != "%"; j++) {
                    str[j] = str[j + 1];
                }
                i--;
                flag = true;
            } else
                Toast.makeText(getApplicationContext(), "No item at the specified position", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(), "No item at the specified position", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(getApplicationContext(), "No item at the specified position", Toast.LENGTH_LONG).show();



    }

    public void displayItems()
    {
        if(i>=1 && flag) {
            String[] S1 = new String[i];
            String[] no = new String[i];
            for (j = 0; j < i; j++) {
                S1[j] = str[j];
                no[j]=Integer.toString(j+1)+")";
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_list, S1);
            ArrayAdapter<String> adap = new ArrayAdapter<String>(this,R.layout.item_list,no);

            L1.setAdapter(adapter);
            L2.setAdapter(adap);

            flag=false;
        }
        else
            if(i==0)
            {
                L1.setAdapter(null);
                L2.setAdapter(null);
            }

    }

}
