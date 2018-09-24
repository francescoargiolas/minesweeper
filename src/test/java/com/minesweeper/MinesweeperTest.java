package com.minesweeper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MinesweeperTest {

	@Test
	public void testSetFieldLenght() {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		minesweeper.setFieldLength(3);
		Assert.assertEquals(new Integer(3), minesweeper.getFieldLength());
	}
	
	@Test
	public void testPositionNotValidOnXNegative() {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(null, minesweeper.checkPosition(-1, 0));
	}
	
	@Test
	public void testPositionNotValidOnXBiggerThanSide() {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(null, minesweeper.checkPosition(2, 0));
	}
	
	@Test
	public void testPositionNotValidOnYNegative() {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(null, minesweeper.checkPosition(0, -1));
	}
	
	@Test
	public void testPositionNotValidOnYBiggerThanSide() {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(null, minesweeper.checkPosition(0, 2));
	}
	
	@Test
	public void testPositionValidAndTherIsOneBombFromRight() {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(new Integer(1), minesweeper.checkPosition(1, 0));
	}
	
	@Test
	public void testPositionValidAndTherIsOneBombFromDown() {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(new Integer(1), minesweeper.checkPosition(0, 1));
	}
	
	@Test
	public void testPositionValidAndTherIsOneBombFromRightDown() {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(new Integer(1), minesweeper.checkPosition(1, 1));
	}
	
	@Test
	public void testPositionValidAndThereIsBomb() {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(new Integer(-1), minesweeper.checkPosition(0, 0));
	}

	// check invalid field size
	@Test
	public void testPositionInvalid() {
		
		Minesweeper minesweeper = new Minesweeper(0, "{ \"bombsPosition\" : \"1000\" }");
		Assert.assertEquals(null, minesweeper.checkPosition(0, 0));
	}
	
	@Test
	public void testPositionInAValidFieldOfBigSize() {
		
		Minesweeper minesweeper = new Minesweeper(3, "{ \"bombsPosition\" : \"111000000\" }");
		Assert.assertEquals(new Integer(-1), minesweeper.checkPosition(0, 0));
	}
	
	@Test
	public void testGetBombNumberPositionWithNegativePosition() {
		Minesweeper minesweeper = new Minesweeper(5, "{ \"bombsPosition\" : \"1111100000000001111100000\" }");
		Assert.assertEquals(new Integer(0), minesweeper.getBombNumber(-1));
	}
	
	@Test
	public void testGetBombNumberPositionWithPositionBiggerThanMaxFiledNumber() {
		Minesweeper minesweeper = new Minesweeper(5, "{ \"bombsPosition\" : \"1111100000000001111100000\" }");
		Assert.assertEquals(new Integer(0), minesweeper.getBombNumber(40));
	}
}
