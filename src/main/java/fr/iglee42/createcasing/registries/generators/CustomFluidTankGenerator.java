package fr.iglee42.createcasing.registries.generators;

import com.simibubi.create.Create;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlock.Shape;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SpecialBlockStateGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;

import fr.iglee42.createcasing.registries.EncasedBlockStateGens;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class CustomFluidTankGenerator extends SpecialBlockStateGen {

	private final String name;

    public CustomFluidTankGenerator(String name) {
        this.name = name;
    }

    @Override
	protected int getXRotation(BlockState state) {
		return 0;
	}

	@Override
	protected int getYRotation(BlockState state) {
		return 0;
	}

	@Override
	public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
		BlockState state) {
		Boolean top = state.getValue(FluidTankBlock.TOP);
		Boolean bottom = state.getValue(FluidTankBlock.BOTTOM);
		Shape shape = state.getValue(FluidTankBlock.SHAPE);

		String shapeName = "middle";
		if (top && bottom)
			shapeName = "single";
		else if (top)
			shapeName = "top";
		else if (bottom)
			shapeName = "bottom";

		String modelName = shapeName + (shape == Shape.PLAIN ? "" : "_" + shape.getSerializedName());

		return prov.models()
				.withExistingParent("block/fluid_tank/"+name+"/block_" + modelName, Create.asResource("block/fluid_tank/block_" + modelName))
				.texture("0", prov.modLoc("block/fluid_tank_top/" + name))
				.texture("1", prov.modLoc("block/fluid_tank/" + name))
				.texture("3", prov.modLoc("block/fluid_tank_window/"+name))
				.texture("4", prov.modLoc("block/fluid_tank_inner/" + name))
				.texture("5", prov.modLoc("block/fluid_tank_window_single/"+name))
				.texture("particle", prov.modLoc("block/fluid_tank/"+name));

	}

}
