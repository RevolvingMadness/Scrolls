package com.revolvingmadness.scrolls.item;

import com.revolvingmadness.scrolls.Scrolls;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup SCROLLS = FabricItemGroupBuilder.build(new Identifier(Scrolls.MOD_ID, "scrolls_tab"), () -> new ItemStack(ModItems.TELEPORT_SCROLL));
}
