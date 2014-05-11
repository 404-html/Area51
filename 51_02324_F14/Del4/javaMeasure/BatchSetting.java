package javaMeasure;

public class BatchSetting {
	private int id;
	private String settingName;
	private String valueType;
	private String value;
	
	
	public BatchSetting(int id, String settingName, String valueType, String value) {
		this.id = id;
		this.settingName = settingName;
		this.valueType = valueType;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
