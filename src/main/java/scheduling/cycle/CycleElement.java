package scheduling.cycle;

import org.joda.time.DateTime;

public abstract class CycleElement {
	private int birthPeriod;
	private Cycle cycle;

	public CycleElement() {
	}

	public CycleElement(Cycle cycle) {
		init(cycle);
	}

	/**
	 * To be used when wanting to instance a CycleElement before instancing its
	 * cycle
	 */
	public void init(Cycle cycle) {
		this.cycle = cycle;
		this.birthPeriod = cycle.getPeriodsFromStart();
	}

	protected int getBirth() {
		return birthPeriod;
	}

	public int getAgeInPeriods() {
		return getCycle().getPeriodsFromStart() - getBirth();
	}

	protected DateTime getDate() {
		return getCycle().getDate();
	}

	public Cycle getCycle() {
		return cycle;
	}
}
