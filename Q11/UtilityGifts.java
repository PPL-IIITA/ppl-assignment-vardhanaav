public class UtilityGifts {
	int uvalue, uclass;
	double value, price;
	String giftName;
	
	/**
	* constructor
	* @param arr contains the line to be read gifts
	*/
	public UtilityGifts(String arr[]) {
		uvalue = Integer.parseInt(arr[0]);
		uclass = Integer.parseInt(arr[1]);
		value = Double.parseDouble(arr[2]);
		price = Double.parseDouble(arr[3]);	
		giftName = arr[4];
	}
}
