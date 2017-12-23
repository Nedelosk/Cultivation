package nedelosk.cultivation.api.soil;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ISoilModelProvider {

	@Nullable
	default String getModel(ISoil soil){
		return null;
	}

	@Nullable
	String getTexture(ISoil soil);
}
