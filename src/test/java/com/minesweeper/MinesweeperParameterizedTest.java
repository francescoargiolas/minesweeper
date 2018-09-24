package com.minesweeper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@RunWith(Parameterized.class)
@SpringBootTest
public class MinesweeperParameterizedTest {

	@Autowired Environment env;
	
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
	public static Collection input() throws IOException{

		File test3x3 = new File("src/main/resources/test3x3.json");
		File test5x5 = new File("src/main/resources/test5x5.json");
		String test3x3Json =  FileUtils.readFileToString(test3x3, "UTF-8");		
		String test5x5Json =  FileUtils.readFileToString(test5x5, "UTF-8");		

		return Arrays.asList(  
				new Object[][] { 
											
					{3, test3x3Json, 0, 0 ,-1},
					{3, test3x3Json, 1, 0 ,-1},
					{3, test3x3Json, 2, 0 ,-1},
					{3, test3x3Json, 0, 1 ,2},
					{3, test3x3Json, 1, 1 ,3},
					{3, test3x3Json, 2, 1 ,2},
					{3, test3x3Json, 0, 2 ,0},
					{3, test3x3Json, 1, 2 ,0},
					{3, test3x3Json, 2, 2 ,0},
					
					/*
					 * 
					 * 11111
					 * 00000
					 * 00000
					 * 11111
					 * 00000
					 * 
					 */
					{5, test5x5Json, 0, 0 ,-1},
					{5, test5x5Json, 1, 0 ,-1},
					{5, test5x5Json, 2, 0 ,-1},
					{5, test5x5Json, 3, 0 ,-1},
					{5, test5x5Json, 4, 0 ,-1},
					{5, test5x5Json, 0, 1 ,2},
					{5, test5x5Json, 1, 1 ,3},
					{5, test5x5Json, 2, 1 ,3},
					{5, test5x5Json, 3, 1 ,3},
					{5, test5x5Json, 4, 1 ,2},
					{5, test5x5Json, 0, 2 ,2},
					{5, test5x5Json, 1, 2 ,3},
					{5, test5x5Json, 2, 2 ,3},
					{5, test5x5Json, 3, 2 ,3},
					{5, test5x5Json, 4, 2 ,2},
					{5, test5x5Json, 0, 3 ,-1},
					{5, test5x5Json, 1, 3 ,-1},
					{5, test5x5Json, 2, 3 ,-1},
					{5, test5x5Json, 3, 3 ,-1},
					{5, test5x5Json, 4, 3 ,-1},
					{5, test5x5Json, 0, 4 ,2},
					{5, test5x5Json, 1, 4 ,3},
					{5, test5x5Json, 2, 4 ,3},
					{5, test5x5Json, 3, 4 ,3},
					{5, test5x5Json, 4, 4 ,2},
				}
		);				
	}
	
	@Test
	public void checkPositionTest() throws ParseException, BombsJsonInvalidException{
		
		Minesweeper minesweeper = new Minesweeper(filedSide,  inputJson);
		
		Integer actualResult = minesweeper.checkPosition(xPosition, yPosition);
		Assert.assertEquals(expectedResult, actualResult);
	}
	
}
