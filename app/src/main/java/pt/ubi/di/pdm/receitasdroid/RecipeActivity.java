package pt.ubi.di.pdm.receitasdroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RecipeActivity extends AppCompatActivity {
    private SQLiteDatabase oSQLiteDB;
    private AjudanteParaAbrirBD oAPABD;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        oAPABD = new AjudanteParaAbrirBD(this);
        oSQLiteDB = oAPABD.getWritableDatabase();

        LinearLayout oLL = (LinearLayout) findViewById(R.id.ingredientes);
        Cursor oCursor = oSQLiteDB.query(oAPABD.TABLE_RECIPE, new String[]{ "name", "description" }, "id_rec="+id ,
                null , null , null , null , null);

        oCursor.moveToFirst();
        TextView t = findViewById(R.id.titlo);
        t.setText(oCursor.getString(0));
        TextView t1 = findViewById(R.id.description);
        t1.setText(oCursor.getString(1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oCursor = oSQLiteDB.query(oAPABD.TABLE_INGREDIENTS,new String[]{"ingredient"},"id_rec="+id,null,null,null,null,null);
        boolean bCarryOn=oCursor.moveToFirst();
        while (bCarryOn) {
            TextView t3 = (TextView) getLayoutInflater().inflate(R.layout.ingrediente, null);
            t3.setText(oCursor.getString(0));


            oLL.addView(t3);
            bCarryOn = oCursor.moveToNext();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        this.finish();
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        oSQLiteDB = oAPABD.getWritableDatabase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        oAPABD.close();
    }

    public void start(View view) {
        Intent intent = new Intent( this, StepsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }




}
