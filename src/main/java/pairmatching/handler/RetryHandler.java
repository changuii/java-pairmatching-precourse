package pairmatching.handler;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryHandler {
    public void retryUntilTrue(final BooleanSupplier logic) {
        while (!logic.getAsBoolean()) {
        }
    }

    public <T> T retryUntilNotException(final Supplier<T> logic,
                                        final Consumer<IllegalArgumentException> exceptionCallback) {
        while (true) {
            try {
                return logic.get();
            } catch (final IllegalArgumentException e) {
                exceptionCallback.accept(e);
            }
        }
    }
}
