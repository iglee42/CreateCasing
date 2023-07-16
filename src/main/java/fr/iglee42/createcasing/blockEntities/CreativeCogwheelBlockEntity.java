package fr.iglee42.createcasing.blockEntities;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import com.simibubi.create.content.kinetics.motor.KineticScrollValueBehaviour;
import com.simibubi.create.content.kinetics.speedController.SpeedControllerBlock;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.VecHelper;
import fr.iglee42.createcasing.blocks.customs.CreativeCogwheelBlock;
import fr.iglee42.createcasing.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CreativeCogwheelBlockEntity extends GeneratingKineticBlockEntity {

    public static final int DEFAULT_SPEED = 16;
    public static final int MAX_SPEED = 256;

    protected ScrollValueBehaviour generatedSpeed;


    public CreativeCogwheelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        int max = MAX_SPEED;
        generatedSpeed = new KineticScrollValueBehaviour(Lang.translateDirect("kinetics.creative_motor.rotation_speed"),
                this, new CogwheelValueBox());
        generatedSpeed.between(-max, max);
        generatedSpeed.value = DEFAULT_SPEED;
        generatedSpeed.withCallback(i -> this.updateGeneratedRotation());
        behaviours.add(generatedSpeed);
    }

    @Override
    public List<BlockPos> addPropagationLocations(IRotate block, BlockState state, List<BlockPos> neighbours) {
        return super.addPropagationLocations(block, state, neighbours);
    }

    @Override
    public void initialize() {
        super.initialize();
        if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed())
            updateGeneratedRotation();
    }

    @Override
    public float getGeneratedSpeed() {
        if (!ModBlocks.CREATIVE_COGWHEEL.has(getBlockState()))
            return 0;
        return generatedSpeed.getValue();
    }

    class CogwheelValueBox extends ValueBoxTransform.Sided {

        @Override
        protected Vec3 getSouthLocation() {
                return VecHelper.voxelSpace(8, 8, 15.5f);
        }

        @Override
        protected boolean isSideActive(BlockState state, Direction direction) {
            return state.getValue(CreativeCogwheelBlock.AXIS) == direction.getAxis();
        }

    }
}
