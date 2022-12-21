package com.revolvingmadness.scrolls.item;

import com.revolvingmadness.scrolls.Scrolls;
import com.revolvingmadness.scrolls.item.custom.TeleportScroll;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {
    public static final Item TELEPORT_SCROLL = registerItem("teleport_scroll", new TeleportScroll(new FabricItemSettings()
            .group(ModItemGroup.SCROLLS)
            .maxCount(1)
    ));

    public static final Item BLANK_SCROLL = registerItem("blank_scroll", new Item(new FabricItemSettings()
            .group(ModItemGroup.SCROLLS)
            .maxCount(1)
    ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Scrolls.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Scrolls.LOGGER.info("Registering Mod Items for " + Scrolls.MOD_ID);
    }

}
