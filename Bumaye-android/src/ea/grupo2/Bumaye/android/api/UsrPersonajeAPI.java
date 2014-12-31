package ea.grupo2.Bumaye.android.api;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ea.grupo2.Bumaye.ClasesVO.EquipamientoVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;

// METODOS LLAMADOS POR LAS DISTINTAS ACTIVITY

public class UsrPersonajeAPI {

	private final static String TAG = UsrPersonajeAPI.class.toString();

	// METODO GET, SE DEFINE INPUT DE DATOS I UN BUFFEREDREADER
	
	public PersonajeVO getPersonaje(String url) {
		Gson gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		PersonajeVO data = new PersonajeVO();
		 java.lang.reflect.Type aType = new TypeToken<PersonajeVO>()
		{}.getType();
		 gson = new Gson();
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 HttpEntity entity = response.getEntity();
		 Reader reader = new InputStreamReader(entity.getContent());
		 data = gson.fromJson(reader, aType);
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 return data;
	}
	
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

	public PersonajeVO loginUsr(String texto, String pass, String url) {		
		Log.d(TAG, "Login()");
		PersonajeVO person = new PersonajeVO();
		UsuarioVO dev = new UsuarioVO();
		dev.setUsername(texto);
		dev.setPass(pass);
        Gson gson = new GsonBuilder().create();
        
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		StringEntity params;
		
		try {
			params = new StringEntity(gson.toJson(dev));
	        post.addHeader("content-type", MediaType.API_USER);
	        post.setEntity(params);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			person = gson.fromJson(reader, PersonajeVO.class);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			Log.d(TAG, "No logeado");
			person = new PersonajeVO();
			person.setNombre("");
		}
		
		return person;
		
	}
	
// MIRAR QUE DEVUELVE LA API CUANDO HACES LA API (ESTARIA BIEN NO DEVOLVER NADA)
	public PersonajeVO registUsr(String texto, String pass, String mail,String idGCM,String lat, String lng, String url) {		
		Log.d(TAG, "Register() en API");
		Log.d(TAG, url);
		PersonajeVO person = null;
		UsuarioVO dev = new UsuarioVO();
		dev.setUsername(texto);
		dev.setPass(pass);
		dev.setEmail(mail);
		dev.setIdGCM(idGCM);
		dev.setLatitud(Double.parseDouble(lat));
		dev.setLongitud(Double.parseDouble(lng));
        Gson gson = new GsonBuilder().create();
        
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		StringEntity params;
		
		try {
			params = new StringEntity(gson.toJson(dev));
	        post.addHeader("content-type", MediaType.API_USER);
	        post.setEntity(params);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			person = gson.fromJson(reader, PersonajeVO.class);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			Log.d(TAG, "Register No valido");
			person = new PersonajeVO();
			person.setNombre("");
		}
		
		return person;
		
	}
	public void locateUsr(String url) {		
		Log.d(TAG, "Ubicacion() en API");
		        
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		HttpPut put = new HttpPut(url);
		
		try {
	        //put.addHeader("content-type", MediaType.API_USER);
			httpClient.execute(put);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
	}
	public ObjetoCantidadVO combinacionObjetos (String combo1, String combo2, String url) {		
		Log.d(TAG, "combinacionObjetos()");
		ObjetoCantidadVO person = new ObjetoCantidadVO();
//		UsuarioVO dev = new UsuarioVO();
//		dev.setUsername(texto);
//		dev.setPass(pass);
//        Gson gson = new GsonBuilder().create();
//        
//		HttpClient httpClient = new DefaultHttpClient();
//		httpClient = WebServiceUtils.getHttpClient();
//		
//		HttpPost post = new HttpPost(url);
//		HttpResponse response;
//		StringEntity params;
//		
//		try {
//			params = new StringEntity(gson.toJson(dev));
//	        post.addHeader("content-type", MediaType.API_USER);
//	        post.setEntity(params);
//			response = httpClient.execute(post);
//			HttpEntity entity = response.getEntity();
//			Reader reader = new InputStreamReader(entity.getContent());
//			person = gson.fromJson(reader, PersonajeVO.class);
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NullPointerException e){
//			Log.d(TAG, "No logeado");
//			person = new PersonajeVO();
//			person.setNombre("");
//		}
//		
		return person;
		
	}
	
	public PersonajeVO equiparUser (int iduser,int idarmequipada, int idesequipada,  String url) {		
		Log.d(TAG, "Equiparse()");
		PersonajeVO person = new PersonajeVO();
		EquipamientoVO equip = new EquipamientoVO();
		equip.setIdarmequipada(idarmequipada);
		equip.setIdesequipada(idesequipada);
		equip.setIduser(iduser);
		
        Gson gson = new GsonBuilder().create();
        
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		StringEntity params;
		
		try {
			params = new StringEntity(gson.toJson(equip));
	        post.addHeader("content-type", MediaType.API_EQUIPAMIENTO);
	        post.setEntity(params);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			person = gson.fromJson(reader, PersonajeVO.class);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			Log.d(TAG, "No equipado");
			person = new PersonajeVO();
			person.setNombre("");
		}
		
		return person;
		
	}
	
	public PersonajeVO desequiparUser (int iduser, int idesequipada,  String url) {		
		Log.d(TAG, "Equiparse()");
		PersonajeVO person = new PersonajeVO();
		EquipamientoVO equip = new EquipamientoVO();
		equip.setIdarmequipada(0);// cero porque no hay nada que equipar y por no crear una clase entera nueva
		equip.setIdesequipada(idesequipada);
		equip.setIduser(iduser);
		
        Gson gson = new GsonBuilder().create();
        
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		StringEntity params;
		
		try {
			params = new StringEntity(gson.toJson(equip));
	        post.addHeader("content-type", MediaType.API_EQUIPAMIENTO);
	        post.setEntity(params);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			person = gson.fromJson(reader, PersonajeVO.class);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			Log.d(TAG, "No Desequipado");
			person = new PersonajeVO();
			person.setNombre("");
		}
		
		return person;
		
	}

}
