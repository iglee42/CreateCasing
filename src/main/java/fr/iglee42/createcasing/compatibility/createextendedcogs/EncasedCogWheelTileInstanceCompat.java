//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.jozufozu.flywheel.api.InstanceData;
import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.core.materials.FlatLit;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.rabbitminers.extendedgears.basecog.ICustomCogWheel;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.base.KineticTileInstance;
import com.simibubi.create.content.contraptions.base.flwdata.RotatingData;
import com.simibubi.create.foundation.utility.Iterate;
import java.util.Optional;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EncasedCogWheelTileInstanceCompat extends KineticTileInstance<KineticTileEntity> {
    protected RotatingData rotatingModel;
    protected Optional<RotatingData> rotatingTopShaft;
    protected Optional<RotatingData> rotatingBottomShaft;


    public EncasedCogWheelTileInstanceCompat(MaterialManager modelManager, KineticTileEntity tile) {
        super(modelManager, tile);
    }

    public void init() {
        this.rotatingModel = this.setup((RotatingData)this.getCogModel().createInstance());
        Block block = this.blockState.getBlock();
        if (block instanceof IRotate def) {
            this.rotatingTopShaft = Optional.empty();
            this.rotatingBottomShaft = Optional.empty();
            Direction[] var3 = Iterate.directionsInAxis(this.axis);
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Direction d = var3[var5];
                if (def.hasShaftTowards(((KineticTileEntity)this.blockEntity).getLevel(), ((KineticTileEntity)this.blockEntity).getBlockPos(), this.blockState, d)) {
                    RotatingData data = this.setup((RotatingData)this.getRotatingMaterial().getModel(AllBlockPartials.SHAFT_HALF, this.blockState, d).createInstance());
                    if (d.getAxisDirection() == AxisDirection.POSITIVE) {
                        this.rotatingTopShaft = Optional.of(data);
                    } else {
                        this.rotatingBottomShaft = Optional.of(data);
                    }
                }
            }

        }
    }

    public void update() {
        this.updateRotation(this.rotatingModel);
        this.rotatingTopShaft.ifPresent((x$0) -> {
            this.updateRotation(x$0);
        });
        this.rotatingBottomShaft.ifPresent((x$0) -> {
            this.updateRotation(x$0);
        });
    }

    public void updateLight() {
        this.relight(this.pos, new FlatLit[]{this.rotatingModel});
        this.rotatingTopShaft.ifPresent((d) -> {
            this.relight(this.pos, new FlatLit[]{d});
        });
        this.rotatingBottomShaft.ifPresent((d) -> {
            this.relight(this.pos, new FlatLit[]{d});
        });
    }

    public void remove() {
        this.rotatingModel.delete();
        this.rotatingTopShaft.ifPresent(InstanceData::delete);
        this.rotatingBottomShaft.ifPresent(InstanceData::delete);
    }

    protected Instancer<RotatingData> getCogModel() {
        BlockState referenceState = ((KineticTileEntity)this.blockEntity).getBlockState();
        Direction facing = Direction.fromAxisAndDirection((Direction.Axis)referenceState.getValue(BlockStateProperties.AXIS), AxisDirection.POSITIVE);
        PartialModel partial = ICustomCogWheel.getPartialModelType(referenceState);
        if (partial == null){
            partial = AllBlockPartials.SHAFTLESS_COGWHEEL;
        }
        return this.getRotatingMaterial().getModel(partial, referenceState, facing, () -> {
            PoseStack poseStack = new PoseStack();
            ((TransformStack)((TransformStack)((TransformStack)TransformStack.cast(poseStack).centre()).rotateToFace(facing)).multiply(Vector3f.XN.rotationDegrees(90.0F))).unCentre();
            return poseStack;
        });
    }
}
