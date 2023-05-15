package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelModel;
import com.rabbitminers.extendedgears.cogwheels.CustomCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.HalfShaftCogwheelBlock;
import com.rabbitminers.extendedgears.cogwheels.ICustomCogwheel;
import com.rabbitminers.extendedgears.cogwheels.ShaftlessCogwheelBlock;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.compatibility.createcrystalclear.GlassEncasedCogwheelBlockCompat;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public class CustomGlassCogwheelCompat extends GlassEncasedCogwheelBlockCompat implements ICustomCogwheel {

    private final CustomCogwheelBlock cogwheel;
    private final HalfShaftCogwheelBlock halfCog;
    private final ShaftlessCogwheelBlock shaftlessCog;

    public CustomGlassCogwheelCompat(boolean large, Properties properties, BlockEntry<GlassCasing> casing, CustomCogwheelBlock cogwheel,HalfShaftCogwheelBlock halfCog, ShaftlessCogwheelBlock shaftlessCog) {
        super(large, properties, casing);
        this.cogwheel = cogwheel;
        this.halfCog = halfCog;
        this.shaftlessCog = shaftlessCog;
    }

    @Override
    public ICogwheelMaterial getMaterial() {
        return cogwheel.getMaterial();
    }

    @Override
    public PartialModel getPartialModelType() {
        PartialModel model = isLargeCog() ? AllBlockPartials.SHAFTLESS_LARGE_COGWHEEL :AllBlockPartials.SHAFTLESS_COGWHEEL;
        if(getWoodenCogwheel(cogwheel.getMaterial().asId()) != null){
            CogwheelModel cogModel = CreateExtendedCogwheelsPartials.SHAFTLESS_WOODEN_COGWHEELS.get(getWoodenCogwheel(cogwheel.getMaterial().asId()));
            model = isLargeCog() ? cogModel.large() : cogModel.small();
        } else {
            CogwheelModel cogModel = CreateExtendedCogwheelsPartials.SHAFTLESS_METAL_COGWHEELS.get(getMetalCogwheel(cogwheel.getMaterial().asId()));
            model = isLargeCog() ? cogModel.large() : cogModel.small();
        }
        return model;
    }

    public WoodenCogwheel getWoodenCogwheel(String id){
        return Arrays.stream(WoodenCogwheel.values()).filter(c-> c.asId().equals(id.toLowerCase())).findFirst().orElse(null);
    }

    public MetalCogwheel getMetalCogwheel(String id){
        return Arrays.stream(MetalCogwheel.values()).filter(c-> c.asId().equals(id.toLowerCase())).findFirst().orElse(null);
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

    public CustomCogwheelBlock getCogwheel() {
        return cogwheel;
    }

    public HalfShaftCogwheelBlock getHalfCog() {
        return halfCog;
    }

    public ShaftlessCogwheelBlock getShaftlessCog() {
        return shaftlessCog;
    }
}
