package ea.grupo2.Bumaye.Motor;
 
import java.util.ArrayList;
import java.util.List;
 
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.pojos.ArmasArmaduras;
import ea.grupo2.Bumaye.pojos.Ataques;
import ea.grupo2.Bumaye.pojos.Batalla;
import ea.grupo2.Bumaye.pojos.UsrPersonaje;
 
 
public interface BumayeInterface {
     
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
 
    //funciones interface usuario/personaje
    public String addUsrPersonaje(UsrPersonaje usrpersonaje);
    public String updateUsrPersonaje(int idUsrPersonaje, UsrPersonaje usrpersonaje);
    public String deleteUsrPersonaje(int idUsrPersonaje);
    public PersonajeVO LoginUser(UsuarioVO userlog);
    public PersonajeVO RegistroUser(UsuarioVO userregistrado);
     
     
    //funciones PersojaneVO
    public PersonajeVO getPersonaje(int idPersonaje);
//  public String añadirPersonajeVO(PersonajeVO personaje);
    public BatallaVO updateAtributosBatallaVO(int idbatallaVO,int idPersonajeActuali, String atributo, float nuevoatributo);
    public BatallaVO ResultadoAtaqueVO(int idataque, int idbatallaVO);
     
    //funcion de batalla
    public void PasaTurno(int idBatallaVO);
    public BatallaVO getBatallaVO(int idBatalla);
    public BatallaVO iniciarBatallaVO(ArrayList<PersonajeVO> listaPersonajesVO); 
//  public BatallaVO UpdateTurnoVO(BatallaVO batallaVO);
    public String addbatallafinBBDD(Batalla batalla);
    public int EfectuarAtaque(int idataque);
    public String UpdateAtributosBatalla(int iduser, String atributo, float nuevoAtrib);
    //FUNCIONES DE SIMULACION
}
