package nz.ac.vuw.ecs.group16.openrocketextension;

import net.sf.openrocket.simulation.FlightEvent;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

/**
 * A simulation listener that listens for the time to apogee.
 * 
 * @author David
 */
public class MaxHeightSimulationListener extends AbstractSimulationListener {

  static SimulationStatus simStatus = null;

  public MaxHeightSimulationListener() {
    super();
  }

  @Override
  public boolean handleFlightEvent(SimulationStatus status, FlightEvent event)
      throws SimulationException {

    if (event.getType() == FlightEvent.Type.APOGEE) {
      MaxHeightSimulationListener.simStatus = status;
    }

    return true;
  }
}
