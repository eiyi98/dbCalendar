//package org.techtown.myapplication;
//
//import android.graphics.Canvas;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MainActivity extends AppCompatActivity {
//    EditText name_et,age_et;
//    Button add_bt;
//    RecyclerView rv;
//    ListAdapter adapterM;
//    ItemTouchHelper helper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        name_et = findViewById(R.id.name_et);
//        add_bt = findViewById(R.id.add_bt);
//
//        add_bt.setOnClickListener(new View.OnClickListener()
//        { @Override public void onClick(View v)
//        {
//            //EditText 입력된 값 가져오기
//            String name = name_et.getText().toString();
//
//
//            //Person 객체 생성, 값 세팅
//            Person person = new Person(name);
//            person.setName(name);
//
//            //ListAdapter에 객체 추가
//            adapterM.addItem(person);
//            adapterM.notifyDataSetChanged();
//            //EditText 초기화
//            name_et.setText("");
//            //데이터 추가 확인 토스트 띄우기
//            Toast.makeText(MainActivity.this, "목록에 추가되었습니다", Toast.LENGTH_SHORT).show();
//            name_et.requestFocus(); } });
//
//        rv = findViewById(R.id.rv);
//        //RecyclerView의 레이아웃 방식을 지정
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        rv.setLayoutManager(manager);
//        //RecyclerView의 Adapter 세팅
//        adapterM = new ListAdapter(this) {
//        };
//        rv.setAdapter(adapterM);
//
//        //ItemTouchHelper 생성
//        helper =new ItemTouchHelper(new ItemTouchHelperCallback(adapterM));
////RecyclerView에 ItemTouchHelper 붙이기기
//       helper.attachToRecyclerView(rv);
//    }
//    private void setUpRecyclerView(){
//        rv.addItemDecoration(new RecyclerView.ItemDecoration()
//    {
//        @Override public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
//    {
//        helper.onDraw(c,parent, state);
//            }
//        });
//    }
//}
//
//
//
