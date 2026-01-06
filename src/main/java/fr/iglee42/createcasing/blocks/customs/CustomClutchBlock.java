package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.transmission.ClutchBlock;
import com.simibubi.create.content.kinetics.transmission.GearshiftBlock;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomClutchBlock extends ClutchBlock {

	public CustomClutchBlock(Properties props) {
		super(props);
	}

	@Override
	public BlockEntityType<? extends SplitShaftBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.CLUTCH.get();
	}
}
