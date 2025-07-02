package fr.iglee42.createcasing.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import fr.iglee42.createcasing.blocks.ConfigurableGearboxBlock;
import fr.iglee42.createcasing.config.ModConfigs;
import fr.iglee42.createcasing.registries.EncasedBlocks;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPonderScenes {

    private static final Logger log = LoggerFactory.getLogger(CustomPonderScenes.class);

    public static void creativeCogwheel(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("creative_cogwheel", "Generating Rotational Force using Creative Cogwheels");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        BlockPos motor = util.grid().at(3, 1, 2);

        for (int i = 0; i < 4; i++) {
            scene.idle(5);
            scene.world().showSection(util.select().position(i, 1, 2), Direction.DOWN);
        }
        scene.idle(10);
        scene.effects().rotationSpeedIndicator(motor);
        scene.overlay().showText(50)
                .text("Creative cogwheel are a compact and configurable source of Rotational Force")
                .placeNearTarget()
                .pointAt(util.vector().topOf(motor));
        scene.idle(70);

        Vec3 blockSurface = util.vector().blockSurface(motor, Direction.NORTH);
        scene.overlay().showFilterSlotInput(blockSurface, Direction.NORTH, 80);
        scene.overlay().showControls(blockSurface, Pointing.DOWN,60).rightClick();
        scene.idle(20);

        scene.overlay().showText(60)
                .text("The generated speed can be configured on its input panels")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(blockSurface);
        scene.idle(10);
        scene.idle(50);
        scene.world().modifyKineticSpeed(util.select().fromTo(0, 1, 2, 3, 0, 2), f -> 4 * f);
        scene.idle(10);

        scene.effects().rotationSpeedIndicator(motor);
    }

    public static void configurableGearbox(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("configurable_gearbox", "Gearbox are so boring !");
        scene.configureBasePlate(1, 1, 5);
        scene.setSceneOffsetY(-1);

        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.world().showSection(util.select().fromTo(4, 1, 6, 3, 2, 5), Direction.UP);
        scene.idle(10);

        BlockPos gearbox = util.grid().at(3, 2, 3);
        BlockPos shaftBack = util.grid().at(3, 2, 4);
        BlockPos shaftFront = util.grid().at(3, 2, 2);
        BlockPos shaftRight = util.grid().at(2, 2, 3);

        scene.world().modifyKineticSpeed(util.select().fromTo(shaftRight,shaftRight.west()),(f)->32f);


        scene.world().showSection(util.select().position(shaftBack), Direction.SOUTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(gearbox),Direction.SOUTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(shaftRight), Direction.EAST);
        scene.world().showSection(util.select().position(shaftRight.west()), Direction.EAST);
        scene.idle(5);
        scene.world().showSection(util.select().position(shaftFront), Direction.SOUTH);
        scene.world().showSection(util.select().position(shaftFront.north()), Direction.SOUTH);

        scene.idle(20);

        scene.overlay().showText(80)
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(util.grid().at(3,2,3), Direction.UP))
                .placeNearTarget()
                .text("Gearboxes are useful to change the direction of a shaft");
        scene.idle(80);


        scene.world().showSection(util.select().fromTo(3,3,3,3,4,3), Direction.DOWN);

        scene.idle(20);
        scene.overlay().showText(80)
                .colored(PonderPalette.RED)
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 3),Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("But they are very annoying when you want to send the rotation in multiple axis");

        scene.idle(90);

        scene.world().setBlock(gearbox, EncasedBlocks.ANDESITE_CONFIGURABLE_GEARBOX.getDefaultState()
                .setValue(ConfigurableGearboxBlock.UP,false)
                .setValue(ConfigurableGearboxBlock.DOWN,false)
                .setValue(ConfigurableGearboxBlock.NORTH,true)
                .setValue(ConfigurableGearboxBlock.WEST,true)
        ,true);
        scene.world().modifyKineticSpeed(util.select().position(gearbox),(f)->32f);


        scene.idle(20);

        scene.overlay().showText(80)
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 3),Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("The configurable gearbox is here to fix this problem");

        scene.idle(90);

        scene.overlay().showControls(util.vector().topOf(gearbox),Pointing.DOWN,40)
                .rightClick().withItem(ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get() ? AllBlocks.SHAFT.asStack() : AllItems.WRENCH.asStack());
        scene.world().cycleBlockProperty(gearbox,ConfigurableGearboxBlock.UP);
        scene.world().modifyKineticSpeed(util.select().fromTo(3,3,3,3,4,3),(f)->32f);
        scene.idle(50);

        scene.overlay().showText(ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get() ? 60 : -10)
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 3),Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("You can add a shaft to this gearbox by right clicking on a face with a shaft");

        scene.overlay().showText(!ModConfigs.common().kinetics.configurableGearboxRequiresShaft.get() ? 60 : -10)
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 3),Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("You can add a shaft to this gearbox by right clicking on a face with a wrench");

        scene.idle(70);

        scene.overlay().showControls(util.vector().blockSurface(gearbox,Direction.NORTH),Pointing.RIGHT,40)
                .rightClick().withItem(AllItems.WRENCH.asStack());
        scene.world().cycleBlockProperty(gearbox,ConfigurableGearboxBlock.NORTH);
        scene.world().modifyKineticSpeed(util.select().fromTo(shaftFront,shaftFront.north()),(f)->0f);
        scene.idle(50);
        scene.overlay().showText(60)
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 3),Direction.NORTH))
                .placeNearTarget()
                .attachKeyFrame()
                .text("You can also remove a shaft on a face by right clicking with a wrench");

        scene.idle(90);

    }
}
