package opensource.pc.gw.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Random;

/**
 * @author olemerdy
 * @since 24/10/12
 */
public class HiLo {

    private static final Random RANDOM = new Random(System.nanoTime());

    @Inject
    private AlertManager alertManager;

    @Persist(PersistenceConstants.FLASH)
    @Property
    String message;

    @Persist
    @Property
    Integer target;

    @OnEvent(EventConstants.ACTIVATE)
    void onActivate() {
        if (target == null)
            resetTarget();
    }

    @Log
    @OnEvent(value = EventConstants.ACTION, component = "guessLink")
    void onGuess(int guess) {
        final Severity severity;
        if (guess == target) {
            message = "Bwavo";
            severity = Severity.SUCCESS;
        } else {
            message = "Better luck next time";
            severity = Severity.ERROR;
        }
        alertManager.alert(Duration.TRANSIENT, severity, message);
    }

    @OnEvent(value = EventConstants.ACTION, component = "resetLink")
    void resetTarget() {
        target = RANDOM.nextInt(10) + 1;
    }
}