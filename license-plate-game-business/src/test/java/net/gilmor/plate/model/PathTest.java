package net.gilmor.plate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

import net.gilmor.plate.utility.LicensePlateTest;

public class PathTest extends LicensePlateTest {

	@Test
	void testAddEnd() {
		Crossing x1 = makeCrossing("CODE1", "CODE2");
		Crossing x2 = makeCrossing("CODE2", "CODE3");
		Path path = new Path();
		path.addEnd(x1);
		path.addEnd(x2);
		assertEquals(2, path.getCrossings().size());
		assertEquals(x1, path.getCrossings().get(0));
		assertEquals(x2, path.getCrossings().get(1));
	}

	@Test
	void testAddEndBad() {
		Crossing x1 = makeCrossing("CODE1", "CODE2");
		Crossing x2 = makeCrossing("CODE2", "CODE3");
		Path path = new Path();
		path.addEnd(x2);
		assertThrows(InvalidParameterException.class, () -> path.addEnd(x1));
	}

	@Test
	void testMergePath() {
		Path path1 = makePath("CODE1", "CODE2", "CODE3");
		Path path2 = makePath("CODE3", "CODE4", "CODE5");
		path1.mergePath(path2);
		assertEquals(4, path1.getCrossings().size());
		assertEquals("CODE1", path1.getStart().getCode());
		assertEquals("CODE5", path1.getEnd().getCode());
	}
}
