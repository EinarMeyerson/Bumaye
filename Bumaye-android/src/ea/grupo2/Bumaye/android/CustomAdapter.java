package ea.grupo2.Bumaye.android;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomAdapter extends BaseAdapter{   
	String [] result;
	int [] cantidad;
	Context context;
	private static LayoutInflater inflater=null;
	public CustomAdapter(InventarioActivity inventarioActivity, String[] prgmNameList, int[] prgmCantidadList) {
		// TODO Auto-generated constructor stub
		result=prgmNameList;
		cantidad=prgmCantidadList;
		context=inventarioActivity;
		inflater = ( LayoutInflater )context.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public class Holder
	{
		TextView tv;
		TextView cn;
		ImageView img;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder=new Holder();
		View rowView;       
		rowView = inflater.inflate(R.layout.objeto_adapter, null);
		holder.tv=(TextView) rowView.findViewById(R.id.nombre_objeto);
		holder.cn=(TextView) rowView.findViewById(R.id.cantidad_objeto);
		holder.img=(ImageView) rowView.findViewById(R.id.imagen_objeto);       
		holder.tv.setText(result[position]);
		holder.cn.setText("          x"+Integer.toString(cantidad[position]));
		rowView.setTag(holder.tv.getText());
		
		Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+ holder.tv.getText());
		
		holder.img.setImageURI(uri); 
		return rowView;
	}

}
