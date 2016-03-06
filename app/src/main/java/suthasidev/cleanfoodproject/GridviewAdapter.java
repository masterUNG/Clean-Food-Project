package suthasidev.cleanfoodproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridviewAdapter extends BaseAdapter {

    private Context mContext;
    private String[] title;
    private int[] img;

    public GridviewAdapter(Context c, String[] title, int[] img) {
        mContext = c;
        this.title = title;
        this.img = img;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridview_item, null);

            TextView txtTitle = (TextView) grid.findViewById(R.id.grid_txt);
            txtTitle.setText(title[position]);

            ImageView imgView = (ImageView) grid.findViewById(R.id.grid_image);
            imgView.setImageResource(img[position]);

        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
