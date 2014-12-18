package ea.grupo2.Bumaye.android.api;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.R;

public class JugadorAdapter extends BaseAdapter {

	List<PersonajeVO> data;
	LayoutInflater inflater;

	// mapea lso widgets creados en el layout
	private static class ViewHolder {
		TextView tvLibro;
		TextView tvAutor;
		TextView tvEditorial;
	}

	// contxt es por odnde se muebe la aplicacion, es donde esta la aplicaicon
	// hay que arrastrarlo
	public JugadorAdapter(Context context, List<PersonajeVO> result) {
		super();
		inflater = LayoutInflater.from(context);
		this.data = result;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int posicion) {
		// TODO Auto-generated method stub
		return data.get(posicion);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// es el reciclaje de vistas
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.jugador_row_layout, null);

			viewHolder = new ViewHolder();
			// referenciowidgets de layout con viewholder
			viewHolder.tvLibro = (TextView) convertView
					.findViewById(R.id.tvLibro);
			viewHolder.tvAutor = (TextView) convertView
					.findViewById(R.id.tvAutor);
			viewHolder.tvEditorial = (TextView) convertView
					.findViewById(R.id.tvEditorial);
			// lo guardo en el ConvertView
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String Nombre = data.get(position).getNombre();
		String Atack = Float.toString(data.get(position).getAtaque());
		String Deffens = Float.toString(data.get(position).getDefensa());
		viewHolder.tvLibro.setText(Nombre);
		viewHolder.tvAutor.setText("Ataque: "+Atack);
		viewHolder.tvEditorial.setText("Defensa: "+Deffens);
		return convertView;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
