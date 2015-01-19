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
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.EquipamientoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.PeticionBatallaVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import android.util.Log;

public class BatallaAPI {
	private final static String TAG = BatallaAPI.class.toString();

	public void peticionBatalla(String url) {		
		Log.d(TAG, "Peticion API");
		//		PuntoVO punt = new PuntoVO();
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		HttpPut put = new HttpPut(url);

		try {
			// put.addHeader("content-type", MediaType.API_PUNTO);
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
	
	
	public PeticionBatallaVO solicitudVerificaciona(String url) {		
		Gson gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		PeticionBatallaVO data = new PeticionBatallaVO();
		 java.lang.reflect.Type arrayListType = new TypeToken<PeticionBatallaVO>()
		{}.getType();
		 gson = new Gson();
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 HttpEntity entity = response.getEntity();
		 Reader reader = new InputStreamReader(entity.getContent());
		 data = gson.fromJson(reader, PeticionBatallaVO.class);
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 return data;
	}
	
	public BatallaVO comprovacion_peticionBatalla(String url) {		
		Gson gson = new Gson();
		HttpClient httpClient = new DefaultHttpClient();
		BatallaVO data = new BatallaVO();
		 java.lang.reflect.Type arrayListType = new TypeToken<BatallaVO>()
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
	
	public BatallaVO ataque_enBatalla(String url) {		
		Log.d(TAG, "Login()");
		BatallaVO batalla = new BatallaVO();
        Gson gson = new GsonBuilder().create();
        
		HttpClient httpClient = new DefaultHttpClient();
		httpClient = WebServiceUtils.getHttpClient();
		
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		
		try {
			
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			batalla = gson.fromJson(reader, BatallaVO.class);
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
		}
		
		return batalla;
		
	}
	
	public String pregunta_es_mi_turno(String url) {		
		 HttpClient httpClient = new DefaultHttpClient();		
		 String responseString = "";
		 httpClient = WebServiceUtils.getHttpClient();
		 try {
		 HttpResponse response = httpClient.execute(new HttpGet(url));
		 responseString = EntityUtils.toString(response.getEntity());
		 } catch (Exception e) {
		 Log.i("json array","While getting server response server generate error. ");
		 }
		 Log.e(TAG, responseString);
		 return responseString;
	}
	
}
