package net.gilmor.plate.utility;

import org.junit.jupiter.api.Disabled;

import net.gilmor.plate.model.Area;
import net.gilmor.plate.model.Crossing;
import net.gilmor.plate.model.Path;

public class LicensePlateTest {
	
	@Disabled
	protected Area makeArea(String code, String...borderCodes) {
		Area area = new Area();
		area.setCode(code);
		for (String borderCode : borderCodes) {
			area.addBorder(borderCode, false, false);
		}
		return area;
	}
	
	@Disabled
	protected Crossing makeCrossing(String firstCode, String secondCode) {
		Area first = makeArea(firstCode);
		first.addBorder(secondCode, false, false);
		Area second = makeArea(secondCode);
		second.addBorder(firstCode, false, false);
		return new Crossing(first, second);
	}
	
	@Disabled
	protected Path makePath(String...codes) {
		Path path = new Path();
		for (int i=1; i < codes.length; i++) {
			path.addEnd(makeCrossing(codes[i-1], codes[i]));
		}
		return path;
	}

}
