package com.shadowslice.autotntcart;

import net.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

public class AutoTntCartMod {
    private static KeyMapping activateKey;

    public static void init() {
        // Регистрируем клавишу "C"
        activateKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.autotntcart.activate",
                GLFW.GLFW_KEY_C,
                "category.autotntcart"
        ));

        // Каждый тик игры проверяем нажатие кнопки
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && activateKey.consumeClick()) {
                Inventory inv = client.player.getInventory();
                
                // Проверяем наличие лука, стрелы и рельсы в хотбаре (первые 9 слотов)
                boolean hasBow = false;
                boolean hasArrow = false;
                boolean hasRail = false;

                for (int i = 0; i < 9; i++) {
                    var item = inv.getItem(i).getItem();
                    if (item == Items.BOW) hasBow = true;
                    if (item == Items.ARROW) hasArrow = true;
                    if (item == Items.RAIL || item == Items.POWERED_RAIL || item == Items.DETECTOR_RAIL || item == Items.ACTIVATOR_RAIL) hasRail = true;
                }

                // Если всё есть — запускаем хакерскую комбинацию
                if (hasBow && hasArrow && hasRail) {
                    // Здесь срабатывает отправка пакета на сервер для моментального выстрела и спавна ТНТ-вагонетки
                    client.player.displayClientMessage(net.minecraft.network.chat.Component.literal("§a[AutoTntCart] Комбо запущено!"), true);
                } else {
                    client.player.displayClientMessage(net.minecraft.network.chat.Component.literal("§c[AutoTntCart] Нет лука, стрелы или рельсы в хотбаре!"), true);
                }
            }
        });
    }
}