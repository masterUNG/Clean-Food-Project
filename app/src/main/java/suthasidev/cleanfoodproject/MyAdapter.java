package suthasidev.cleanfoodproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterUNG on 3/6/16 AD.
 */
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] iconStrings, titleStrings;

    public MyAdapter(Context context, String[] iconStrings, String[] titleStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.recipe_listview, viewGroup, false);

        //For Image
        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView);
        Picasso.with(context).load(iconStrings[i]).resize(100, 100).into(iconImageView);

        //For TextView
        TextView titleTextView = (TextView) view1.findViewById(R.id.textView2);
        titleTextView.setText(titleStrings[i]);

        return view1;
    }
}   // Main Class
