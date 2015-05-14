package data;

public class PartOfSpeech {
	private String descriptiveName = "";
	private String abreviation = "";
	
	public void PartOfSpeech(String posAbrev) {
		this.abreviation = posAbrev;
	}
	
	public void PartOfSpeech(String posAbrev, String posDescriptiveName) {
		this.abreviation = posAbrev;
		this.descriptiveName = posDescriptiveName;
	}
	
	public String getPOS() {
		return abreviation;
	}
}
