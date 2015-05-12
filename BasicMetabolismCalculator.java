class BMRTriple{
	int age;
	double maleBMR;
	double femaleBMR;
	BMRTriple(int age, double maleBMR, double femaleBMR){
		this.age = age;
		this.maleBMR = maleBMR;
		this.femaleBMR = femaleBMR;
	}
}

class BasicMetabolismCalculator{

	//BMRs data from http://blog.sina.com.cn/s/blog_626722b10100n3jm.html
	private static BMRTriple[] bmrs = {
		new BMRTriple(1, 53.0, 53.0),
		new BMRTriple(3, 51.3, 51.2),
		new BMRTriple( 5, 49.3, 48.4),
		new BMRTriple( 7, 47.3, 45.4),
		new BMRTriple( 9, 45.2, 42.8),
		new BMRTriple(11, 43.0, 42.0),
		new BMRTriple(13, 42.3, 40.3),
		new BMRTriple(15, 41.8, 37.9),
		new BMRTriple(17, 40.8, 36.3),
		new BMRTriple(19, 39.2, 35.5),
		new BMRTriple(20, 38.6, 35.3),
		new BMRTriple(25, 37.5, 35.2),
		new BMRTriple(30, 36.8, 35.1),
		new BMRTriple(35, 36.5, 35.0),
		new BMRTriple(40, 36.3, 34.9),
		new BMRTriple(45, 36.2, 34.5),
		new BMRTriple(50, 35.8, 33.9),
		new BMRTriple(55, 35.4, 33.3),
		new BMRTriple(60, 34.9, 32.7),
		new BMRTriple(65, 34.4, 32.2),
		new BMRTriple(70, 33.8, 31.7),
		new BMRTriple(75, 33.2, 31.3),
		new BMRTriple(80, 33.0, 30.9)
	};
	
	
	public double getBasicMetabolism(SwimmerInfo swimmerInfo){ 
		
		double bmr = getBMR(swimmerInfo);
		
		double weight = swimmerInfo.weight;
		double height = swimmerInfo.height;
		
		//体表面积（m2）＝0.00659×身高（cm）＋0.0126×体重（kg）－0.1603
		double bodySurfaceArea = 0.00659 * height + 
			0.0126 * weight -0.1603;
		double basicMetabolism = bodySurfaceArea * bmr;
		System.out.println("bmr = " + bmr);
		System.out.println("bodySurfaceArea = " + bodySurfaceArea);
		System.out.println("basicMetabolism = " + basicMetabolism);
		
		return basicMetabolism;
	}
	
	public double getBMR(SwimmerInfo swimmerInfo){
		int age = swimmerInfo.age;
		char sex = swimmerInfo.sex;
		int length = bmrs.length;
		
		for(int i = 1; i < length; i++){
			if(age < bmrs[i].age){
				if(sex == 'M'){
					return bmrs[i - 1].maleBMR;
				}else{
					return bmrs[i - 1].femaleBMR;
				}
			}
		}
		if(sex == 'M'){
			return bmrs[length - 1].maleBMR;
		}else{
			return bmrs[length - 1].femaleBMR;
		}
	}
}
