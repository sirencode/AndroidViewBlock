package diablo.viewblock.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    //第一块UI区域
    private LinearLayout firstLay;
    //第二块UI区域
    private LinearLayout secondLay;

    private FirstContenController firstContenController;

    private SecondContentController secondContentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBlock();
    }

    /**
     * 初始化UI块
     */
    private void initBlock() {
        firstLay = (LinearLayout) findViewById(R.id.block1);
        secondLay = (LinearLayout) findViewById(R.id.block2);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View firstBlock = inflater.inflate(R.layout.block1, null);
        View secondBlock = inflater.inflate(R.layout.block2, null);
        firstLay.addView(firstBlock);
        secondLay.addView(secondBlock);

        firstContenController = new FirstContenController(MainActivity.this, firstBlock);
        secondContentController = new SecondContentController(MainActivity.this, firstBlock);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (firstContenController != null) {
            firstContenController.release();
        }

        if (secondContentController != null) {
            secondContentController.release();
        }
    }
}
