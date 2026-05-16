package fr.iglee42.createcasing.packets;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;

import fr.iglee42.createcasing.blockEntities.AutoClutchBlockEntity;
import fr.iglee42.createcasing.registries.EncasedPackets;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;

public class ConfigureAutoClutchPacket extends BlockEntityConfigurationPacket<AutoClutchBlockEntity> {

	public static final StreamCodec<ByteBuf, ConfigureAutoClutchPacket> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC, packet -> packet.pos,
			ByteBufCodecs.INT, p->p.stress,
			ByteBufCodecs.INT, p->p.mode,
			ByteBufCodecs.INT, p->p.operation,
			ConfigureAutoClutchPacket::new
	);

	private final int stress;
	private final int mode;
	private final int operation;

	public ConfigureAutoClutchPacket(BlockPos pos, int stress, int mode, int operation) {
		super(pos);
		this.stress = stress;
		this.mode = mode;
		this.operation = operation;
	}


	@Override
	protected void applySettings(ServerPlayer serverPlayer, AutoClutchBlockEntity be) {
		be.setConfiguredValue(stress);
		be.setMode(AutoClutchBlockEntity.Mode.byId(mode));
		be.setOperation(AutoClutchBlockEntity.Operation.byId(operation));
		RotationPropagator.handleAdded(be.getLevel(),pos, be);
	}

	@Override
	public PacketTypeProvider getTypeProvider() {
		return EncasedPackets.AUTO_CLUTCH_CONFIGURE;
	}
}
