package pt.ubi.di.pdm.receitasdroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StepsActivity extends AppCompatActivity {
    private SQLiteDatabase oSQLiteDB;
    private AjudanteParaAbrirBD oAPABD;
    private int id;
    private TextView oLL;
    private Cursor oCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oAPABD = new AjudanteParaAbrirBD(this);
        oSQLiteDB = oAPABD.getWritableDatabase();

        oLL = (TextView) findViewById(R.id.Steps);

        oCursor = oSQLiteDB.query(oAPABD.TABLE_STEPS, new String[]{ "step" }, "id_rec="+id ,
                null , null , null , null , null);

        oCursor.moveToFirst();

        oLL.setText(oCursor.getString(0));
    }

    public void nextStep(View v){
        if(oCursor.moveToNext()){
            oLL.setText(oCursor.getString(0));
        }else{
            this.finish();
        }
    }

    public void previousStep(View v){
        if(oCursor.moveToPrevious()){
            oLL.setText(oCursor.getString(0));
        }else{
            this.finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
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
    public void back(View view) {
        Intent intent = new Intent( this, StepsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void done(View view) {
        Intent intent = new Intent( this, StepsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
