package com.minesweeper;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Parameterized.class)
@SpringBootTest
public class MinesweeperParameterizedTest {

	@Parameter(value  = 0)
	public Integer filedSide;
	@Parameter(value  = 1)
	public String inputJson;
	@Parameter(value  = 2)
	public Integer xPosition;
	@Parameter(value  = 3)
	public Integer yPosition;
	@Parameter(value  = 4)
	public Integer expectedResult;

	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection input(){
		return Arrays.asList(  
				new Object[][] { 
											
					{3,"{ \"bombsPosition\" : \"111000000\" }", 0, 0 ,-1},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 1, 0 ,-1},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 2, 0 ,-1},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 0, 1 ,2},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 1, 1 ,3},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 2, 1 ,2},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 0, 2 ,0},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 1, 2 ,0},
					{3,"{ \"bombsPosition\" : \"111000000\" }", 2, 2 ,0},
					
					/*
					 * 
					 * 11111
					 * 00000
					 * 00000
					 * 11111
					 * 00000
					 * 
					 */
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 0, 0 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 1, 0 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 2, 0 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 3, 0 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 4, 0 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 0, 1 ,2},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 1, 1 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 2, 1 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 3, 1 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 4, 1 ,2},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 0, 2 ,2},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 1, 2 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 2, 2 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 3, 2 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 4, 2 ,2},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 0, 3 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 1, 3 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 2, 3 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 3, 3 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 4, 3 ,-1},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 0, 4 ,2},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 1, 4 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 2, 4 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 3, 4 ,3},
					{5,"{ \"bombsPosition\" : \"1111100000000001111100000\" }", 4, 4 ,2},
				}
		);				
	}
	
	@Test
	public void checkPositionTest() throws ParseException{
		
		Minesweeper minesweeper = new Minesweeper(filedSide,  inputJson);
		
		Integer actualResult = minesweeper.checkPosition(xPosition, yPosition);
		Assert.assertEquals(expectedResult, actualResult);
	}
	
}
