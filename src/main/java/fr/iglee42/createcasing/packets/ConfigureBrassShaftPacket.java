package fr.iglee42.createcasing.packets;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;

import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.blockEntities.MetalShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;

public class ConfigureBrassShaftPacket extends BlockEntityConfigurationPacket<BrassShaftBlockEntity> {

	private int stress;

	public ConfigureBrassShaftPacket(BlockPos pos, int stress) {
		super(pos);
		this.stress = stress;
	}

	public ConfigureBrassShaftPacket(FriendlyByteBuf buffer) {
		super(buffer);
	}

	@Override
	protected void readSettings(FriendlyByteBuf buffer) {
		stress = buffer.readInt();
	}

	@Override
	protected void writeSettings(FriendlyByteBuf buffer) {
		buffer.writeInt(stress);
	}

	@Override
	protected void applySettings(BrassShaftBlockEntity be) {
		be.setMaxSupportedStress(stress);
		RotationPropagator.handleAdded(be.getLevel(),pos, be);
	}

}
