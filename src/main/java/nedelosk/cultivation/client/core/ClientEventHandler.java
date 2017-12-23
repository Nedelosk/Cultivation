package nedelosk.cultivation.client.core;

import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import nedelosk.cultivation.Cultivation;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.client.models.GardeningBoxModelBaked;
import nedelosk.cultivation.registry.ModBlocks;
import nedelosk.cultivation.registry.RegistryHandler;
import nedelosk.cultivation.utils.IBlockModelRegister;
import nedelosk.cultivation.utils.IItemModelRegister;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

	@SubscribeEvent
	public void onModelRegistration(ModelRegistryEvent event){
		RegistryHandler.blocks.stream().filter(b-> Item.getItemFromBlock(b) != Items.AIR && b instanceof IItemModelRegister).map(b -> (IItemModelRegister)b).forEach(IItemModelRegister::registerItemModels);
		RegistryHandler.items.stream().filter(b->b instanceof IItemModelRegister).map(b -> (IItemModelRegister)b).forEach(IItemModelRegister::registerItemModels);
		RegistryHandler.blocks.stream().filter(b->b instanceof IBlockModelRegister).map(b -> (IBlockModelRegister)b).forEach(IBlockModelRegister::registerModel);
	}

	@SubscribeEvent
	public void onBakeModel(ModelBakeEvent event){
		try {
			Method method = ReflectionHelper.findMethod(ModelLoader.class, "getModelBlockDefinition", "func_177586_a", ResourceLocation.class);
			method.setAccessible(true);
			ModelBlockDefinition blockDefinition = (ModelBlockDefinition) method.invoke(event.getModelLoader(), new ResourceLocation(Cultivation.MOD_ID, "gardening_box_soil"));
			blockDefinition.getMultipartData().setStateContainer(ModBlocks.raisedBed.getBlockState());
		}catch (Exception e){
			e.printStackTrace();
		}
		IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
		for(EnumNutrition nutrition : EnumNutrition.values()){
			Block block = ModBlocks.getGardeningBox(nutrition);
			for(ModelResourceLocation location : event.getModelManager().getBlockModelShapes().getBlockStateMapper().getVariants(block).values()){
				IBakedModel model = registry.getObject(location);
				if(!(model instanceof GardeningBoxModelBaked)) {
					registry.putObject(location, new GardeningBoxModelBaked(model, location.getVariant()));
				}
			}
		}

		GardeningBoxModelBaked.onBakeModel(event);
	}
}
