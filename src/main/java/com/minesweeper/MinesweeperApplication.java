package com.minesweeper;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinesweeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);
		
		System.out.println("#### initialized application ");
		
		Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter the size of the side of your square: ");
	    Integer fieldSide = null;
	    try{
	    	fieldSide = Integer.valueOf(scanner.next());
	    }catch (Exception e) {
	    	System.out.print("The size typed is not an integer");
	    	return;
		}
	    
	    System.out.print("Enter the json with the bomb position the format should be such as { \"bombsPosition\" : [[1,0],[0,0] }: for a square 2x2: ");
	    String bombsPositionJson = scanner.next();
	    
	    System.out.println("json: "+bombsPositionJson);
	    Minesweeper minesweeper = null;
	    try{
	    	minesweeper = new Minesweeper(fieldSide, bombsPositionJson);
	    }catch (Exception e) {
	    	System.out.print("The json inserted is not valid");
	    	return;
		}
	    
	    System.out.println("press n to continue");
	    String next = scanner.next();
	    
	    while(next.equalsIgnoreCase("n")){
	    	
	    	System.out.println("type the coordinates of the point into the field such as x,y :");
		    String coords = scanner.next();
		    
		    int x;
			int y;
			
		    try{
		    	String[] coordsArray = coords.split(",");
		    	
		    	x = Integer.valueOf(coordsArray[0]);
		    	y = Integer.valueOf(coordsArray[1]);
		    	
		    }catch (Exception e) {
		    	System.out.print("The value of coordinates is not correct");
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
				System.out.println("Near to the position ("+coords+") there are "+result+" bombs");
			}

	    }
	    
	    System.out.println("exit game");
	    scanner.close();
	    
		
	}
}
