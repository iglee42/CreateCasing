package fr.iglee42.createcasing.compatibility.createextendedcogs;

import com.jozufozu.flywheel.core.PartialModel;
import com.rabbitminers.extendedgears.ExtendedCogwheels;
import com.rabbitminers.extendedgears.base.data.ICogwheelMaterial;
import com.rabbitminers.extendedgears.base.data.MetalCogwheel;
import com.rabbitminers.extendedgears.base.data.WoodenCogwheel;
import com.rabbitminers.extendedgears.base.datatypes.CogwheelModel;
import fr.iglee42.createcasing.CreateCasing;

import java.util.EnumMap;
import java.util.Map;

public class CreateExtendedCogwheelsPartials {
    public static final Map<MetalCogwheel, CogwheelModel> SHAFTLESS_METAL_COGWHEELS = fillCogwheels(MetalCogwheel.class, "shaftless");
    public static final Map<WoodenCogwheel, CogwheelModel> SHAFTLESS_WOODEN_COGWHEELS = fillCogwheels(WoodenCogwheel.class, "shaftless");


    public CreateExtendedCogwheelsPartials() {
    }

    public static <T extends Enum<T> & ICogwheelMaterial> Map<T, CogwheelModel> fillCogwheels(Class<T> materialType, String type) {
        Map<T, CogwheelModel> map = new EnumMap(materialType);
        String prefix = type != null ? type + "_" : "";
        Enum[] var4 = materialType.getEnumConstants();
        int var5 = var4.length;

        for (Enum anEnum : var4) {
            T material = (T) anEnum;
            map.put(material, new CogwheelModel(block("large_"+prefix + material.asId() + "_cogwheel"), block( prefix + material.asId() + "_cogwheel")));
        }

        return map;
    }

    private static PartialModel block(String path) {
        return new PartialModel(CreateCasing.asResource("block/extendedcogs/" + path));
    }

    public static void init() {
    }
}