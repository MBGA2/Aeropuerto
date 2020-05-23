package SA;
import Transfer.seg.*;




public interface seg_SA {

	public void init(String nombreArch);
	
	public void notifyRefresh();
	
	public boolean addCamToRepairs(TCam tC);
	
	public boolean loadData(String nombreArch);
	
	public TCam searchCamByID(TCam tC);
	
	public  void visFlow(TCam tC);
	
	public  void actvAlarm(TCam tC);
	
	public  void actvLockdown(TCam tC);
	
	
}
