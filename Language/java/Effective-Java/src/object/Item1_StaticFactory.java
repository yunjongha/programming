package object;

public class Item1_StaticFactory {

}

class Car {

	private final CarType type;
	private final String brand;
	private String owner;
	
	private static Car myCar;
	
	//static factory method로 객체 생성시 생성자는 private이나 protected으로 한다.
	protected Car(CarType type, String brand) {
		this.type = type;
		this.brand = brand;
	}
	
	//일반적인 객체 생성은 static factory method로 한다.
	//경우에 따라 sub class가 리턴 될 수 있다.
	public static Car createCar(CarType type, String brand) {
		switch(type) {
		case Electronic: return new ElectronicCar(brand);
		case Hydrogen: return new HydrogenCar(brand);
		default: return new Car(type, brand);
		}
	}
	
	//특별한 객체 생성은 method 이름을 다르게 줄 수 있다.
	//또한 동일한 객체를 요청한다면 매번 생성하지 않고 기존 거를 반환할 수 있다.
	public static Car createMyCar(CarType type, String brand, String owner) {

		synchronized (Car.class) { //여러 thread에서 동시에 호출시 중복 객체 생성되지 않도록 atomic을 보장한다.
			if (myCar != null 
					&& myCar.type == type 
					&& myCar.brand == brand 
					&& myCar.owner == owner) {
				return myCar;
			} else {
				myCar = new Car(type, brand);
				myCar.owner = owner;
			}
		}
		
		return myCar;
	}
	
}

class ElectronicCar extends Car {

	private int batteryTime;
	
	protected ElectronicCar(String brand) {
		super(CarType.Electronic, brand);
	}

}

class HydrogenCar extends Car {

	private int fuels;
	
	protected HydrogenCar(String brand) {
		super(CarType.Hydrogen, brand);
	}
	
}

class SolidElectronicCar extends ElectronicCar {

	private String solidBattery;
	
	protected SolidElectronicCar(String brand) {
		super(brand);
	}
}

enum CarType {
	Gasoline, Diesel, Hybrid, Electronic, Hydrogen
}
