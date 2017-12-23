package nedelosk.cultivation.api.soil;

import javax.annotation.Nullable;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public enum EnumNutrition implements IStringSerializable {
	POOR(TextFormatting.DARK_GRAY),
	NORMAL(TextFormatting.GOLD),
	RICH(TextFormatting.LIGHT_PURPLE);

	@Nullable
	private final TextFormatting color;

	EnumNutrition(TextFormatting color) {
		this.color = color;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	public String getLocalisedName(boolean withColor) {
		String localisedName = I18n.translateToLocal("cultivation.nutrition." + getName());
		if (withColor && color != null) {
			localisedName = color + localisedName + TextFormatting.GRAY;
		}
		return localisedName;
	}

	public static EnumNutrition getFromValue(float rawMoisture) {
		if(rawMoisture <= -1.0f){
			return EnumNutrition.POOR;
		}else if(rawMoisture >= 1.0f){
			return EnumNutrition.RICH;
		}else{
			return EnumNutrition.NORMAL;
		}
	}
}
