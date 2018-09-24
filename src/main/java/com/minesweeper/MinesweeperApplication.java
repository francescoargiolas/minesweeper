package com.minesweeper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gigya.socialize.SigUtils;

@SpringBootApplication
public class MinesweeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);
		
		String UID = "75e521a5858748d88bcb44f628987283";
		String timestamp = "1536928356";
		String secret = "test";
		String signature = "4S7gW665R2ugnKHjolOlyYedLBk="; //"bVXp5BNDNvMqUflVx8cd+UBvpZg=";
		
		try {
			
			String signatureStr = SigUtilsExtended.getUserSignature(UID, timestamp, secret, signature);
			System.out.println("signatureStr: "+signatureStr);
			
			boolean res = SigUtils.validateUserSignature(UID,timestamp,secret,signature);
			System.out.println("is signature valid? "+res);
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		System.out.println("#### initialized application ");
		
		Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter the size of the side of your field: ");
	    Integer fieldSide = null;
	    try{
	    	fieldSide = Integer.valueOf(scanner.next());
	    }catch (Exception e) {
	    	System.out.print("The size typed is not an integer");
	    	return;
		}
	    
	    System.out.print("Enter the json with the bomb position the format showld be like this { \"bombsPosition\" : \"1000\" }: for a field 2x2: ");
	    String bombsPositionJson = scanner.next();
	    
	    System.out.println("json: "+bombsPositionJson);
	    Minesweeper minesweeper = null;
	    try{
	    	minesweeper = new Minesweeper(fieldSide, bombsPositionJson);
	    }catch (Exception e) {
	    	System.out.print("The json inserted is not valid");
	    	return;
		}
	    
	    int iter = 0;
	    while(iter < minesweeper.getFieldLength()){
	    	
	    	System.out.println("type the coordinates of the point into the field such as x,y :");
		    String coords = scanner.next();
		    
		    int x;
			int y;
			
		    try{
		    	String[] coordsArray = coords.split(",");
		    	
		    	x = Integer.valueOf(coordsArray[0]);
		    	y = Integer.valueOf(coordsArray[1]);
		    	
		    }catch (Exception e) {
		    	System.out.print("The value of coorddinates is not correct");
		    	return;
			}

			Integer result = minesweeper.checkPosition(x, y);
			if(result == null){
				System.out.println("The position ("+coords+") is not valid");
			}else if(result == -1){
				System.out.println("In the position ("+coords+") there is a bomb!!!!");
				System.out.println("YOU LOSE!!!!");
				return;
			}else{
				System.out.println("Close to the position ("+coords+") there are "+result+" bombs");
			}
			
			iter++;
	    }
	    */
		
	}
}
