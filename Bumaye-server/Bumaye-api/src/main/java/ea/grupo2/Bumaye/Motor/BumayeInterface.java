package ea.grupo2.Bumaye.Motor;
 
import java.util.ArrayList;
import java.util.List;
 


















import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.MercadoVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCofreCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeLogeadoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.PeticionBatallaVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.pojos.ArmasArmaduras;
import ea.grupo2.Bumaye.pojos.Ataques;
import ea.grupo2.Bumaye.pojos.Batalla;
import ea.grupo2.Bumaye.pojos.Cofre;
import ea.grupo2.Bumaye.pojos.Objeto;
import ea.grupo2.Bumaye.pojos.UsrPersonaje;
 
 
public interface BumayeInterface {
	
	//funciones extras, como randomint o distancia entre puntos
	
	public double distance(double lat1, double lat2, double lon1, double lon2);
	public double deg2rad(double deg);
	public int randInt();
	
	//funciones de objetos
	public String addObjeto(Objeto objeto);
	public String añadirObjetos(int idobjeto, int iduser);
    public List<ObjetoCantidadVO> listaObjetosUsr(int iduser);
    public ObjetoCantidadVO combinacion(int iduser, String objeto1, String objeto2) throws Exception;
    public boolean VerificarObjeto(String objeto , int idPersonajeVO) throws Exception;
    public ObjetoCantidadVO combinacionFinal(int iduser, String objeto1, String objeto2) throws Exception;
    public ObjetoCantidadVO getObjeto(String objeto, int idUser);
    public ObjetoVO getObjeto(int idObjeto);

    
	//funciones de cofre
	public String addCofre(Cofre cofre);
	public String añadirObjetosCofre(int idobjeto, int idCofre);
    public List<ObjetoCofreCantidadVO> listaObjetosCofre(int idCofre);
    public String eliminarObjetosCofre(int idUser, int idCofre, int idObjeto);
    public String recogerObjetosCofre(int idUser, int idCofre, int idObjeto, int cantidad) throws Exception;
	public boolean VerificarCantidadCofre(int idCofre, int idObjeto, int cantidad) throws Exception;
    public String limpiezaObjetosCofre();
    public boolean VerificarDistanciaCofre(int iduser, int idcofre);
    public CofreVO getCofre(int idcofre);


	//funciones de inventario
    public String eliminarObjetosInventario(int idUser, int idObjeto);
    public String limpiezaObjetosInventario();

    //funciones inreface de los Ataques
    public String addAtaqueBBDD(Ataques ataque, int idarmaarmadura);
    public String updateAtaqueBBDD(int idAtaque, Ataques ataque);
    public String deleteAtaqueBBDD(int idAtaque);
    public AtaqueVO getAtaque(int idAtaque);
    public List<AtaqueVO> listaAtaquesUsr(int iduser);
    public List<AtaqueVO> listaAtaquesArm(int idArm);
     
    //funciones interface de las Armaduras y Armas
    public String addArmasArmadurasBBDD(ArmasArmaduras armasarmaduras);
    public String updateArmasArmadurasBBDD(int idArmasArmaduras, ArmasArmaduras armasarmaduras);
    public String deleteArmasArmadurasBBDD(int idArmasArmaduras);
    public String añadirArmasArmaduras(int idarmaarmadura, int iduser);
    public String añadirArmasArmadurasEquipada(int idarmaarmadura, int iduser);
    public String desequiparArmasArmadurasEquipada(int idarmaarmadura, int iduser);
    public int verificarArmaArmaduraEquipada(int idarmaarmadura, int iduser);
    public List<ArmaArmaduraVO> listaArmasArmadurasUsr(int iduser);
    public void updateAtributosEquipada_UserArmasArmaduras(int idarmaarmadura, int iduser);
    public void updateAtributosDesequipada_UserArmasArmaduras(int idarmaarmadura, int iduser);
 
    //funciones interface usuario/personaje
    public String addUsrPersonaje(UsrPersonaje usrpersonaje);
    public String updateUsrPersonaje(int idUsrPersonaje, UsrPersonaje usrpersonaje);
    public String deleteUsrPersonaje(int idUsrPersonaje);
    public PersonajeVO LoginUser(UsuarioVO userlog);
    public PersonajeVO RegistroUser(UsuarioVO userregistrado);
    public boolean VerificarCapacidadInventario(int idUser, int idObjeto);
    public String CambiarPosicionUser(int iduser, double latitud, double longitud);
    public int devolvemosaIDuser(String nombrePersonaje);
    
  //Lista de jugadores logeados basic info
    public List<PersonajeLogeadoVO> listPersonajes(int idUsersolicita);     
    //Lista de cofres info
    public List<CofreVO> listCofres();     
     
    //funciones PersojaneVO
    public PersonajeVO getPersonaje(int idPersonaje);
    public PersonajeVO getPersonajeCompleto(int idPersonaje);
    public BatallaVO updateAtributosBatallaVO(int idbatallaVO,int idPersonajeActuali, String atributo, float nuevoatributo);
    public BatallaVO ResultadoAtaqueVO(int idataque, int idbatallaVO, int idPersonajeVO) throws Exception;
    public String añadirObjetoInventarioVerificado(int idobjeto, int iduser) throws Exception;
    
    //funciones para la peticion de la batalla
    public String crearPeticion (int idatacante, int iddefensor);
    public BatallaVO aceptarPeticion (int iddefensor);
    public int comprovacion_solo_una_atacante (int idatacante);
    public PeticionBatallaVO comprovacion_peticion (int iddefensor);
    public PeticionBatallaVO comprovacion_peticion_atacante (int idatacante);
    public BatallaVO aceptarPeticion_atacante (int atacante);
    public PeticionBatallaVO verificar_aceptacion (int idatacante);
    public void eliminar_peticion(int idatacante);
    public String comprobarPeticion (int iddefensor);
    
    
    //funcion de batalla
    public void PasaTurno(int idBatallaVO);
    public BatallaVO getBatallaVO(int idBatalla);
    public BatallaVO iniciarBatallaVO(ArrayList<PersonajeVO> listaPersonajesVO); 
//  public BatallaVO UpdateTurnoVO(BatallaVO batallaVO);
    public String addbatallafinBBDD(Batalla batalla);
    public int EfectuarAtaque(int idataque);
    public String UpdateAtributosBatalla(int iduser, String atributo, float nuevoAtrib);
    public boolean VerificarTurno(int idBatallaVO, int posicionBatalla);
    public BatallaVO RealizarAtaque(int idbatallaVO, int idataque, int mod, int mod2);
    public boolean VerificarAtaque(int idataque, int idPersonajeVO) throws Exception;

    public BatallaVO UtilizarObjeto(int idbatallaVO, String nombreObj, int mod);
    public BatallaVO ResultadoUtilizarObjetoVO(String nombreObj, int idbatallaVO, int idPersonajeVO) throws Exception;
    public int EfectuarObjeto(int idobjeto);
    //FUNCIONES DE SIMULACION
    
    //MERCADO
    public String addEntradaMercado(int idUser, int idObjeto, int cantidad, int precioUnidad);
    public MercadoVO getMercadoVO(int idEntradaMercado);
    public String compraObjetoMercado(int idEntradaMercado, int cantidad, int idUserCompador) throws Exception;
    public String compraObjetoUser(int idEntradaMercado, int cantidad, int idUserCompador);
    public boolean VerificarCompra(int totalCompra, int idUser) throws Exception;
    public boolean VerificarCantidadCompra(int idEntradaMercado, int cantidad) throws Exception;
    public String limpiezaEntradaMercado();
    public List<MercadoVO> listaMercado();

}
