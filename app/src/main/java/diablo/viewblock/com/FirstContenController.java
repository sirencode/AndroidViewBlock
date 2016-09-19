package diablo.viewblock.com;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Diablo on 16/9/2.
 */
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
