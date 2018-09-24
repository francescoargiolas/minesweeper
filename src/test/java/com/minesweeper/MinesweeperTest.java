package com.minesweeper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MinesweeperTest {
	
	@Autowired Environment env;
	
	@Test
	public void testInvalidJson() {
		try{
			new Minesweeper(2, "[{ \"bombsPosition\" : [[1,0],[0,0]] }");
		}catch (BombsJsonInvalidException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void testInvalidJsonWithNullBombsPosition() {
		try{
			new Minesweeper(2, "{ \"bombsPosition\" : null }");
		}catch (BombsJsonInvalidException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void testInvalidJsonWithMissmatchOfArraySizeSmaller() {
		try{
			new Minesweeper(3, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		}catch (BombsJsonInvalidException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void testInvalidJsonWithMissmatchOfArraySizeBigger() {
		try{
			new Minesweeper(1, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		}catch (BombsJsonInvalidException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void testInvalidJsonWithMissmatchOfArrayRowWithColumns() {
		try{
			new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0]] }");
		}catch (BombsJsonInvalidException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
	}
	
	@Test
	public void testPositionNotValidOnXNegative() throws BombsJsonInvalidException {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(null, minesweeper.checkPosition(-1, 0));
	}
	
	@Test
	public void testPositionNotValidOnXBiggerThanSide() throws BombsJsonInvalidException {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(null, minesweeper.checkPosition(2, 0));
	}
	
	@Test
	public void testPositionNotValidOnYNegative() throws BombsJsonInvalidException {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(null, minesweeper.checkPosition(0, -1));
	}
	
	@Test
	public void testPositionNotValidOnYBiggerThanSide() throws BombsJsonInvalidException {
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(null, minesweeper.checkPosition(0, 2));
	}
	
	@Test
	public void testPositionValidAndTherIsOneBombFromRight() throws BombsJsonInvalidException {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(new Integer(1), minesweeper.checkPosition(1, 0));
	}
	
	@Test
	public void testPositionValidAndTherIsOneBombFromDown() throws BombsJsonInvalidException {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(new Integer(1), minesweeper.checkPosition(0, 1));
	}
	
	@Test
	public void testPositionValidAndTherIsOneBombFromRightDown() throws BombsJsonInvalidException {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(new Integer(1), minesweeper.checkPosition(1, 1));
	}
	
	@Test
	public void testPositionValidAndThereIsBomb() throws BombsJsonInvalidException {
		
		Minesweeper minesweeper = new Minesweeper(2, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		Assert.assertEquals(new Integer(-1), minesweeper.checkPosition(0, 0));
	}

	// check invalid field size
	@Test
	public void testPositionInvalid() {
		
		try{
			Minesweeper minesweeper = new Minesweeper(0, "{ \"bombsPosition\" : [[1,0],[0,0]] }");
		}catch (BombsJsonInvalidException e) {
			Assert.assertTrue(true);
			return;
		}
		
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void testPositionInAValidFieldOfBigSize() throws BombsJsonInvalidException {
		
		Minesweeper minesweeper = new Minesweeper(3, "{ \"bombsPosition\" : [[1,1,1],[0,0,0],[0,0,0]]}");
		Assert.assertEquals(new Integer(-1), minesweeper.checkPosition(0, 0));
	}
	
}
