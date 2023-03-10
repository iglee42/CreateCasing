package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.basecog.ICustomCogWheel;
import com.rabbitminers.extendedgears.basecog.MetalCogWheel;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.compatibility.createcrystalclear.GlassEncasedCogwheelBlockCompat;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CustomGlassCogwheelCompat extends GlassEncasedCogwheelBlockCompat implements ICustomCogWheel {

    private final MetalCogWheel cogwheel;

    public CustomGlassCogwheelCompat(boolean large, Properties properties, BlockEntry<GlassCasing> casing, MetalCogWheel cogwheel) {
        super(large, properties, casing);
        this.cogwheel = cogwheel;
    }

    @Override
    public PartialModel getPartialModelType() {
        return cogwheel.getPartialModelType();
    }

    @Override
    public BlockEntityType<? extends SimpleKineticTileEntity> getTileEntityType() {
        return CreateExtendedCogwheelsCompat.COGWHEEL.get();
    }

    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (!context.getLevel().isClientSide) {
            context.getLevel().levelEvent(2001, context.getClickedPos(), Block.getId(state));
            KineticTileEntity.switchToBlockState(context.getLevel(), context.getClickedPos(), cogwheel.defaultBlockState().setValue(AXIS, (Direction.Axis) state.getValue(AXIS)));
        }
        return InteractionResult.SUCCESS;
    }

    public MetalCogWheel getCogwheel() {
        return cogwheel;
    }
}
