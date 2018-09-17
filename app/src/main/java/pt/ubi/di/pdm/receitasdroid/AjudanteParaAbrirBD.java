package pt.ubi.di.pdm.receitasdroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;

import pt.ubi.di.pdm.receitasdroid.R;


public class AjudanteParaAbrirBD extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String BD_NAME="MainActivity";
    protected static final String TABLE_RECIPE ="Recipe";
    protected static final String TABLE_INGREDIENTS ="Ingredients";
    protected static final String TABLE_STEPS ="Steps";
    protected static final String COL1="id_rec";
    protected static final String COL2="name";
    protected static final String COL3="description";
    protected static final String COL4="id_ing";
    protected static final String COL5="ingredient";
    protected static final String COL6="id_step";
    protected static final String COL7="step";




    private static final String CREATE_RECIPE="Create Table Recipe(" +
            "id_rec integer primary key AUTOINCREMENT," +
            "name varchar(200)," +
            "description varchar(500));";



    private static final String[][] RECIPES={
            {"Cação com maça", "Serve with rice, yoghurt and fresh guacamole. Garnish with the remaining coriander."},
            {"Estrogonofe de carne","Serve with rice, yoghurt and fresh guacamole. Garnish with the remaining coriander."},
            {"Lasanha aos quatro queijos", "Some guacamole recipes call for many ingredients and can be a pain to prepare. This super simple guac can be thrown together in a couple of minutes and tastes great."},
            {"Macarrão com molho de requeijão e maionese","Some guacamole recipes call for many ingredients and can be a pain to prepare. This super simple guac can be thrown together in a couple of minutes and tastes great."}
    };



    private static final String CREATE_INGREDIENTS="Create Table Ingredients(" +
            "id_ing integer primary key AUTOINCREMENT," +
            "id_rec integer," +
            "ingredient varchar(200)," +
            "CONSTRAINT CI1 FOREIGN KEY (id_rec) REFERENCES Recipe (id_rec));";



    private static final String[][] INGREDIENTS= {
            {"1", "4 postas de cação (ou tubarão)"},
            {"1", "3 dentes de alho picados)"},
            {"1", "1 xícara (chá) de molho de tomate"},
            {"1", "1/2 copo de leite)"},
            {"1", "1 unidade de maçã sem casca cortada em cubinhos"},
            {"1", "1 colher (sobremesa) de curry (ou caril)"},
            {"1", "1/4 colher (chá) de noz-moscada"},
            {"1", "1/2 colher (chá) de canela em pó"},
            {"1", "1 colher (sopa) de coentro picado"},
            {"1", "• sal a gosto"},
            {"1", "• pimenta-do-reino a gosto"},


            {"2", "500 gramas de alcatra cortada em tirinhas"},
            {"2", "1/4 xícara (chá) de manteiga"},
            {"2", "1 unidade de cebola picada"},
            {"2", "1 colher (sobremesa) de mostarda"},
            {"2", "1 colher (sopa) de ketchup (ou catchup)"},
            {"2", "1 pitada de pimenta-do-reino"},
            {"2", "1 unidade de tomate sem pele picado"},
            {"2", "1 xícara (chá) de cogumelo variado | variados escorridos"},
            {"2", "1 lata de creme de leite"},
            {"2", "• sal a gosto"},


            {"3", "500 gramas de massa de lasanha cozida"},
            {"3", "250 gramas de queijo mussarela em fatias"},
            {"3", "3 colheres (sopa) de manteiga"},
            {"3", "2 colheres (sopa) de farinha de trigo"},
            {"3", "3 xícaras (chá) de leite"},
            {"3", "50 gramas de queijo gorgonzola esfarelad"},
            {"3", "100 gramas de queijo provolone ralado"},
            {"3", "50 gramas de queijo parmesão ralado"},
            {"3", "1 lata de creme de leite"},



            {"4", "500 gramas de macarrão longo cozido al dente"},
            {"4", "2 colheres (sopa) de azeite de oliva"},
            {"4", "1 unidade de cebola picada"},
            {"4", "1 lata de molho de tomate"},
            {"4", "1/2 lata de milho"},
            {"4", "1/2 unidade de pimentão verde picado"},
            {"4", "100 gramas de queijo mussarela ralado"},
            {"4", "1 copo de requeijão"},
            {"4", "4 colheres (sopa) de maionese"},
    };

    private static final String CREATE_STEPS="Create Table Steps(" +
            "id_step integer primary key AUTOINCREMENT," +
            "id_rec integer," +
            "step varchar(200)," +
            "CONSTRAINT STEP FOREIGN KEY (id_rec) REFERENCES Recipe (id_rec));";



    private static final String[][] STEPS= {
            {"1", "STEP 1: Tempere o peixe com o sal e a pimenta."},
            {"1", "STEP 2:Refogue o alho e a cebola no azeite."},
            {"1", "STEP 3:Junte o molho de tomate e cozinhe por 5 minutos."},
            {"1", "STEP 4:Adicione o leite, a maçã, o curry, a noz-moscada e a canela."},
            {"1", "STEP 5:Mexa e acrescente o peixe."},
            {"1", "STEP 6:Abaixe o fogo, tampe a panela e cozinhe por mais 10 minutos aproximadamente."},
            {"1", "STEP 7:Retire do fogo, polvilhe o coentro e sirva imediatamente."},
            {"1", "Dica: manga em cubos pode substituir a maçã. Coloque-a apenas no final do cozimento."},


            {"2", "STEP 1: Derreta a manteiga e refogue a cebola até ficar transparente."},
            {"2", "STEP 2: Junte a carne e tempere com o sal."},
            {"2", "STEP 3: Mexa até a carne dourar de todos os lados."},
            {"2", "STEP 4: Acrescente a mostarda, o catchup, a pimenta-do-reino e o tomate picado."},
            {"2", "STEP 5: Cozinhe até formar um moho espesso."},
            {"2", "STEP 6: Se necessário, adicione água quente aos poucos."},
            {"2", "STEP 7: Quando o molho estiver encorpado e a carne macia, adicione os cogumelos e o creme de leite."},
            {"2", "STEP 8: Mexa por 1 minuto e retire do fogo."},
            {"2", "STEP 9: Sirva imediatamente, acompanhado de arroz e batata palha."},
            {"2", "Dica: Se juntar água ao refogar a carne, frite-a até todo o líquido evaporar."},


            {"3", "STEP 1: Para fazer o molho, em uma panela, doure a farinha com a manteiga. Espere amornar."},
            {"3", "STEP 2: No liquidificador, bata a mistura de farinha e manteiga, o leite, o gorgonzola, o provolone, o parmesão e o creme de leite."},
            {"3", "STEP 3: Transfira para uma panela e leve ao fogo brando, a 160 ºC, mexendo sempre até engrossar."},
            {"3", "STEP 4: Em um refratário, alterne camadas de massa, molho e mussarela."},
            {"3", "STEP 5: Termine com molho, polvilhe queijo parmesão e leve ao forno alto, preaquecido, a 200 ºC, até derreter o queijo e dourar."},
            {"3", "Dica: varie de queijos. Substitua o provolone por queijo prato e o gorgonzola por queijo meia cura."},


            {"4", "STEP 1: Em uma panela, aqueça o azeite e doure a cebola."},
            {"4", "STEP 2: Junte o molho de tomate, o milho, o pimentão e cozinhe por 5 minutos no fogo brando (160 ºC)."},
            {"4", "STEP 3: Misture a mussarela, o requeijão e a maionese."},
            {"4", "STEP 4: Deixe no fogo até a mussarela derreter."},
            {"4", "STEP 5: Envolva a massa e sirva em seguida."},
            {"4", "Dica: Acrescente ao molho 100g de presunto ralado."}
    };


    public static final String q = "select R.name,R.description from Recipe R where R.id_rec=1;";
    public static final String s = "select I.ingredients from Ingredients I where I.id_rec=1;";
    public static final String t = "select S.step from Ingredients I where I.id_rec=1;";


    public AjudanteParaAbrirBD(Context context){
        super(context,BD_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_RECIPE);
        db.execSQL(CREATE_INGREDIENTS);
        db.execSQL(CREATE_STEPS);
        ContentValues cv = new ContentValues();
        for (int i = 0; i < RECIPES.length; i++) {
            cv.put(COL2, RECIPES[i][0]);
            cv.put(COL3, RECIPES[i][1]);
            db.insert(TABLE_RECIPE, null, cv);
        }

        cv = new ContentValues();
        for (int i = 0; i < INGREDIENTS.length; i++) {
            cv.put(COL1, INGREDIENTS[i][0]);
            cv.put(COL5, INGREDIENTS[i][1]);
            db.insert(TABLE_INGREDIENTS, null, cv);
        }

        cv = new ContentValues();
        for (int i = 0; i < STEPS.length; i++) {
            cv.put(COL1, STEPS[i][0]);
            cv.put(COL7, STEPS[i][1]);
            db.insert(TABLE_STEPS, null, cv);
        }




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE "+ TABLE_RECIPE + ";");
        db.execSQL("DROP TABLE "+ TABLE_INGREDIENTS + ";");
        db.execSQL("DROP TABLE "+ TABLE_STEPS + ";");
        db.execSQL(CREATE_RECIPE);
        db.execSQL(CREATE_INGREDIENTS);
        db.execSQL(CREATE_STEPS);

        ContentValues cv = new ContentValues();
        for (int i = 0; i < RECIPES.length; i++) {
            cv.put(COL2, RECIPES[i][0]);
            cv.put(COL3, RECIPES[i][1]);
            db.insert(TABLE_RECIPE, null, cv);
        }

        cv = new ContentValues();
        for (int i = 0; i < INGREDIENTS.length; i++) {
            cv.put(COL1, INGREDIENTS[i][0]);
            cv.put(COL5, INGREDIENTS[i][1]);
            db.insert(TABLE_INGREDIENTS, null, cv);
        }

        cv = new ContentValues();
        for (int i = 0; i < STEPS.length; i++) {
            cv.put(COL1, STEPS[i][0]);
            cv.put(COL7, STEPS[i][1]);
            db.insert(TABLE_STEPS, null, cv);
        }

    }



}
