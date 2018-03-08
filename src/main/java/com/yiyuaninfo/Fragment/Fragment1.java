package com.yiyuaninfo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yiyuaninfo.R;
import com.yiyuaninfo.util.ViewUtils;

import java.util.ArrayList;

/**
 * Created by Daemon1993 on 15/8/22.
 */
public class Fragment1 extends Fragment {

    private ListView lvdata;
    private ArrayList<String> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null,false);
        lvdata = (ListView)view.findViewById(R.id.lv_data);
        list=new ArrayList<String>();
        for(int i=0 ; i<80 ; i++){
            list.add("123");
        }
        MyAdpater myAdpater=new MyAdpater(getActivity());

        lvdata.setAdapter(myAdpater);
        int listViewHeight = ViewUtils.setListViewHeightBasedOnChildren1(lvdata);
        Log.d("recycleview的高度1",listViewHeight+"");

        ViewGroup.LayoutParams params = ((Homefragment)getParentFragment()).viewPagerHome.getLayoutParams();
        params.height = listViewHeight;
        ((Homefragment)getParentFragment()).viewPagerHome.setLayoutParams(params);


        return view;
    }


    class MyAdpater extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private Context context;

        public MyAdpater(Context context) {
            this.context=context;
            layoutInflater= LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = layoutInflater.inflate(R.layout.item_view,null,false);
            }else{

            }

            return convertView;
        }
    }



}
