package object;

public class Item1_StaticFactory {

}

class Car {

	private final CarType type;
	private final String brand;
	private String owner;
	
	private static Car myCar;
	
	//static factory method�� ��ü ������ �����ڴ� private�̳� protected���� �Ѵ�.
	protected Car(CarType type, String brand) {
		this.type = type;
		this.brand = brand;
	}
	
	//�Ϲ����� ��ü ������ static factory method�� �Ѵ�.
	//��쿡 ���� sub class�� ���� �� �� �ִ�.
	public static Car createCar(CarType type, String brand) {
		switch(type) {
		case Electronic: return new ElectronicCar(brand);
		case Hydrogen: return new HydrogenCar(brand);
		default: return new Car(type, brand);
		}
	}
	
	//Ư���� ��ü ������ method �̸��� �ٸ��� �� �� �ִ�.
	//���� ������ ��ü�� ��û�Ѵٸ� �Ź� �������� �ʰ� ���� �Ÿ� ��ȯ�� �� �ִ�.
	public static Car createMyCar(CarType type, String brand, String owner) {

		synchronized (Car.class) { //���� thread���� ���ÿ� ȣ��� �ߺ� ��ü �������� �ʵ��� atomic�� �����Ѵ�.
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
