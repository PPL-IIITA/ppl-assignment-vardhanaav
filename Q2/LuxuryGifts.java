public class LuxuryGifts {
	int rating, difficulty;
	double value, price;
	String giftName;
	
	LuxuryGifts(String arr[]) {
		rating = Integer.parseInt(arr[0]);
		difficulty = Integer.parseInt(arr[1]);
		value = 2*(Double.parseDouble(arr[2]));
		price = Double.parseDouble(arr[3]);
		giftName = arr[4];		
	}
}
