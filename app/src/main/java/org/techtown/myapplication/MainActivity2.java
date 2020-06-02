package org.techtown.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    ArrayList<CalData> arrData;
   // ArrayList<String> list;
    Calendar mCalToday;
    Calendar mCal;
    GridView mGridView;
    DateAdapter adapterC;


    Date date = new Date();
    int thisYear = date.getYear();
    int thisMonth = date.getMonth();

    //메모
    EditText name_et,age_et;
    ImageView add_bt;
    RecyclerView rv;
    ListAdapter adapterM;
    ItemTouchHelper helper;
    TextView list_name;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mCalToday = Calendar.getInstance();
        mCal = Calendar.getInstance();

        setCalendarDate(thisYear, thisMonth);  //처음에 년/달 출력
        ImageButton pre = findViewById(R.id.pre); //이전 달
        ImageButton next = findViewById(R.id.next); //다음 달

        pre.setOnClickListener(this);
        next.setOnClickListener(this);

        name_et = findViewById(R.id.name_et);
        add_bt = findViewById(R.id.add_bt);






        add_bt.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                //EditText 입력된 값 가져오기
                String name = name_et.getText().toString();

                //Person 객체 생성, 값 세팅
                Person person = new Person(name);
                person.setName(name);

                //ListAdapter에 객체 추가
                adapterM.addItem(person);
                adapterM.notifyDataSetChanged();

                //EditText 초기화
                name_et.setText("");

                //데이터 추가 확인 토스트 띄우기
                Toast.makeText(MainActivity2.this, "목록에 추가되었습니다", Toast.LENGTH_SHORT).show();
                name_et.requestFocus(); } });

                rv = findViewById(R.id.rv);
                //RecyclerView의 레이아웃 방식을 지정
                LinearLayoutManager manager = new LinearLayoutManager(this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);
                //RecyclerView의 Adapter 세팅
                adapterM = new ListAdapter(this) {
                 };
                rv.setAdapter(adapterM);

                //ItemTouchHelper 생성
                helper =new ItemTouchHelper(new ItemTouchHelperCallback(adapterM));
                //RecyclerView에 ItemTouchHelper 붙이기기
                helper.attachToRecyclerView(rv);
    }

    private void setUpRecyclerView(){
        rv.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
            {
                helper.onDraw(c,parent, state);
            }
        });
    }

    //캘린더
    @Override
    public void onClick(View v) { //버튼 누를시 달 이동
        switch (v.getId()){
            case R.id.pre:
                thisMonth --;
                if (thisMonth == -1) // 1월은 0이니까 0에서 또 전으로 돌아가서 -1이 되면
                {
                    thisMonth = 11; // 12월로 세팅
                    thisYear--;
                }
                setCalendarDate(thisYear,thisMonth);
                break;

            case R.id.next:
                thisMonth ++;
                if (thisMonth == 12) // 12월이 11인데 또 올라가서 13이 되면
                {
                    thisMonth = 0; // 1월로 세팅
                    thisYear++;
                }
                setCalendarDate(thisYear,thisMonth);
                break;
            default:
                break;
        }
    }

    private void setCalendarDate(int year, int month) {

        arrData = new ArrayList<CalData>();

        Date date = new Date();
        date.setYear(year);
        date.setMonth(month);
        date.setDate(1);

        TextView today = findViewById(R.id.today); //현재텍스트
        today.setText((year + 1900) + "." + (month + 1 )); // 년/월 출력

        mCalToday.set((year + 1900), month, 1); //1일에 맞는 요일 세팅

        int startDay = mCalToday.get(Calendar.DAY_OF_WEEK);
        if (startDay != 1) {                    //시작 요일이 일요일이 아니면 그만큼 공백
            for (int i =0; i < startDay-1; i++) {
                arrData.add(null);
            }
        }
        mCal.set(Calendar.MONTH, month); //요일은 +1해야되서 달력에 요일을 세팅할 때 -1을 해줌
        int dayOfMonth = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (!isLeapYear((year)) && month == 1) // 윤년이고 2월이면
        {
            dayOfMonth--;
        }

        for (int i = 0; i < dayOfMonth; i++) {
            mCalToday.set(mCal.get(Calendar.YEAR), month, (i + 1));
            arrData.add(new CalData((i+1), mCalToday.get(Calendar.DAY_OF_WEEK)));
        }
        adapterC = new DateAdapter(this,arrData);
        mGridView = findViewById(R.id.gridview);
        mGridView.setAdapter(adapterC);
    }
    private boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }
}

class DateAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CalData> arrData;
    private LayoutInflater inflater;

    public DateAdapter(Context c, ArrayList<CalData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        /*
        if (arrData.get(position) == null)
        {
            return null;
        }
        else
        {
            return arrData.get(position).getDay();
        }
        */

        // 위에꺼랑 똑같은 소스코드
        // 조건 ? true 일때 : false 일때
        return arrData.get(position) == null ? "" : arrData.get(position).getDay();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.viewitem, parent, false);
            holder = new ViewHolder();
            holder.Viewitem = (TextView)convertView.findViewById(R.id.viewitem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.Viewitem.setText("" + getItem(position));

        TextView ViewText = convertView.findViewById(R.id.viewitem);

        if (position % 7 == 0) {
            ViewText.setTextColor(Color.RED);
        } else if (position % 7 == 6) {
            ViewText.setTextColor(Color.BLUE);
        } else {
            ViewText.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView Viewitem;
    }
}

