package org.techtown.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialog extends Dialog
{ private OnDialogListener listener;
private Context context;
private Button mod_bt;
private EditText mod_name, mod_age;
private String name;
private int image,age;

public CustomDialog(Context context, final int position, Person person)
{ super(context); requestWindowFeature(Window.FEATURE_NO_TITLE);
getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
setContentView(R.layout.customdialog);
name = person.getName();

// EditText에 값 채우기
mod_name = findViewById(R.id.mod_name);
mod_name.setText(name);
mod_bt = findViewById(R.id.mod_bt);
mod_bt.setOnClickListener(new View.OnClickListener()

{ @Override public void onClick(View v)
{ if(listener!=null){
    //EditText의 수정된 값 가져오기
    String name = mod_name.getText().toString();

    Person person = new Person(name);
    //Listener를 통해서 person객체 전달
    listener.onFinish(position, person);

    //다이얼로그 종료
    dismiss(); } } }); }

    public void setDialogListener(OnDialogListener listener){ this.listener = listener; } }

