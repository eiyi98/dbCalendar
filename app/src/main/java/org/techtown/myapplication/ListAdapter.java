package org.techtown.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder>
implements  ItemTouchHelperListener, OnDialogListener{

    ArrayList<Person> items = new ArrayList<>();
    Context context;
    public ListAdapter(Context context){this.context = context;}

    /*public ArrayList<Person> getItems(){
        return items;
    }*/

@NonNull
@Override
public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //LayoutInflater를 이용해서 원하는 레이아웃을 띄워줍니다.
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.list_item, parent, false);
    return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
    //ItemViewHolder가 생성되고 넣어야할 코드들을 넣어줍다.
        // 보통 onBind 함수 안에 모두 넣어줍니다.
        ;


    holder.onBind(items.get(position));


    }

    @Override
    public int getItemCount() {

        return items.size();
}
    public void addItem(Person person){
    //items에 Person객체 추가 items.add(person);
        // 추가후 Adapter에 데이터가 변경된것을 알림
        items.add(person);

    }
    @Override
    public boolean onItemMove(int from_position, int to_position){
        //이동할 객체 저장
        Person person = items.get(from_position);
        //이동할 객체 삭제
        items.remove(from_position);
        //이동하고 싶은 position에 추가
        items.add(to_position,person);
        //어댑터에 데이터 이동 알림
        notifyItemMoved(from_position,to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        items.remove(position);
        notifyItemRemoved(position);

    }


//왼쪽 버튼 누르면 수정할 다이얼로그 띄우기
@Override public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
    //수정 버튼 클릭시 다이얼로그 생성
    CustomDialog dialog = new CustomDialog(context, position, items.get(position));
    //화면 사이즈 구하기
    DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
    int width = dm.widthPixels; int height = dm.heightPixels;
    //다이얼로그 사이즈 세팅
    WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
    wm.copyFrom(dialog.getWindow().getAttributes());
    wm.width = (int) (width * 0.7);
    wm.height = height/2;
    //다이얼로그 Listener 세팅
    dialog.setDialogListener(this);
    //다이얼로그 띄우기
    dialog.show(); }
    //오른쪽 버튼 누르면 아이템 삭제

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder)
    { items.remove(position); notifyItemRemoved(position); }

    @Override
    public void onFinish(int position, Person person)
    { items.set(position,person); notifyItemChanged(position); }



    class ItemViewHolder extends RecyclerView.ViewHolder
    { TextView list_name;


    public ItemViewHolder(View itemView)
    {
        super(itemView); list_name = itemView.findViewById(R.id.list_name);
    }

    public void onBind(Person person)
    {

        list_name.setText(person.getName());

    }


    }


}






class ItemViewHolder extends RecyclerView.ViewHolder
{

    public TextView list_name;
    public CheckBox checkBox;







    public ItemViewHolder(View itemView) {
        super(itemView);
        list_name = itemView.findViewById(R.id.list_name);
        checkBox=(CheckBox)itemView.findViewById(R.id.chk1);

        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    list_name.setPaintFlags(list_name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }




            }


        });
    }
}










