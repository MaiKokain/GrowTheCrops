package yuria.growitout.actions;

import com.google.common.collect.ImmutableSet;
import yuria.growitout.Twerk;
import yuria.growitout.actions.compats.AgricraftAction;
import yuria.growitout.actions.compats.MysticalAction;
import yuria.growitout.actions.vanilla.BoneMeal;
import yuria.growitout.actions.vanilla.CropBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public enum Actions {
    INSTANCE;

    ImmutableSet<Action> actions_immu;
    final Set<Supplier<Action>> possibleActions = new HashSet<>();

    Actions()
    {
        register(BoneMeal::new);
        register(CropBlock::new);
        register(MysticalAction::new);
        register(AgricraftAction::new);
    }

    public void register(Supplier<Action> actionSupplier)
    {
        this.possibleActions.add(actionSupplier);
    }

    public void setup()
    {
        Set<Action> actions_hash = new HashSet<>();
        for (Supplier<Action> action : this.possibleActions)
        {
            Action trueAction = action.get();
            Twerk.LOGGER.info("Trying to register action: {}", action.getClass().getSimpleName());
            if (trueAction.isAvailable().getAsBoolean())
            {
                actions_hash.add(trueAction);
                Twerk.LOGGER.info("Registered action: {}", action.getClass().getSimpleName());
            } else {
                Twerk.LOGGER.info("Failed to register action: {}", action.getClass().getSimpleName());
            }
        }
        this.actions_immu = ImmutableSet.copyOf(actions_hash);
    }

    public static Actions get()
    {
        return INSTANCE;
    }

    public ImmutableSet<Action> getActions()
    {
        return actions_immu;
    }
}
