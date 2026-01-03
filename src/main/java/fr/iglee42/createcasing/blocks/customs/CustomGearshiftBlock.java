package fr.iglee42.createcasing.blocks.customs;

import com.simibubi.create.content.kinetics.transmission.GearshiftBlock;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import com.simibubi.create.content.logistics.depot.DepotBlock;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomGearshiftBlock extends GearshiftBlock {

	public CustomGearshiftBlock(Properties props) {
		super(props);
	}

	@Override
	public BlockEntityType<? extends SplitShaftBlockEntity> getBlockEntityType() {
		return EncasedBlockEntities.GEARSHIFT.get();
	}
}
