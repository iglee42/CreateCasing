package fr.iglee42.createcasing.registries.generators;

import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlock;
import com.simibubi.create.foundation.data.SpecialBlockStateGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import fr.iglee42.createcasing.registries.EncasedBlockStateGens;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.neoforged.neoforge.client.model.generators.ModelFile;

public class CustomSmartFluidPipeGenerator extends SpecialBlockStateGen {

	private final String name;

    public CustomSmartFluidPipeGenerator(String name) {
        this.name = name;
    }

    @Override
	protected int getXRotation(BlockState state) {
		AttachFace attachFace = state.getValue(SmartFluidPipeBlock.FACE);
		return attachFace == AttachFace.CEILING ? 180 : attachFace == AttachFace.FLOOR ? 0 : 270;
	}

	@Override
	protected int getYRotation(BlockState state) {
		AttachFace attachFace = state.getValue(SmartFluidPipeBlock.FACE);
		int angle = horizontalAngle(state.getValue(SmartFluidPipeBlock.FACING));
		return angle + (attachFace == AttachFace.CEILING ? 180 : 0);
	}

	@Override
	public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
                                                BlockState state) {
		return EncasedBlockStateGens.smartPipeModel(prov,name,false);
	}

}
