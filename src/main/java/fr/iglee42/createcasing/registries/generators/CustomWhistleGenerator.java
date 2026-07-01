package fr.iglee42.createcasing.registries.generators;

import com.simibubi.create.Create;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SpecialBlockStateGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;

import fr.iglee42.createcasing.registries.EncasedBlockStateGens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class CustomWhistleGenerator extends SpecialBlockStateGen {

	private final String name;

    public CustomWhistleGenerator(String name) {
        this.name = name;
    }

    @Override
	protected int getXRotation(BlockState state) {
		return 0;
	}

	@Override
	protected int getYRotation(BlockState state) {
		return horizontalAngle(state.getValue(WhistleBlock.FACING));
	}

	@Override
	public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
		BlockState state) {
		String wall = state.getValue(WhistleBlock.WALL) ? "wall" : "floor";
		String size = state.getValue(WhistleBlock.SIZE)
			.getSerializedName();
		boolean powered = state.getValue(WhistleBlock.POWERED);
		ModelFile model = prov.models()
				.withExistingParent("block/steam_whistle/"+name+"/block_"+size +"_"+wall, Create.asResource("block/steam_whistle/block_"+size+"_"+wall))
				.texture("1", EncasedBlockStateGens.getEngineTexture(name))
				.texture("2", EncasedBlockStateGens.getRedstonePlate(name,false));
		if (!powered)
			return model;
		ResourceLocation parentLocation = model.getLocation();
		return prov.models()
			.withExistingParent(parentLocation.getPath() + "_powered", parentLocation)
				.texture("2", EncasedBlockStateGens.getRedstonePlate(name,true));
	}

}
