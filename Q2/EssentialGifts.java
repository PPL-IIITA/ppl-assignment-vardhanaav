public class EssentialGifts {
	double price, value;
	String giftName;
	
	EssentialGifts(String arr[]) {
		//rating = arr[0];
		//difficulty = arr[1];
		value = Double.parseDouble(arr[2]);
		price = Double.parseDouble(arr[3]);	
		giftName = arr[4];
	}
}
