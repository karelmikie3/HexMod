package at.petrak.hexcasting.api.spell.iota;

import at.petrak.hexcasting.common.lib.HexIotaTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An iota with no data associated with it.
 */
public class NullIota extends Iota {
    private static final Object NULL_SUBSTITUTE = new Object();

    /**
     * There's no <i>reason</i> you can't make your own new {@link NullIota}; it should work just fine.
     * But having a canonical one saves allocations.
     */
    public static final NullIota INSTANCE = new NullIota();

    public NullIota() {
        // We have to pass *something* here, but there's nothing that actually needs to go there,
        // so we just do this i guess
        super(HexIotaTypes.NULL, NULL_SUBSTITUTE);
    }

    @Override
    public boolean isTruthy() {
        return false;
    }

    @Override
    public boolean toleratesOther(Iota that) {
        return typesMatch(this, that);
    }

    @Override
    public @NotNull Tag serialize() {
        return new CompoundTag();
    }

    public static IotaType<NullIota> TYPE = new IotaType<>() {
        @Nullable
        @Override
        public NullIota deserialize(Tag tag, ServerLevel world) throws IllegalArgumentException {
            return new NullIota();
        }

        @Override
        public Component display(Tag tag) {
            return null;
        }

        @Override
        public int color() {
            return 0;
        }
    };
}