package nedelosk.cultivation.api.plant;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum PlantSectionType implements IStringSerializable {
	TOP, CENTER, BOTTOM;


	@Override
	public String getName() {
		return name().toLowerCase(Locale.ENGLISH);
	}
}
