package nedelosk.cultivation.utils;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemModelRegister {
	
	@SideOnly(Side.CLIENT)
	void registerItemModels();
}
