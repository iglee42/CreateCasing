package fr.iglee42.createcasing.packets;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;

import fr.iglee42.createcasing.blockEntities.BrassShaftBlockEntity;
import fr.iglee42.createcasing.registries.EncasedPackets;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;

public class ConfigureBrassShaftPacket extends BlockEntityConfigurationPacket<BrassShaftBlockEntity> {

	public static final StreamCodec<ByteBuf, ConfigureBrassShaftPacket> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC, packet -> packet.pos,
			ByteBufCodecs.INT, p->p.stress,
			ByteBufCodecs.INT, p->p.mode,
			ByteBufCodecs.INT, p->p.operation,
			ConfigureBrassShaftPacket::new
	);

	private final int stress;
	private final int mode;
	private final int operation;

	public ConfigureBrassShaftPacket(BlockPos pos, int stress, int mode,int operation) {
		super(pos);
		this.stress = stress;
		this.mode = mode;
		this.operation = operation;
	}


	@Override
	protected void applySettings(ServerPlayer serverPlayer, BrassShaftBlockEntity be) {
		be.setMaxSupportedStress(stress);
		be.setMode(BrassShaftBlockEntity.Mode.byId(mode));
		be.setOperation(BrassShaftBlockEntity.Operation.byId(operation));
		RotationPropagator.handleAdded(be.getLevel(),pos, be);
	}

	@Override
	public PacketTypeProvider getTypeProvider() {
		return EncasedPackets.BRASS_SHAFT_CONFIGURE;
	}
}
