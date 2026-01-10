package fr.iglee42.createcasing.packets;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;
import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class ConfigureBrassShaftPacket extends BlockEntityConfigurationPacket<BrassShaftBlockEntity> {

	private int stress;
	private int mode;
	private int operation;

	public ConfigureBrassShaftPacket(BlockPos pos, int stress, int mode,int operation) {
		super(pos);
		this.stress = stress;
		this.mode = mode;
		this.operation = operation;
	}

	public ConfigureBrassShaftPacket(FriendlyByteBuf buffer) {
		super(buffer);
	}

	@Override
	protected void readSettings(FriendlyByteBuf buffer) {
		stress = buffer.readInt();
		mode = buffer.readInt();
		operation = buffer.readInt();
	}

	@Override
	protected void writeSettings(FriendlyByteBuf buffer) {
		buffer.writeInt(stress);
		buffer.writeInt(mode);
		buffer.writeInt(operation);
	}

	@Override
	protected void applySettings(BrassShaftBlockEntity be) {
		be.setMaxSupportedStress(stress);
		be.setMode(BrassShaftBlockEntity.Mode.byId(mode));
		be.setOperation(BrassShaftBlockEntity.Operation.byId(operation));
		RotationPropagator.handleAdded(be.getLevel(),pos, be);
	}

}