package com.yiyuaninfo.Activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.yiyuaninfo.R;
import com.yiyuaninfo.entity.Text;
import com.yiyuaninfo.util.ToastUtils;
import com.yiyuaninfo.view.BaseProgressDialog;
import com.yiyuaninfo.view.ProgressDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;


/**
 * Created by Administrator on 2017/2/24.
 */

public class PDFActivity extends BaseActivity {
    private PDFView pdfview;
    //private String  url;
    private String url = "http://yyapp.1yuaninfo.com/app/houtai/admin/pdf/1514743296.pdf";
    private File path;
    private String name;
    private TextView  textView;
    private ImageView  imageView;
    private BaseProgressDialog  dialog;
    @Override
    protected int getContentView() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //url = getIntent().getStringExtra("url");
        dialog = new ProgressDialog(this).setLabel("加载中...");
        dialog.show();
         setToolBarTitle("研报");
        path = this.getExternalFilesDir("yiyuaninfo");
        pdfview = (PDFView) findViewById(R.id.pdfView);
        // name=getFileName(uri);
        Download();

    }

    private void Download() {

        EasyHttp.downLoad(url)
                .savePath(path.getPath())
                .saveName("1514743296.pdf")
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void onStart() {
                       // ToastUtils.showToast("开始下载");


                    }

                    @Override
                    public void onError(ApiException e) {
                        Log.d("下载完成", "下载完成");

                    }

                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        Log.d("下载完成", "下载完成");


                    }

                    @Override
                    public void onComplete(String path) {
                       // ToastUtils.showToast("下载完成");
                        dialog.dismiss();
                        File mfile = new File(path);
                        Log.d("下载完成", mfile + "++" + path);
//
//                        if (mfile.exists()) {
//                            Bitmap bm = BitmapFactory.decodeFile(path);
//                            Drawable drawable = new BitmapDrawable(bm);// 转换成drawable
//                           // head.getBigCircleImageView().setImageDrawable(drawable);
//
//                        }
                         LoadPdf(mfile);

                    }
                });

    }

    private void LoadPdf(File file) {

        pdfview.fromFile(file)
       //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                       // Toast.makeText(getApplicationContext(), "loadComplete", Toast.LENGTH_SHORT).show();
                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {

                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                })
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();

    }








}
