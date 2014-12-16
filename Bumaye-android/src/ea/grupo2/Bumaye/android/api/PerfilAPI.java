package ea.grupo2.Bumaye.android.api;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import android.util.Log;

// METODOS LLAMADOS POR LAS DISTINTAS ACTIVITY

public class PerfilAPI {

	private final static String TAG = PerfilAPI.class.toString();

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

	public PersonajeVO loginUsr(String texto, String pass, String url) {		
		Log.d(TAG, "Login()");
		PersonajeVO person = null;
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
	public PersonajeVO registUsr(String texto, String pass, String mail, String url) {		
		Log.d(TAG, "Register() en API");
		Log.d(TAG, url);
		PersonajeVO person = null;
		UsuarioVO dev = new UsuarioVO();
		dev.setUsername(texto);
		dev.setPass(pass);
		dev.setEmail(mail);
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

	

}