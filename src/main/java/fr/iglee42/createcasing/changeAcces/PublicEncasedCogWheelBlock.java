package fr.iglee42.createcasing.changeAcces;

import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.ModTiles;
import net.minecraft.world.level.block.entity.BlockEntityType;


/*
This class allow to use the constructore of EncasedCogWheelBlock from the create code
 */
public class PublicEncasedCogWheelBlock extends EncasedCogwheelBlock {

	public PublicEncasedCogWheelBlock(boolean large, Properties properties, BlockEntry<CasingBlock> casing) {
		super(large, properties, casing);
	}

	public BlockEntityType<? extends SimpleKineticTileEntity> getTileEntityType() {
		return this.isLargeCog() ? (BlockEntityType) ModTiles.ENCASED_COGWHEEL_LARGE.get() : (BlockEntityType)ModTiles.ENCASED_COGWHEEL.get();
	}
}