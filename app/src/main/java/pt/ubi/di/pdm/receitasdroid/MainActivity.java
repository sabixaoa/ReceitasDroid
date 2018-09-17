package pt.ubi.di.pdm.receitasdroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private SQLiteDatabase oSQLiteDB;
    private AjudanteParaAbrirBD oAPABD;
    private ListView lv;
    Button btnView;
    private ArrayList<String> myData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnView = (Button) findViewById(R.id.entrar);
        // escrever mensagem para o log
        Log.e("cenas","antes de abrir");
        oAPABD=new AjudanteParaAbrirBD(this);
        // escrever mensagem para o log
        Log.e("cenas","depois de abrir");
        oSQLiteDB=oAPABD.getWritableDatabase();
        Log.e("cenas","depois de pedir");
        // escrever mensagem para o log


        LinearLayout oLL = (LinearLayout) findViewById(R.id.llsv);
        Cursor oCursor = oSQLiteDB.query(oAPABD.TABLE_RECIPE, new String[]{ "*" }, null ,
                null , null , null , null , null);

        boolean bCarryOn = oCursor.moveToFirst();
        while (bCarryOn) {
//FALTA CODIGO
            LinearLayout oLL1 = (LinearLayout) getLayoutInflater().inflate(R.layout.main, null);
            oLL1.setId(oCursor.getInt(0)*10+4);

            TextView oED1 = (TextView) oLL1.findViewById (R.id.tv_name);
            oED1.setId(oCursor.getInt(0)*10+2);
            oED1.setText(oCursor.getString(1));

            TextView oED2 = (TextView) oLL1.findViewById (R.id.description);
            oED2.setId(oCursor.getInt(0)*10+3);
            oED2.setText(oCursor.getString(2));

            Button b = oLL1.findViewById(R.id.entrar);
            b.setId(oCursor.getInt(0)*10+1);

            oLL.addView(oLL1);
            bCarryOn = oCursor.moveToNext();
        }

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
    public void abrir(View view) {
        Intent intent = new Intent( this, RecipeActivity.class);
        intent.putExtra("id", view.getId()/10);
        startActivity(intent);
    }

    public void MR(View view) {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}
