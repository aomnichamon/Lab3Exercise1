package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void addCourse(View v) {
        Intent i = new Intent(this,CourseActivity.class);
        startActivityForResult(i,1);

    }

    public void showCourseList(View v){
        Intent i = new Intent(this,CourseListActivity.class);
        String courseList = "";

        for(int j=0;j<listCodes.size();j++){
            courseList += listCodes.get(j) + "(" + listCredits.get(j).toString() + " credits) = " + listGrades.get(j) + "\n";
        }
        i.putExtra("courseList",courseList);
        startActivity(i);
    }

    public void buttonClicked(View v) {
    }

    public void resetList(View v){

        listCodes.clear();
        listCredits.clear();
        listGrades.clear();
        calculateGPA();

        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
        tvGPA.setText(" No!! ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if(requestCode ==1 && resultCode==RESULT_OK){
            listCodes.add(data.getStringExtra("cCode"));
            listCredits.add(data.getIntExtra("cCredit", 0));
            listGrades.add(data.getStringExtra("cGrade"));
            calculateGPA();
        }

    }
    public void calculateGPA() {
        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCR = (TextView)findViewById(R.id.tvCR);
        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
        double GP = 0;
        double grade;
        int credits=0;


        for(int i=0;i<listCredits.size();i++){
            grade =0;
            switch(listGrades.get(i)) {
                case "A":
                    grade = 4;
                    break;
                case "B+":
                    grade = 3.5;
                    break;
                case "B":
                    grade = 3;
                    break;
                case "C+":
                    grade = 2.5;
                    break;
                case "C":
                    grade = 2;
                    break;
                case "D+":
                    grade = 1.5;
                    break;
                case "D":
                    grade = 1;
                    break;
                case "F":
                    grade = 0;
                    break;

            }
            GP += grade *listCredits.get(i);
            credits += listCredits.get(i);

        }

        tvGP.setText(Double.toString(GP));
        tvCR.setText(Integer.toString(credits));
        tvGPA.setText(Double.toString(GP/credits));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
