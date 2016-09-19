package diablo.viewblock.com;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Diablo on 16/9/19.
 */
public class SecondContentController implements View.OnClickListener{
    private Activity activity;
    private View base;

    public SecondContentController(Activity activity,View base){
        this.activity = activity;
        this.base = base;
        initViews();
    }

    private void  initViews(){
        Button change = (Button) base.findViewById(R.id.button3);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(activity, "second button", Toast.LENGTH_SHORT).show();
    }

    //释放资源
    public void release(){

    }
}
