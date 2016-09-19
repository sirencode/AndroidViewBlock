### Android UI 模块化实现
在比较复杂的页面里面，会根据不同的业务逻辑将ui分为不同的块。当我们初始化的时候会很混乱，而且很容易和其他ui块的子View混淆，为了使各个ui块更加清晰，我们将UI块化。

首先，这里的UI模块化是指布局碎片fragment文件里面的ui的模块化，并不是说叫大家不做ui布局的fragment的碎片化。我们这么做的主要目的是为了实现在最小碎片化上面在做一些模块化处理，当然这要根据fragment的ui的复杂程度而定，只有当ui很复杂的时候才会这么做。

#### 尽量不要使用activity和fragment
将独立的ui模块抽离出来，实现自定义control，将模块化的View封装在control里面，并将所有的view相关操作抽离，以及对生命周期的管理。

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


#### 3 模块逻辑处理

    public class FirstContenController implements View.OnClickListener {

    private Activity activity;
    private View base;

    public FirstContenController(Activity activity,View base){
        this.activity = activity;
        this.base = base;
        initViews();
    }

    private void  initViews(){
        Button firstBtn = (Button) base.findViewById(R.id.button1);
        firstBtn.setOnClickListener(this);
        Button secondBtn = (Button) base.findViewById(R.id.button2);
        secondBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(activity, "first block first button", Toast.LENGTH_SHORT).show();
    }

    //释放资源
    public void release(){

    }
    }

#### 4 逻辑处理

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

