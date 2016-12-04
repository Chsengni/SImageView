package com.szysky.customize.simageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.szysky.customize.simageview.data.SimulationData;
import com.szysky.customize.siv.SImageView;

import java.util.ArrayList;

import static com.szysky.customize.simageview.R.styleable.SImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sususu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rv_main = (RecyclerView) findViewById(R.id.rv_main);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 5);
        rv_main.setLayoutManager(gridLayoutManager);

        ArrayList<SimulationData> datas = new ArrayList<>();
        new SimulationData(getApplicationContext(), datas);
        rv_main.setAdapter(new MyAdapter(getApplicationContext(), datas));



        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_test);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
//        bitmaps.add(bitmap);
       // bitmaps.add(bitmap);
//        iv.setBorderWidth(10);
//        iv.setDisplayShape(SImageView.TYPE_CIRCLE);
      //  iv.setCloseNormalOnePicLoad(true);
       // iv.setBorderColor(getResources().getColor(R.color.testColor));



    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        private final Context context;
        private final ArrayList<SimulationData> datas;

        MyAdapter (Context context, ArrayList<SimulationData> datas){
            this.context = context;
            this.datas = datas;

        }
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = View.inflate(context, R.layout.item_list, null);

            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            // 获取控件要展示的信息
            SimulationData data = datas.get(position);
            com.szysky.customize.siv.SImageView sImageView = holder.siv_display;


            // 设置是否关闭单张图片处理逻辑
            sImageView.setCloseNormalOnePicLoad(data.mCloseNormalOnePicLoad);

            // 设置描边颜色
            if (data.borderColor <= 0){
                sImageView.setBorderColor(data.borderColor);
            }

            // 设置描边宽度
            sImageView.setBorderWidth(data.borderWidth);

            // 设置显示类型
            sImageView.setDisplayShape(data.displayType);

            // 设置测量布局
            if (null != data.mMeasureManager){
                sImageView.setLayoutManager(data.mMeasureManager);

            }

            // 最后设置图片展示
            sImageView.setImages(data.readyBmp);
        }



        @Override
        public int getItemCount() {
            return datas.size();
        }

        static class MyHolder extends RecyclerView.ViewHolder{
            public SImageView siv_display;

            public MyHolder(View itemView) {
                super(itemView);
                siv_display = (com.szysky.customize.siv.SImageView) itemView.findViewById(R.id.siv);
            }
        }


    }


}
