package nedelosk.cultivation.blocks.properties;

import net.minecraftforge.common.property.IUnlistedProperty;

import nedelosk.cultivation.api.soil.ISoil;

public final class PropertySoil implements IUnlistedProperty<ISoil> {
	public static final PropertySoil INSTANCE = new PropertySoil();

	private PropertySoil() {
	}

	@Override
	public String getName() {
		return "soil";
	}

	@Override
	public boolean isValid(ISoil value) {
		return true;
	}

	@Override
	public Class<ISoil> getType() {
		return ISoil.class;
	}

	@Override
	public String valueToString(ISoil value) {
		return value.toString();
	}
}
