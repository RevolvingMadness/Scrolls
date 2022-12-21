package com.revolvingmadness.scrolls.item.custom;

import com.ibm.icu.impl.units.ComplexUnitsConverter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.jetbrains.annotations.Nullable;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class TeleportScroll extends Item {
    public TeleportScroll(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getStackInHand(hand);
        NbtCompound NBT = item.getOrCreateNbt();
        if (Objects.equals(item.getTranslationKey(), "item.scrolls.teleport_scroll")) {
            if (!NBT.contains("x") && world.isClient) user.sendMessage(Text.of("The Teleport Scroll has no location stored."), false);
            if (NBT.contains("x")) {
                if (!user.isSneaking()) {
                    double x = NBT.getDouble("x") + 0.5;
                    double y = NBT.getDouble("y") + 1.0;
                    double z = NBT.getDouble("z") + 0.5;
                    user.teleport(x, y, z);
                } else {
                    NBT.remove("x");
                    NBT.remove("y");
                    NBT.remove("z");
                    if (world.isClient) user.sendMessage(Text.of("Location removed."), false);
                }
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        assert player != null;
        ItemStack item = player.getStackInHand(context.getHand());
        NbtCompound NBT = item.getOrCreateNbt();
        Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        BlockPos blockPos = context.getBlockPos();
        if (Objects.equals(block.getTranslationKey(), "block.scrolls.teleport_scroll_block")) {
            double x = blockPos.getX();
            double y = blockPos.getY();
            double z = blockPos.getZ();
            if (context.getWorld().isClient && !NBT.contains("x")) player.sendMessage(Text.of("Stored Location: (" +
                    "X: " + x + ", " +
                    "Y: " + y + ", " +
                    "Z: " + z + ")"), false);
            NBT.putDouble("x", x);
            NBT.putDouble("y", y);
            NBT.putDouble("z", z);
        }


        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound NBT = stack.getOrCreateNbt();
        if (NBT.contains("x")) {
            double x = NBT.getDouble("x");
            double y = NBT.getDouble("y");
            double z = NBT.getDouble("z");
            tooltip.add(Text.of("Location Stored: (" +
                    "X: " + x + ", " +
                    "Y: " + y + ", " +
                    "Z: " + z + ")"));
        } else {
            tooltip.add(Text.of("No Location Stored"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getOrCreateNbt().contains("x");
    }
}
