

public interface CaloryCalculator {
	public double calcultateColory(NewSwimmingData newSimmingData, 
			HistorySwimmingData historySwimmingData, SwimmerInfo swimmerInfo);
	
	public static void main(String[] args){
		NewSwimmingData newSwimmingData = new NewSwimmingData(0.8, 600);
		System.out.println("Swimming distance:" + newSwimmingData.distance +
				", Swimming time:" + newSwimmingData.duration);
		SwimmerInfo swimmerInfo = new SwimmerInfo('M', 78, 176, 45);
		System.out.println("SwimmerInfo, sex:" + swimmerInfo.sex +
				", weight:" + swimmerInfo.weight + 
				", height:" + swimmerInfo.height +
				", age:" + swimmerInfo.age);
		HistorySwimmingData historySwimmingData = new HistorySwimmingData();
		SimpleCaloryCalculator scc = new SimpleCaloryCalculator();
		System.out.println("" + scc.calcultateColory(newSwimmingData, 
				historySwimmingData, swimmerInfo) + " calories consumed");
	}
}

class NewSwimmingData{
	double duration; //unit: hour
	double distance; //unit: m
	NewSwimmingData(double duration, double distance){
		this.duration = duration;
		this.distance = distance;
	}
}

class HistorySwimmingData{}

class SwimmerInfo{
	char sex; //'M':male; 'F': female
	double weight; //unit: kg
	double height; //unit: cm
	int age;
	
	SwimmerInfo(char sex, double weight, double height, int age){
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.age = age;
	}
}


//algorithm from: http://www.topswim.net/thread-13802-1-1.html
class SimpleCaloryCalculator implements CaloryCalculator{
	private static final double CALORIES_PER_HOUR_PER_KG = 9.5; //6~13
	
	public double calcultateColory(NewSwimmingData newSwimmingData, 
			HistorySwimmingData historySwimmingData, SwimmerInfo swimmerInfo){
		BasicMetabolismCalculator bmc = new BasicMetabolismCalculator();
		double weight = swimmerInfo.weight;
		double duration = newSwimmingData.duration;
		double calories = (CALORIES_PER_HOUR_PER_KG * weight - 
				bmc.getBasicMetabolism(swimmerInfo)) * duration;
		//double calories = CALORIES_PER_HOUR_PER_KG * weight * time;
		return calories;
	}
}