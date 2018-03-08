package com.yiyuaninfo.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyuaninfo.Fragment.BaseFragment;
import com.yiyuaninfo.Fragment.ChannelFragment;
import com.yiyuaninfo.Fragment.Communefragment;
import com.yiyuaninfo.Fragment.Homefragment;
import com.yiyuaninfo.Fragment.Myfragment;
import com.yiyuaninfo.Interface.MusicFragment;
import com.yiyuaninfo.Interface.ScienceFragment;
import com.yiyuaninfo.Interface.SearchFragment;
import com.yiyuaninfo.Interface.SettingFragment;
import com.yiyuaninfo.R;
import com.yiyuaninfo.util.CommonUtil;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by gaocongcong on 2017/7/21.
 */

public class MActivity extends BaseActivity implements View.OnClickListener,BaseFragment.OnFragmentInteractionListener {

    RelativeLayout rl_home;
    ImageView image_home;
    RelativeLayout rl_channel;
    ImageView image_channel;
    RelativeLayout rl_commune;
    ImageView image_commune;
    RelativeLayout rl_my;
    ImageView image_my;
    TextView tv_home;
    TextView tv_channel;
    TextView tv_commune;
    TextView tv_my;
    private Homefragment homefragment;
    private ChannelFragment channelFragment;
    private Communefragment communefragment;
    private Myfragment myfragment;
    private MusicFragment  musicFragment;
    private SettingFragment  settingFragment;
    private SearchFragment  searchFragment;
    private ScienceFragment  scienceFragment;
    private long mExitTime = 0;
    private FragmentManager fragmentManager;
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isshowToolBar(false);
        rl_home=(RelativeLayout) findViewById(R.id.rl_home);
        rl_channel=(RelativeLayout) findViewById(R.id.rl_channel);
        rl_commune=(RelativeLayout) findViewById(R.id.rl_commune);
        rl_my=(RelativeLayout) findViewById(R.id.rl_my);
        rl_my.setOnClickListener(this);
        rl_channel.setOnClickListener(this);
        rl_commune.setOnClickListener(this);
        rl_home.setOnClickListener(this);
        image_channel=(ImageView)findViewById(R.id.image_channel);
        image_home=(ImageView)findViewById(R.id.image_home);
        image_commune=(ImageView)findViewById(R.id.image_commune);
        image_my=(ImageView)findViewById(R.id.image_my);
        tv_home=(TextView)findViewById(R.id.tv_home);
        tv_channel=(TextView)findViewById(R.id.tv_channel);
        tv_commune=(TextView)findViewById(R.id.tv_commune);
        tv_my=(TextView)findViewById(R.id.tv_my);
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);

    }
    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                image_home.setImageResource(R.drawable.icon_home_true);
                tv_home.setTextColor(this.getResources().getColor(R.color.colorPrimary));
                if (musicFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    musicFragment = new MusicFragment();
                    transaction.add(R.id.view_content, musicFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(musicFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                image_channel.setImageResource(R.drawable.icon_channel_true);
                tv_channel.setTextColor(this.getResources().getColor(R.color.colorPrimary));

                if (scienceFragment == null) {
                    scienceFragment = new ScienceFragment();
                    transaction.add(R.id.view_content, scienceFragment);
                } else {
                    transaction.show(scienceFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                image_commune.setImageResource(R.drawable.icon_commune_true);
                tv_commune.setTextColor(this.getResources().getColor(R.color.colorPrimary));
                if (searchFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    searchFragment = new SearchFragment();
                    transaction.add(R.id.view_content, searchFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(searchFragment);
                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                image_my.setImageResource(R.drawable.icon_my_true);
                tv_my.setTextColor(this.getResources().getColor(R.color.colorPrimary));
                if (settingFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.view_content, settingFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }
    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        image_home.setImageResource(R.drawable.icon_home);
        tv_home.setTextColor(Color.parseColor("#82858b"));
        image_channel.setImageResource(R.drawable.icon_channel);
        tv_channel.setTextColor(Color.parseColor("#82858b"));
        image_commune.setImageResource(R.drawable.icon_commune);
        tv_commune.setTextColor(Color.parseColor("#82858b"));
        image_my.setImageResource(R.drawable.icon_my);
        tv_my.setTextColor(Color.parseColor("#82858b"));
    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homefragment != null) {
            transaction.hide(homefragment);
        }
        if (channelFragment != null) {
            transaction.hide(channelFragment);
        }
        if (communefragment != null) {
            transaction.hide(communefragment);
        }
        if (myfragment != null) {
            transaction.hide(myfragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_home:
                setTabSelection(0);
                break;
            case R.id.rl_channel:
                setTabSelection(1);
                break;
            case R.id.rl_commune:
                CommonUtil.showToast(this,"敬请期待！");
                //setTabSelection(2);
                break;
            case R.id.rl_my:
                setTabSelection(3);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Snackbar.make(rl_home, R.string.exit_toast, Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {

    }
}







































