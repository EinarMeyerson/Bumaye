package ea.grupo2.Bumaye.android.api;


import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCofreCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;

// METODOS LLAMADOS POR LAS DISTINTAS ACTIVITY

public class MapAPI {

	private final static String TAG = MapAPI.class.toString();

	// METODO GET, SE DEFINE INPUT DE DATOS I UN BUFFEREDREADER

	public List<PersonajeVO> getAllPerson(String url) {
		Gson gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		 List<PersonajeVO> data = new ArrayList<PersonajeVO>();
		 java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<PersonajeVO>>()
		{}.getType();
		 gson = new Gson();
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 HttpEntity entity = response.getEntity();
		 Reader reader = new InputStreamReader(entity.getContent());
		 data = gson.fromJson(reader, arrayListType);
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 return data;
	}
	public List<CofreVO> getAllItem(String url) {
		Gson gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		 List<CofreVO> data = new ArrayList<CofreVO>();
		 java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<CofreVO>>()
		{}.getType();
		 gson = new Gson();
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 HttpEntity entity = response.getEntity();
		 Reader reader = new InputStreamReader(entity.getContent());
		 data = gson.fromJson(reader, arrayListType);
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 return data;
	}

	public List<ObjetoCofreCantidadVO> getObjetos(String url) {
		Gson gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		 List<ObjetoCofreCantidadVO> data = new ArrayList<ObjetoCofreCantidadVO>();
		 java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<ObjetoCofreCantidadVO>>()
		{}.getType();
		 gson = new Gson();
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 HttpEntity entity = response.getEntity();
		 Reader reader = new InputStreamReader(entity.getContent());
		 data = gson.fromJson(reader, arrayListType);
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 return data;
	}
	public String acceptObjetos(String url) {		
		 HttpClient httpClient = new DefaultHttpClient();		
		 String responseString = "";
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 responseString = EntityUtils.toString(response.getEntity());
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 return responseString;
	}

}
