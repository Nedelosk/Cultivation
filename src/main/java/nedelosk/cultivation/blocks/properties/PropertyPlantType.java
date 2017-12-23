package nedelosk.cultivation.blocks.properties;

import com.google.common.base.Optional;

import java.util.Collection;

import net.minecraft.block.properties.IProperty;

import nedelosk.cultivation.api.plant.IPlantType;
import nedelosk.cultivation.apiimp.GardeningManager;

public class PropertyPlantType implements IProperty<IPlantType> {
	private final String name;

	public PropertyPlantType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<IPlantType> getAllowedValues() {
		return GardeningManager.INSTANCE.getTypes();
	}

	@Override
	public Class<IPlantType> getValueClass() {
		return IPlantType.class;
	}

	@SuppressWarnings("all")
	@Override
	public Optional<IPlantType> parseValue(String value) {
		return Optional.fromNullable(GardeningManager.INSTANCE.getType(value));
	}

	@Override
	public String getName(IPlantType value) {
		return value.getName();
	}
}
