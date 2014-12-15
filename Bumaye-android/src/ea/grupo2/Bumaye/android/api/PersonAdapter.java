package ea.grupo2.Bumaye.android.api;

import java.util.ArrayList;

import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
private ArrayList <PersonajeVO> data;
private LayoutInflater inflater;	

private static class ViewHolder {
	TextView tvSubject;
	TextView tvUsername;
	TextView tvDate;
}
	public PersonAdapter(Context context, ArrayList<PersonajeVO> data) {
		super();
		inflater = LayoutInflater.from(context);
		this.data = data;
	}
	
	@Override
	public int getCount() {//devuelve numero total de filas que habria en la lista , numero de datos q tu muestras
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {//modelo, cada posicion de la lista tiene un modelo de la cual tiene una serie de datos
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {//devuelve valor unico para una determinada posicion
		// TODO Auto-generated method stub
		return (((PersonajeVO)getItem(position)).getIduser());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {//devueve ese layout qe hemos creado cn datos
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_row_person, null);//listrowsting que habiamos creado
			viewHolder = new ViewHolder();
			viewHolder.tvSubject = (TextView) convertView
					.findViewById(R.id.tvSubject);
			viewHolder.tvUsername = (TextView) convertView
					.findViewById(R.id.tvUsername);
			viewHolder.tvDate = (TextView) convertView
					.findViewById(R.id.tvDate);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String username = data.get(position).getNombre();//recuperando valores de esa posicion
		String atack =  Float.toString(data.get(position).getAtaque());
		String deff = Float.toString(data.get(position).getDefensa());
		viewHolder.tvSubject.setText(username);
		viewHolder.tvUsername.setText(atack);
		viewHolder.tvDate.setText(deff);
		return convertView;
	}

}