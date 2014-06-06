package javaMeasure;

import java.util.ArrayList;

public class BatchProfile {
	
	public String profileName;
	public int profileID;
	public ArrayList<BatchSetting> profileSettings;
	
	public BatchProfile(){
		this.profileName=null;
		this.profileSettings = new ArrayList<>();
	}
	
	public BatchProfile(String profileName, ArrayList<BatchSetting> profileSettings){
		this.profileName = profileName;
		this.profileSettings = profileSettings;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public ArrayList<BatchSetting> getProfileSettings() {
		return profileSettings;
	}

	public void setProfileSettings(ArrayList<BatchSetting> profileSettings) {
		this.profileSettings = profileSettings;
	}
	
	public void addSetting(BatchSetting batchSetting){
		this.profileSettings.add(batchSetting);
	}

	public int getProfileID() {
		return profileID;
	}
	//should only be set by dbcontroller
	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}
	
	

}
