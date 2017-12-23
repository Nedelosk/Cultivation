package nedelosk.cultivation.api.soil;

import javax.annotation.Nullable;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public enum EnumMoisture implements IStringSerializable {
	DRY(TextFormatting.YELLOW),
	NORMAL(null),
	DAMP(TextFormatting.DARK_BLUE);

	@Nullable
	private final TextFormatting color;

	EnumMoisture(@Nullable TextFormatting color) {
		this.color = color;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	public String getLocalisedName(boolean withColor) {
		String localisedName = I18n.translateToLocal("cultivation.moisture." + getName());
		if (withColor && color != null) {
			localisedName = color + localisedName + TextFormatting.GRAY;
		}
		return localisedName;
	}

	public static EnumMoisture getFromValue(float rawMoisture) {
		if(rawMoisture <= -1.0f){
			return EnumMoisture.DRY;
		}else if(rawMoisture >= 1.0f){
			return EnumMoisture.DAMP;
		}else{
			return EnumMoisture.NORMAL;
		}
	}
}
