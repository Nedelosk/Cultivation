package nedelosk.cultivation.apiimp.soil;

import javax.annotation.concurrent.Immutable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import nedelosk.cultivation.api.soil.EnumAcidity;
import nedelosk.cultivation.api.soil.EnumMoisture;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilType;
import nedelosk.cultivation.apiimp.GardeningManager;

@Immutable
public class Soil implements ISoil {
	private final ISoilType type;
	private final EnumNutrition nutrition;
	private final EnumAcidity acidity;
	private final EnumMoisture moisture;

	public Soil(ISoilType type) {
		this.nutrition = type.getNutrition();
		this.acidity = type.getAcidity();
		this.moisture = type.getMoisture();
		this.type = type;
	}

	public Soil(EnumNutrition nutrition, EnumAcidity acidity, EnumMoisture moisture, ISoilType type) {
		this.nutrition = nutrition;
		this.acidity = acidity;
		this.moisture = moisture;
		this.type = type;
	}

	public Soil(NBTTagCompound compound) {
		this.nutrition = EnumNutrition.values()[compound.getByte("N")];
		this.acidity = EnumAcidity.values()[compound.getByte("A")];
		this.moisture = EnumMoisture.values()[compound.getByte("M")];
		this.type = GardeningManager.INSTANCE.typeRegistry.getValue(new ResourceLocation(compound.getString("T")));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setByte("N", (byte) nutrition.ordinal());
		compound.setByte("A", (byte) acidity.ordinal());
		compound.setByte("M", (byte) moisture.ordinal());
		compound.setString("T", type.getRegistryName().toString());
		return compound;
	}

	@Override
	public EnumAcidity getAcidity() {
		return acidity;
	}

	@Override
	public EnumMoisture getMoisture() {
		return moisture;
	}

	@Override
	public EnumNutrition getNutrition() {
		return nutrition;
	}

	@Override
	public ISoil withAcidity(EnumAcidity acidity) {
		return new Soil(nutrition, acidity, moisture, type);
	}

	@Override
	public ISoil withMoisture(EnumMoisture moisture) {
		return new Soil(nutrition, acidity, moisture, type);
	}

	@Override
	public ISoil withNutrition(EnumNutrition nutrition) {
		return new Soil(nutrition, acidity, moisture, type);
	}

	@Override
	public ISoil withType(ISoilType type) {
		return new Soil(nutrition, acidity, moisture, type);
	}

	@Override
	public ISoilType getType() {
		return type;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Soil)){
			return false;
		}
		Soil otherSoil = (Soil) obj;
		return nutrition == otherSoil.nutrition && acidity == otherSoil.acidity && moisture == otherSoil.moisture && type.equals(otherSoil.type);
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + nutrition.ordinal();
		hash = hash * 31 + acidity.ordinal();
		hash = hash * 31 + moisture.ordinal();
		hash = hash * 31 + GardeningManager.INSTANCE.typeRegistry.getID(type);
		return hash;
	}
}
