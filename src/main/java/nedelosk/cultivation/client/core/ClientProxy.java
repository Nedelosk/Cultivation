package nedelosk.cultivation.client.core;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import nedelosk.cultivation.core.CommonProxy;
import nedelosk.cultivation.registry.RegistryHandler;
import nedelosk.cultivation.utils.IColoredBlock;
import nedelosk.cultivation.utils.IColoredItem;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		RegistryHandler.blocks.stream().filter(b->b instanceof IColoredBlock).map(b -> (IColoredBlock)b).forEach(c -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(ColoredBlockBlockColor.INSTANCE, (Block)c));
		RegistryHandler.items.stream().filter(b->b instanceof IColoredItem).map(b -> (IColoredItem)b).forEach(c -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler(ColoredItemItemColor.INSTANCE, (Item) c));
	}

	@SideOnly(Side.CLIENT)
	private static class ColoredItemItemColor implements IItemColor {
		public static final ColoredItemItemColor INSTANCE = new ColoredItemItemColor();

		private ColoredItemItemColor() {

		}

		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex) {
			Item item = stack.getItem();
			if (item instanceof IColoredItem) {
				return ((IColoredItem) item).getColorFromItemstack(stack, tintIndex);
			}
			return 0xffffff;
		}
	}

	@SideOnly(Side.CLIENT)
	private static class ColoredBlockBlockColor implements IBlockColor {
		public static final ColoredBlockBlockColor INSTANCE = new ColoredBlockBlockColor();

		private ColoredBlockBlockColor() {

		}

		@Override
		public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
			Block block = state.getBlock();
			if (block instanceof IColoredBlock && worldIn != null && pos != null) {
				return ((IColoredBlock) block).colorMultiplier(state, worldIn, pos, tintIndex);
			}
			return 0xffffff;
		}
	}
}
