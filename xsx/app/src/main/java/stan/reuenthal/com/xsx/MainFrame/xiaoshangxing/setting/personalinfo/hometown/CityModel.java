package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.hometown;

public class CityModel {
	private String name;
	
	public CityModel() {
		super();
	}

	public CityModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "q.CityModel [name=" + name + "]";
	}
	
}
