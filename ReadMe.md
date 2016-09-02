### Android UI 模块化实现
在比较复杂的页面里面，会根据不同的业务逻辑将ui分为不同的块。当我们初始化的时候会很混乱，而且很容易和其他ui块的子View混淆，为了使各个ui块更加清晰，我们将UI块化。

#### 1 主UI实现，main布局文件
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_height="match_parent"
    android:layout_width="match_parent"
    android:orientation="vertical">
    <LinearLayout
        android:gravity="center"
        android:id="@+id/block1"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_width="match_parent"
        android:orientation="vertical" />

    <LinearLayout
        android:id="@+id/block2"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_width="match_parent"
        android:orientation="vertical" />
    </LinearLayout>

#### 2 各个块UI实现，block布局
    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_height="match_parent"
    android:layout_width="match_parent">

    <TextView
        android:gravity="center"
        android:id="@+id/textView"
        android:layout_alignParentEnd="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:text="This is block1!"
        android:textAppearance="?android:attr/textAppearanceMedium" />
    <Button
        android:id="@+id/button1"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_below="@+id/textView"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:text="button1" />
    <Button
        android:id="@+id/button2"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true"
        android:layout_below="@+id/textView"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:text="button2" />
    </RelativeLayout>


#### 3 逻辑处理

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

