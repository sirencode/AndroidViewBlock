package diablo.viewblock.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    //第一块UI区域
    private LinearLayout firstLay;
    //第二块UI区域
    private LinearLayout secondLay;

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

        initFirstBlock(firstBlock);
        initSecondBlock(secondBlock);
    }

    /**
     * 初始化第一块UI块里面的UI
     * @param baseView 父view
     */
    private void initFirstBlock(View baseView) {
        Button firstBtn = (Button) baseView.findViewById(R.id.button1);
        firstBtn.setOnClickListener(this);
        Button secondBtn = (Button) baseView.findViewById(R.id.button2);
        secondBtn.setOnClickListener(this);
    }

    /**
     * 初始化第二块UI块里面的UI
     * @param baseView 父view
     */
    private void initSecondBlock(View baseView) {
        Button change = (Button) baseView.findViewById(R.id.button3);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Toast.makeText(this, "first block first button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "first block second button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this, "second block change button", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
