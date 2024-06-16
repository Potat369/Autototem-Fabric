package mike.autototem.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.profiling.jfr.event.ServerTickTimeEvent;
import net.minecraft.world.tick.Tick;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class AutototemClient implements ClientModInitializer {
    public static boolean totemEnabled;
    private static KeyBinding totemKeyBind;

    @Override
    public void onInitializeClient() {
        totemKeyBind = new KeyBinding(null, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, null);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (totemKeyBind.wasPressed()) {
                totemEnabled = !totemEnabled;

                assert client.player != null;
                client.player.sendMessage(Text.literal("AutoTotem was: ").append(totemEnabled ? Text.literal("Enabled") : Text.literal("Disabled")));
            }
        });
    }
}
