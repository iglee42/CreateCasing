package fr.iglee42.createcasing.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.iglee42.createcasing.CreateCasing;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CreateCasingCommand {


    public CreateCasingCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> command = dispatcher.register(Commands.literal("createcasing")
                .then(Commands.literal("placeAllBlocks").requires(c->c.hasPermission(4)).executes(this::placeBlocks)
                        .then(Commands.argument("filter", StringArgumentType.string()).executes(this::placeBlocksWithFilter))));
        dispatcher.register(Commands.literal("cc").requires(c->c.hasPermission(4)).redirect(command));
    }

    private int placeBlocks(CommandContext<CommandSourceStack> source) {
        ServerLevel level = source.getSource().getLevel();
        if (!source.getSource().isPlayer()) return 0;
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        BuiltInRegistries.BLOCK.keySet().stream().filter(k->k.getNamespace().equals(CreateCasing.MODID)).forEach(k->{
            Block block = BuiltInRegistries.BLOCK.get(k);
            if (block != null){
                BlockPos pos = source.getSource().getPlayer().blockPosition().offset(x.get(), y.get(),0);
                level.setBlockAndUpdate(pos,block.defaultBlockState());
                x.getAndIncrement();
                if (x.get() > 16) {
                    x.set(0);
                    y.getAndIncrement();
                }
            }
        });
        return 1;
    }
    private int placeBlocksWithFilter(CommandContext<CommandSourceStack> source) {
        ServerLevel level = source.getSource().getLevel();
        if (!source.getSource().isPlayer()) return 0;
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        String filter = source.getArgument("filter",String.class);
        List<ResourceLocation> blocks =  BuiltInRegistries.BLOCK.keySet().stream().filter(k->k.getNamespace().equals(CreateCasing.MODID) && k.getPath().contains(source.getArgument("filter",String.class))).toList();
        if (filter.startsWith("/") && filter.endsWith("/")){
            String regex = filter.substring(1,filter.length() - 1);
            blocks = BuiltInRegistries.BLOCK.keySet().stream().filter(k->k.getNamespace().equals(CreateCasing.MODID) && k.getPath().matches(regex)).toList();
        }
        blocks.forEach(k->{
            Block block = BuiltInRegistries.BLOCK.get(k);
            if (block != null){
                BlockPos pos = source.getSource().getPlayer().blockPosition().offset(x.get(), y.get(),0);
                level.setBlockAndUpdate(pos,block.defaultBlockState());
                x.getAndIncrement();
                if (x.get() > 16) {
                    x.set(0);
                    y.getAndIncrement();
                }
            }
        });
        return 1;
    }


}
